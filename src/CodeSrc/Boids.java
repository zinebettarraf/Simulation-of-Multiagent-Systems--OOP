package CodeSrc;
import java.awt.Color;
import java.util.*;
import gui.*;

public abstract class Boids {
	int nbrBoids;
	int radius;
	int reaction;
	int vlim;
	Color color;
	GUISimulator gui;
	Boid[] boids;
	Boid[] boidsInit;
	
	public Boids(int nbrBoids, GUISimulator gui, int r,int reaction,int vlim) {
		this.nbrBoids = nbrBoids;
		this.reaction=reaction;
		this.gui = gui;
		this.radius=r;
		this.vlim=vlim;
		this.boids = new Boid[this.nbrBoids];
		this.boidsInit = new Boid[this.nbrBoids];
		this.color=new Color(new Random().nextInt(0xFFFFFF));
	}

	public GUISimulator getGui() {
		return gui;
	}

	public Vector2D centerOfMass () {
		Vector2D result=new Vector2D(0,0);
		for(Boid boid:this.boids) {
			result.add(boid.getPosition());
		}
		return result.divide(this.nbrBoids);}
	

	public void moveBoids() {
		for(Boid boid:this.boids) {
			boid.moveBoid(boid);
		}

		
	}
	public void drawBoids() {
		for(Boid boid:this.boids) {
			boid.drawBoid(this.gui);
		}
	}
	

	public void setGui(GUISimulator gui) {
		this.gui = gui;
	}
	
	public Boid[] getBoids() {
		return this.boids;
	}
    public int getNbrBoids() {
    	return this.nbrBoids;
    }

	public int getRadius() {
		return radius;
	}
	public Color getColor() {
		return this.color;
	}
}






