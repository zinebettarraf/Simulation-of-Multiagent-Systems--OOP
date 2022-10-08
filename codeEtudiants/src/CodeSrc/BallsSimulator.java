package CodeSrc;
import java.awt.Point;
import gui.*;
import java . awt . Color ;

public class BallsSimulator implements Simulable {
	
	// The Description of all the attributes of the class BallsSimulator
	private int nbBalls;
	private Balls newBalls;
	private GUISimulator gui;
	private EventManager manager;
	
	public BallsSimulator(int nbBalls, GUISimulator window) {
		/** The Constructor of the class BallsSimulator*/
		this.gui  = window;
		this.nbBalls = nbBalls;
		this.manager = new EventManager();
		this.newBalls = new Balls(this.nbBalls, this.gui);
		for (int i = 0; i< this.newBalls.getEffectif(); i++) {
			Point[] listPoints = newBalls.getBallsTab();
			Point newPoint = listPoints[i];
			this.gui.addGraphicalElement(new Oval (newPoint.x, newPoint.y, Color.WHITE, Color.WHITE, 20));
		}
	}
	
	@Override
	public void next() {
		
		/** Passes To the next step of the simulation*/
		this.gui.reset();
		/* draw balls */
        for (int i = 0; i< this.nbBalls; i++) {		
			Point[] listPoints = newBalls.getBallsTab();
			Point newPoint = listPoints[i];
			
			// First We will define the positions of the balls on the screen to make them rebound on the sides of the screen
			int x = newPoint.x; int y = newPoint.y;
			int width = this.gui.getPanelWidth();
			int height = this.gui.getPanelHeight();
			
			
			int posx; int posy; // the positions of the ball within the screen
			int quotientx = x / width;
			int quotienty = y / height;

			if (quotientx % 2 == 0) {
				// When quotientx is even, the ball is evolving towards the right
				posx = Math.abs(x % width);
			}
			else {
				// When quotientx is odd, the ball is evolving towards the left
				posx = width - Math.abs(x % width);
			}
			if (quotienty % 2 == 0) {
				// When quotienty is even, the ball is evolving towards the bottom
				posy = Math.abs(y % height);
			}
			else {
				// When quotienty is odd, the ball is evolving towards the top
				posy = height -  Math.abs(y % height) ;
			}
			
			//Dawing the Balls
			this.gui.addGraphicalElement(new Oval (posx, posy, Color.WHITE, Color.WHITE, 20));
		}
        
		this.manager.addEvent(new EventBalls(this.newBalls,this.manager.currentDate));
		this.manager.next();

		 
		
	}
	
	
	@Override 
	public void restart() {
		/** Resets the whole System to its initial position */
		this.gui.reset();
		newBalls.reInt();
		for (int i = 0; i< this.newBalls.getEffectif(); i++) {
			Point[] listPoints = newBalls.getBallsTab();
			Point newPoint = listPoints[i];
			this.gui.addGraphicalElement(new Oval (newPoint.x, newPoint.y, Color.WHITE, Color.WHITE, 20));
		}
		/** rependre les mêmes evenements passés **/
		this.manager.restart();
	}
	
	public Balls getState() {
		/** Returns a list of all the balls that will be drawn on the screen*/
		return this.newBalls;
	}
	
	public void setGui(GUISimulator gui) {
		/** The setter of the window we will draw on later on*/
		this.gui = gui;
	}
}

