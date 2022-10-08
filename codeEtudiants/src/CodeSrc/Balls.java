package CodeSrc;
import java.awt.Point;
import gui.*;
import java.util.*;

public class Balls {
	
	// The Description of all the attributes of the class Balls
	private int effectif;
	private Point[] ballsTab;  // An array containing all the balls of the group
	private Point[] ballsTabInitial;  // An array saving the initial positions of the balls
	private GUISimulator gui;
	
	public Balls(int number, GUISimulator gui) {
		/** The Constructor of the class Balls*/
		this.effectif = number;
		this.ballsTab = new Point[effectif];
		this.ballsTabInitial = new Point[effectif];
		this.gui = gui;
		for (int i=0; i< effectif; i++) {
			//We initialize the Balls at random points within  the given window
			int width = this.gui.getPanelWidth(); int height = this.gui.getPanelHeight(); 
			int x = new Random().nextInt(width)+ 50;
			int y = new Random().nextInt(height)+ 50;
			this.ballsTab[i] = new Point(x, y);
			this.ballsTabInitial[i] = new Point(x, y);
		}
		
	}
	
	
	
	public int getEffectif () {
		/** The getter of the number of balls within the group*/
		return this.effectif;
	}
	
	public Point[] getBallsTab() {
		/** The getter of the array containing all the balls within the group*/
		return this.ballsTab;
	}
	
	public void setGui (GUISimulator gui) {
		/** The setter of the window we will draw on later on*/
		this.gui = gui;
	}
	
	public void translate(int dx, int dy) {
		/** Translates All of the Balls of the group with the distances dx and dy respectively*/
		
		for (int i=0; i< effectif; i++) {
			this.ballsTab[i].translate(dx, dy);
		}
	}
	
	public void reInt () {
		/** Resets the System to its initial positions*/
		for (int i=0; i< effectif; i++) {
			this.ballsTab[i] = new Point(this.ballsTabInitial[i].x, this.ballsTabInitial[i].y);
		}
	}
	
	public String toString() {
		/** Defines a way of printing the values of the system of the balls in a presentable way*/
		String Result;
		Result = "les points et leurs positions : ";
		for (int i=0; i< effectif; i++) {
			Result += this.ballsTab[i].toString();
		}
		return Result;
	}
	

}

