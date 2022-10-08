package CodeSrc;

import java.awt.Color;
import gui.GUISimulator;
import gui.Rectangle;

public class Path {

	private Vector2D corner; // the top corner of the path
	private int length; private int radius;
	private GUISimulator window;
	
	public Path(GUISimulator window, Vector2D corner, int length, int radius) {
		/** Builds the paths the Followers will Follow*/
		this.corner = corner;
		this.length = length;
		this.radius = radius;
		this.window = window;
	}
	
	public void drawPath() {
		/** Draws the Path*/
		int x = this.corner.getVector()[0];
		int y = this.corner.getVector()[1];
		System.out.println ("corner " + x + "  " + y);
		Rectangle rect1 = new Rectangle (x, y, Color.WHITE, Color.LIGHT_GRAY, this.length, this.radius);
		System.out.println("le x est " + rect1.getX());
		this.window.addGraphicalElement(rect1);
 	}
	
	public int getLength() {
		return length;
	}

	public int getRadius() {
		return radius;
	}

	public Vector2D getCorner() {
		return corner;
	}

	
	public boolean isInPath (Vector2D position) {
		/** Checks if a point is in the path or not*/
		int width = this.window.getPanelWidth();
		int height = this.window.getPanelHeight();
		int x = position.getVector()[0];
		int y = position.getVector()[1];
		if (y>= height/5 && y<= (height/5)+this.radius) {
			if (x>= width/5 && x<= 4*width/5) {
				return true;
			}
			else {
				return false;
			}
		}
		else if (y>= (height/5)+this.radius && y<= 4*height/5) {
			if (x>= (4*width/5) - radius && x<= 4*width/5) {
				return true;
			}
			else {
				return false;
			}
		}
		else if (y>= 4*height/5 && y <= (4*height/5)+this.radius) {
			if (x>= width/5 && x<= 4*width/5) {
				return true;
			}
			else {
				return false;
			}
		}
		else {return false;}
		
	}
}