package log121_lab1;

import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;


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
	
	public void addToDisplayQueue(Component comp) {
		DisplayQueue oldQueue = queue;
		queue.addToQueue(comp);
		
		validateDisplay(oldQueue);
		
	}
	
	private void validateDisplay(DisplayQueue oldQueue) {
		int i = 0;
		while(i < queue.size()) {
			if( !(oldQueue.get(i)).equals( (queue.get(i)) ) ) {
				remove(i);
				add(queue.get(i));
				
			}
			
		}
		
	}
	
	public void alert(String title, String message) {
		JOptionPane.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE);
		
	}
	
	public String prompt() {
		String in = JOptionPane.showInputDialog(this, "Entrez les informations de connection.", "127.0.0.1:10000");
		
		return in;
	}
	
	public String prompt(String error) {
		String in = JOptionPane.showInputDialog(this,
				error
				+ "\n"
				+ "Entrez les informations de connection.", 
				"127.0.0.1:10000");
		
		return in;
		
	}

	
}
