package CodeSrc;

import  java.awt.Polygon;
import java.awt.Color;
import  java.lang.Math;
import gui.*;

public class Triangle  implements GraphicalElement  {
	
	// The Characteristics of the Triangle
	
	//x and y are the coordinates of the triangle's peak
    int x1;
    int y1;
    int[] direction; // the direction is basically the Vector That determines to which direction the triangle will be pointing
	private Color color;
	public Triangle(int x1,int y1,int[] direction,Color color) {
		
		/** Allows us to define the properties of the Triangle  */
		this.x1=x1;
		this.y1=y1;
		this.direction = new int[2];
		this.direction = direction;
		this.color=color;
	}
	@Override
	public void paint(java.awt.Graphics2D g2d) {
		/** Inherited from GraphicalElement. It Allows us to draw the triangle */
		int dx = this.direction[0]; int dy = this.direction[1];
		double angle;
		if (dx != 0 && dx > 0) { angle =  Math.atan(dy/dx);}
		else if (dx != 0 && dx < 0) {angle = Math.PI + Math.atan(dy/dx); }
		else if (dy > 0){angle = Math.PI;}
		else if (dy < 0){angle = -Math.PI;}
		else {angle = 0;}
		
		double alphademi = Math.toRadians(15);
		int a1 = (int) (30 * Math.cos((Math.PI/2) - angle - alphademi));
		int b1 = (int) (30 * Math.sin((Math.PI/2) - angle - alphademi));
		int a2 = (int)(30 * Math.sin(angle - alphademi));
		int b2 = (int)(30 * Math.cos(angle - alphademi));
		

		
		int x2= this.x1 - b1;
		int y2= this.y1 - a1;
		int x3= this.x1 - b2;
		int y3= this.y1 - a2;
		int[] xpoints=new int[] {this.x1,x2,x3};
		int[] ypoints=new int[] {this.y1,y2,y3};
		Polygon polygon =new Polygon(xpoints, ypoints,3);
		g2d.drawPolygon(polygon);
		g2d.setColor(this.color);
		g2d.fillPolygon(polygon);

	}
}

