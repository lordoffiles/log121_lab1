package log121_lab1;
import java.util.ArrayList;

public interface Reader {
	
	/**
	 * 
	 * @param input is the string to split
	 * @param splitPoint is the character on which to split
	 * @return an array containing the processed strings
	 */
	public ArrayList<String> split(String input, String splitPoint);
	
	public ArrayList<String> split(boolean b, String input, String...splitPoints);
	
	public String findTag(String input);
	

}
