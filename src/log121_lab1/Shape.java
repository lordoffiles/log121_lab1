package log121_lab1;

import java.awt.Color;
import java.awt.Component;

/**
 * Shapes - Square, Rectangle, Circle, Oval, Line
 * @author Vincent Roy
 * @date 29/09/2015
 *
 */
public class Shape extends Component{
	
	private static int CYAN = 0x00FFFF;
	private static int MAGENTA = 0xFF00FF;
	private static int RED = 0xFF0000;
	private static int YELLOW = 0xFFFF00;
	private static int BROWN = 0x9C2700;
	int originX;
	int originY;
	int x2;
	int y2;
	int radiusX;
	int radiusY;
	Color color;
	

	public class Square extends Shape{
		public Square(int originX, int originY, int x2, int y2) {
			super();
			this.originX = originX; 
			this.originY = originY;
			this.x2 = x2;
			this.y2 = y2;
			this.color = new Color(CYAN);
		}
	}

	class Rectangle extends Shape{
		public Rectangle(int originX, int originY, int x2, int y2) {
			super();
			this.originX = originX; 
			this.originY = originY;
			this.x2 = x2;
			this.y2 = y2;
			this.color = new Color(MAGENTA);
		}
	}

	class Circle extends Shape{
		public Circle(int originX, int originY, int radiusX) {
			super();
			this.originX = originX; 
			this.originY = originY;
			this.radiusX = radiusX;
			this.color = new Color(RED);
		}
		
	}

	class Oval extends Shape{
		public Oval(int originX, int originY, int radiusX, int radiusY) {
			super();
			this.originX = originX; 
			this.originY = originY;
			this.radiusX = radiusX;
			this.radiusY = radiusY;
			this.color = new Color(YELLOW);
		}
	}

	class Line extends Shape{
		public Line(int originX, int originY, int x2, int y2) {
			super();
			this.originX = originX; 
			this.originY = originY;
			this.x2 = x2;
			this.y2 = y2;
			this.color = new Color(BROWN);
		}
	}

}
