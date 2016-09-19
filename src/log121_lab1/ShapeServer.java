package log121_lab1;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class ShapeServer implements Connection {
	
	Socket socket;
	public ShapeServer() {
		
	}
	
	public boolean open(InetAddress address, int port) throws IOException{
		try{
			socket = new Socket(address, port);
			
		} catch(IOException e) {
			throw e;
			
		}
		System.out.println("connected");
		return true;
		
	}
	
	/**
	 * returns the address, port and status of the connection
	 * 
	 * @return a formated string containing the status of the connection
	 */
	public String status() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean get() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean close() {
		try{
			socket.close();
		} catch(IOException e){
			return false;
		}
		
		return true;
	}


}
