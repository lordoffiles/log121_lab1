package log121_lab1;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class WindowManager extends JFrame {
	
	public WindowManager() {
		setSize(500, 500);
		setTitle("Client - Shape");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		
		
		
	}
	
	public String prompt() {
		String in = JOptionPane.showInputDialog(this, "Entrez les informations de connection.", "10.0.0.1:9999");
		
		return in;
	}
	
	public String prompt(String error) {
		String in = JOptionPane.showInputDialog(this,
				error
				+ "\n"
				+ "Entrez les informations de connection.", 
				"10.0.0.1:9999");
		
		return in;
		
	}
	
}
