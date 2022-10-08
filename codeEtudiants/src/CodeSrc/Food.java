package CodeSrc;

import java.awt.Color;
import gui.*;

public class Food {

	private static final boolean False = false;
	private Color color = new Color(240,235,210);
	private int posx; private int posy;
	private boolean eaten;
	GUISimulator window;
	
	
	public Food(int x, int y, GUISimulator gui, int m) {
		/** Builds the elements to which the HungryBirds will be attracted*/
		this.posx = x;
		this.posy = y;
		this.window = gui; 
		this.eaten = False;
	}
	
	public boolean isEaten() {
		/** Determines whether the food had been eaten or not - We didn't really use it inn this implementation. But it could be used to make the Food disappear*/
		return eaten;
	}
	public void setEaten(boolean eaten) {
		/** Sets the value of the boolean Eaten */
		this.eaten = eaten;
	}
	
	public void drawFood () {
		/** Draws this particle of food*/ 
		Oval particle = new Oval (posx, posy, this.color, this.color, 40);
		this.window.addGraphicalElement(particle);
	}

	public int getPosx() {
		/** Getter of the position x of the food*/
		return posx;
	}

	public int getPosy() {
		/** Getter of the position y of the food*/
		return posy;
	}
	
}