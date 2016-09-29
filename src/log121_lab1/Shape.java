package log121_lab1;

import java.awt.Component;

public abstract class Shape extends Component{
	int originX;
	int originY;
	int x2;
	int y2;
	int radiusX;
	int radiusY;
	

	public class Square extends Shape{
		public Square(int originX, int originY, int x2, int y2) {
			super();
			this.originX = originX; 
			this.originY = originY;
			this.x2 = x2;
			this.y2 = y2;
		}
	}

	class Rectangle extends Shape{
		public Rectangle(int originX, int originY, int x2, int y2) {
			super();
			this.originX = originX; 
			this.originY = originY;
			this.x2 = x2;
			this.y2 = y2;
		}
	}

	class Circle extends Shape{
		public Circle(int originX, int originY, int radiusX) {
			super();
			this.originX = originX; 
			this.originY = originY;
			this.radiusX = radiusX;
		}
		
	}

	class Oval extends Shape{
		public Oval(int originX, int originY, int radiusX, int radiusY) {
			super();
			this.originX = originX; 
			this.originY = originY;
			this.radiusX = radiusX;
			this.radiusY = radiusY;
		}
	}

	class Line extends Shape{
		public Line(int originX, int originY, int x2, int y2) {
			super();
			this.originX = originX; 
			this.originY = originY;
			this.x2 = x2;
			this.y2 = y2;
		}
	}

}
