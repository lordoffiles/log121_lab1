package log121_lab1;

import java.util.ArrayList;

/**
 * Creates shape objects
 * @author Vincent
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
	 * @param shapeStrings list of properties from the shape
	 * @return the shape object
	 */
	public Shape create(ArrayList<String> shapeStrings) {
		System.out.println(shapeStrings);
		ArrayList<String> sl = shapeStrings;
		int[] prop = new int[sl.size()];
		
		int i = 2;
		while(i < prop.length) {
			prop[i] = Integer.parseInt(sl.get(i));
			
		}
		
		switch(shapeStrings.get(0)) {
		
			case "CARRE":
				return shape.new Square(prop[0], prop[1], prop[2], prop[3]);
			
			case "RECTANGLE":
				return shape.new Rectangle(prop[0], prop[1], prop[2], prop[3]);
				
			case "CERCLE":
				return shape.new Circle(prop[0], prop[1], prop[2]);
				
			case "OVAL":
				return shape.new Oval(prop[0], prop[1], prop[2], prop[3]);
				
			case "LIGNE":
				return shape.new Line(prop[0], prop[1], prop[2], prop[3]);
				
			default:
				return null;
				
			
		}
	}
}
