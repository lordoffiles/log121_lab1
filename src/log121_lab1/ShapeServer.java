/*
 * Cours: LOG121
 * Session: A2016
 * Groupe: 02
 * Projet: Laboratoire 1
 * Étudiant(e)(s): Vincent Roy
 * Professeur: Vincent Lacasse
 * Num du fichier: ShapeServer.java
 * Date création: 18-09
 * Date dern. modif.: 02-10
 */
package log121_lab1;

import java.io.IOException;
import ca.etsmtl.log.util.*;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.beans.PropertyChangeListener;
import javax.swing.SwingWorker;

/**
 * Connection to the server with java.net.socket and worker thread with 
 * javax.swing.Swingworker 
 * @author Vincent
 * @date 29/09/2015
 *
 */
public class ShapeServer implements Connection {
	
	private final long DELAY = 1000;
	
	private Socket socket;
	private DataInputStream in;
	private PrintWriter out;
	@SuppressWarnings("rawtypes")
	private SwingWorker worker;
	private PropertyChangeListener listener;
	private Reader cipher;
	private Shaper shaper;
	
	private IDLogger logger = IDLogger.getInstance();;
	

	public ShapeServer() {
		shaper = new Shaper();
		
	}
	
	/**
	 * Creates a new socket with the specified port and address
	 * @param address
	 * @param port
	 * 
	 */
	public boolean open(InetAddress address, int port)  {
		
		try {
			socket = new Socket(address, port);
			try {
				in = new DataInputStream(socket.getInputStream());
				out = new PrintWriter(socket.getOutputStream(), true);
			} catch (IOException e) {
				throw new RuntimeException();
			}
			
		} catch(IOException e) {
			
			return false;
		}
		
		return true;
		
	}
	
	/**
	 * returns the address, port and status(up or down) of the connection
	 * 
	 * @return a formated string containing the status of the connection
	 */
	public String status() {
		if(socket != null) {
			return "Connected to "+socket.getInetAddress() + " on port " + socket.getPort();
			
		} else {
			return "Not connected";
			
		}
		
	}
	
	
	/**
	 * sends GET command to server through a PrintWriter connected to the
	 * outputStream of the socket and reads with a bufferedReader from the 
	 * inputStream.
	 * 
	 * @throws IOException if the connection can't send command or receive 
	 * response to and from the server
	 */
	public String getResponse() {
		try {	
			out.println("GET");
				
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			
			String line = "";
			while(reader.ready()){
				line += reader.readLine();
			}
	
			return line;
			
		} catch(IOException e) {
			throw new RuntimeException();
			
		} 
		
	}
	
	/**
	 * SwingWorker to get responses from the server asynchronously from the EDT.
	 * 
	 * 
	 */
	@SuppressWarnings("rawtypes")
	public void startGetLoop() {
		System.out.println("lel");
		worker = new SwingWorker() {
			
			@Override
			protected Object doInBackground() throws Exception {
				String serverShape = null;
				//Empty response counter
				int emptyCounter = 0;
				
				while(true) {
					
					Thread.sleep(DELAY);
					
					serverShape = getResponse();
					
					if(!serverShape.equals("")) {
						ArrayList<String> splitShape = cipher.split(true ,serverShape, "<?/?[A-Za-z]+>", " ");
						
						if(serverShape.contains("CARRE")) {
							splitShape.add(0, "CARRE");
							
						} else if(serverShape.contains("RECTANGLE")) {
							splitShape.add(0, "RECTANGLE");
							
						} else if(serverShape.contains("CERCLE")) {
							splitShape.add(0, "CERCLE");
							
						} else if(serverShape.contains("OVALE")) {
							splitShape.add(0, "OVALE");
							
						} else if(serverShape.contains("LIGNE")) {
							splitShape.add(0, "LIGNE");
							
						}
						
						String[] str = new String[splitShape.size()];
						
						for(int i = 0; i <splitShape.size(); i++) {
							str[i] = splitShape.get(i);
							
						}
						
						Shape shape;
						if(str.length > 2){
							shape = shaper.create(str);
							logger.logID(Integer.parseInt(str[1]));
							
						} else{ 
							shape = shaper.create(new String[]{"CARRE","0","0","0","0"});
							
						}
						
						if(listener != null &&shape.originX>0){
							firePropertyChange("Shape", new String(str[0]), shape);
							
						}
						
					} else {
						emptyCounter++;
						
						if(emptyCounter>5) {
							firePropertyChange("Alert", null, null);
							
						}
						
					}
					/*
					 * Edit du 30-09 au soir
					 * Je sais maintenant avec certitude que le thread plantait
					 * . Il passe à la méthode done() quand il plante.
					 * J'ai lu que SwingWorker "Mange" les exception. Il fait 
					 * juste planter lors d'une exception, rien d'autre. 
					 * Ainsi j'ai trouvé qu'en mettant la methode get() dans le 
					 * done(), il m'affichait ainsi la stack de l'exception.
					 * 
					 * 
					 */
					
				}
								
			}
			
			@Override
			protected void done() {
				try {
					get();
					
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
					
				}
				
			}
			
		};
		
		worker.addPropertyChangeListener(listener);
		worker.execute();
		
	}
	/**
	 * Sets the listener for sending information to the EDT in the worker 
	 * @param listener element on which the PropertyChangeListener is set
	 */
	public void setProprietyChangeListener(PropertyChangeListener listener) {
		this.listener = listener;
		
	}
	
	/**
	 * Closes the socket by sending the END command to the server. Also stops
	 * the SwingWorker.
	 */
	public boolean close() {
		if(worker != null){	
			try{
				worker.cancel(true);
				
			} catch(CancellationException e) {
				System.out.println("worker cancelled");
				
			}
			
		}
			
		out.println("END");
		socket = null;

		return true;
	}
	

	public void setCipher(Reader cipher) {
		this.cipher = cipher;
		
	}
	
//	public void setShaper(Shaper shaper) {
//		this.shaper = shaper;
//	}
	
	public boolean isSocketOpen() {
		if(socket != null){
			return true;
		} else {
			return false;
		}
					
	}

}
