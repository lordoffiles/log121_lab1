package log121_lab1;

import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;


public class WindowManager extends JFrame {
	Component[] displayQueue = new Component[10];
	
	
	public WindowManager() {
		setSize(500, 500);
		setTitle("Client - Shape");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		/*
		 * TODO Do menu bar
		 */
		
		
		
		
		
	}
	
	public void addToDisplayQueue(Component comp) {
	
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
