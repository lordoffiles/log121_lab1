/*
 * Cours: LOG121
 * Session: A2016
 * Groupe: 02
 * Projet: Laboratoire 1
 * Étudiant(e)(s): Vincent Roy
 * Professeur: Vincent Lacasse
 * Num du fichier: Shape.java
 * Date création: 18-09
 * Date dern. modif.: 02-10
 */
package log121_lab1;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import org.junit.Before;

/**
 * Shapes - Square, Rectangle, Circle, Oval, Line
 * @author Vincent Roy
 * @date 29/09/2015
 *
 */
public class Shape extends Component{
	
	protected static int CYAN = 0x00FFFF;
	protected static int MAGENTA = 0xFF00FF;
	protected static int RED = 0xFF0000;
	protected static int YELLOW = 0xFFFF00;
	protected static int BROWN = 0x9C2700;
	
	protected int originX;
	protected int originY;
	protected int x2;
	protected int y2;
	protected int radiusX;
	protected int radiusY;
	protected Color color;
	
	public Shape() {
		originX = 0;
		originY = 0;
		x2 = 0;
		y2 = 0;
		radiusX = 0;
		radiusY = 0;
		color = null;
	}
	
	

	public class Square extends Shape{
		public Square(int originX, int originY, int x2, int y2) {
			super();
			this.originX = originX;
			this.originY = originY;
			this.x2 = x2;
			this.y2 = y2;
			this.color = new Color(CYAN);
			
		}
		

		@Override
		public void paint(Graphics g) {
			super.paint(g);
			g.setColor(color);
			g.fillRect(originX, originY, x2, y2);
			
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
		
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			g.setColor(color);
			g.fillRect(originX, originY, x2, y2);
			
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
		
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			g.setColor(color);
			g.fillOval(originX, originY, radiusX, radiusX);
			
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
		
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			g.setColor(color);
			g.fillOval(originX, originY, radiusX, radiusY);
			
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
		
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			g.setColor(color);
			g.drawLine(originX, originY, x2, y2);
			
		}
	}

}
