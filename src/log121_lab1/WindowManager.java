package log121_lab1;

import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 * Frame containing the shapes
 * @author Vincent
 * @date 29/09/2015
 *
 */
public class WindowManager extends JFrame {
	DisplayQueue queue;
	
	
	public WindowManager() {
		setSize(500, 500);
		setTitle("Client - Shape");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		queue = new DisplayQueue(10);
		
		
		/*
		 * TODO Do menu bar
		 */
		
		
		
		
		
	}
	
	/**
	 * Adds the specified component to the display queue which acts in a first
	 * in first out fashion
	 * @param comp the component to add
	 */
	public void addToDisplayQueue(Component comp) {
		System.out.println(comp);
		DisplayQueue oldQueue = queue;
		queue.addToQueue(comp);
		
		validateDisplay(oldQueue);
		
	}
	
	/**
	 * Verifies which component is the newest in the queue and adds it to 
	 * the frame
	 * @param oldQueue the queue before adding the element
	 */
	private void validateDisplay(DisplayQueue oldQueue) {
		int i = 0;
		while(i < queue.size()) {
			if( !(oldQueue.get(i)).equals( (queue.get(i)) ) ) {
				remove(i);
				add(queue.get(i));
				
			}
			
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
