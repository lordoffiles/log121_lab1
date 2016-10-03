/*
 * Cours: LOG121
 * Session: A2016
 * Groupe: 02
 * Projet: Laboratoire 1
 * Étudiant(e)(s): Vincent Roy
 * Professeur: Vincent Lacasse
 * Num du fichier: WindowManager.java
 * Date création: 18-09
 * Date dern. modif.: 02-10
 */
package log121_lab1;

import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Main application window and manages components with javax.swing.JFrame
 * @author Vincent
 * @date 29/09/2015
 *
 */
public class WindowManager extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private DisplayQueue queue;
	
	public static final int WIDTH = 500;
	public static final int HEIGHT = 500;
	
	
	public WindowManager() {
		setSize(WIDTH, HEIGHT);
		setTitle("Client - Shape");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		
		queue = new DisplayQueue(10);
		repaint();
		
	}
	
	
	
	/**
	 * Adds the specified component to the display queue which acts in a first
	 * in, first out fashion
	 * @param comp the component to add
	 * @throws CloneNotSupportedException 
	 */
	public void addToDisplayQueue(Component comp) throws CloneNotSupportedException {
		DisplayQueue oldQueue = (DisplayQueue)queue.clone();
		
		
		queue.addToQueue(comp);

		
		validateDisplay(oldQueue);
		
	}
	
	/**
	 * Verifies which component is the newest in the queue by comparing to the 
	 * previous queue of elements and adds it to 
	 * the frame
	 * @param oldQueue the queue before adding the element
	 */
	private void validateDisplay(DisplayQueue oldQueue) {
		int i = 0;
		Component queueElement;

		while(i < queue.size()) {
			queueElement = queue.get(i);
			if( oldQueue.get(i) != null && !(oldQueue.get(i)).equals( (queueElement) ) ) {
				remove(queue.get(0));
				
				add(queue.get(i));
				validate();
				return;
				
			} else if(oldQueue.get(i) == null) {
				add(queue.get(i));
				validate();
				return;
			}
			i++;
		}
		
		

	}
	
	/**
	 * Displays a popup window
	 * @param title of the popup
	 * @param message inside the popup
	 */
	public void alert(String title, String message) {
		JOptionPane.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE);
		
	}
	
	/**
	 * prompts the user for connection info
	 * @return the string with the input from the user
	 */
	public String prompt() {
		String in = JOptionPane.showInputDialog(this, "Entrez les informations de connection.", "127.0.0.1:10000");
		
		return in;
	}
	
	/**
	 * prompts the user for connection info and adds an error message before the 
	 * text.
	 * @param error to display before the message
	 * @return the string with the input from the user
	 */
	public String prompt(String error) {
		String in = JOptionPane.showInputDialog(this,
				error
				+ "\n"
				+ "Entrez les informations de connection.", 
				"127.0.0.1:10000");
		
		return in;
		
	}

	
}
