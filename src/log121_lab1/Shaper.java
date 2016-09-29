package log121_lab1;

import java.util.ArrayList;

public class Shaper {
	
	Shape shape;
	public Shaper() {
		shape = new Shape();
	}
	
	public Shape create(ArrayList<String> shapeStrings) {
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
