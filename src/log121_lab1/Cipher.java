package log121_lab1;

import java.util.ArrayList;

/**
 * Reads the strings and processes them
 * @author Vincent Roy
 * @date 29/09/2015
 *
 */
public class Cipher implements Reader {

	public ArrayList<String> split(String input, String splitPoint){
		
		ArrayList<String> splitList = new ArrayList<String>(); 
		String[] splitted = input.split(splitPoint);
		for(String line : splitted) {
			if(!line.equals("")) {
				splitList.add(line);
				
			}
			
		}
		
		return splitList;
	}
	
	public String findTag(String input) {
		String str = input;
		int i = 0;
		char currentChar = str.charAt(0);
		
		while(i < input.length()) {
			
			if(currentChar != '<') {
				 str = str.substring(1);
				currentChar = str.charAt(0);
			} else {
				break;
			}
			
			i++;
		}
		
		i = 1;
		
		while(currentChar != '>') {
			currentChar = str.charAt(i);
			i++;
			
		}
		
		str = str.substring(1, i-1);
		
		
		
		
		return str;
	}
	
	/**
	 * Splits a string multiple times in the order the splitpoints arguments were passed
	 * 
	 * @param input is the string to split
	 * @param String...splitpoints the points on which to split the string. accepts multiple parameters
	 * @param b is a param for nothing (only there to differentiate from the other split method...)
	 * @return the list of strings split on the splitpoints
	 */
	public ArrayList<String> split(boolean b, String input, String...splitPoints) {
		ArrayList<String> stringList = new ArrayList<String>();
		ArrayList<String> working = new ArrayList<String>();
		
		int i = 0;
		do{
			if(i == 0) {
				for(String line : split(input, splitPoints[i])) {
					if(!line.equals(" "))	
						stringList.add(line);
					
				}
				
			} else {
				
				for(String element : stringList) {
					
					for(String line : split(element, splitPoints[i])){
						if(!line.equals(" "))
							working.add(line);
						
					}
					
				}
				
				stringList = working;
				
			}
			
			
			i++;
			
		} while(i<splitPoints.length);
		
		//System.out.println(stringList.size());
		
		return stringList;
	}
}
