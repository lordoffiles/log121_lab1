package log121_lab1;

import java.util.ArrayList;

/**
 * Creates shape objects
 * @author Vincent Roy
 * @date 29/09/2015
 *
 */
public class Shaper {
	Shape shape;
	
	public Shaper() {
		shape = new Shape();
	}
	
	/**
	 * Creates a Shape corresponding to the name in the first index of the 
	 * list
	 * @param properties list of properties for the shape
	 * @return the shape object
	 */
	public Shape create(String[] properties) {
		
		String name = "";
		if(!properties[0].equals(null)){
			name = properties[0];
		}
		
		int[] prop = new int[4];
		
		int i = 2;
		
		
		while(i < properties.length && i < 6) {
			prop[i-2] = Integer.parseInt(properties[i]);
			i++;
		}
		
		
		switch(name) {
		
			case "CARRE":
				return shape.new Square(prop[0], prop[1], prop[2], prop[3]);
			
			case "RECTANGLE":
				return shape.new Rectangle(prop[0], prop[1], prop[2], prop[3]);
				
			case "CERCLE":
				return shape.new Circle(prop[0], prop[1], prop[2]);
				
			case "OVALE":
				return shape.new Oval(prop[0], prop[1], prop[2], prop[3]);
				
			case "LIGNE":
				return shape.new Line(prop[0], prop[1], prop[2], prop[3]);
				
			default:
				return shape.new Square(0,0,0,0);
				
			
		}
	}
}
