package CodeSrc;

import java.util.Random;

import gui.GUISimulator;

public class HungryBoids extends Boids {
	private Food birdsFood;
	public HungryBoids(int nbrBoids, GUISimulator gui, int r,int reaction,int vlim) {
		super(nbrBoids, gui, r,reaction, vlim);
	}
	public void setBirdsFood(Food birdsFood) {
		this.birdsFood = birdsFood;
		for (int i=0; i<nbrBoids; i++) {
			int x=new Random().nextInt(this.gui.getPanelWidth());
			int y= new Random().nextInt(this.gui.getPanelHeight());
			int angle =new Random().nextInt(360);
			this.boids[i] = new HungryBoid(new Vector2D(x, y),angle,this);
			this.boidsInit[i] = new HungryBoid (new Vector2D(x, y),angle,this);				
			}

		}
	
	@Override
	public void moveBoids() {
		/** Moves The birds AS A FLOCK towards our TARGET */
		Vector2D v1=new Vector2D(0,0);
		Vector2D v2=new Vector2D(0,0);
		Vector2D v3=new Vector2D(0,0);
		
		Vector2D target = new Vector2D (this.birdsFood.getPosx(), this.birdsFood.getPosy());
		
		for(Boid boid:this.boids) {
			
			DoubleVector2D steer = boid.seek(target);
			Vector2D vectSteer = new Vector2D ((int) steer.getVector()[0], (int) steer.getVector()[1]);
			v1=boid.rule1(boid);
			v2=boid.rule2(boid);
			v3=boid.rule3(boid);
			boid.setAcceleration((v1.add(v2)).add(v3).add(vectSteer)); // the accelaration is the sum of all the forces applied
			Vector2D velocity = new Vector2D ((boid.getVelocity().add(boid.getAcceleration())).getVector()[0], (boid.getVelocity().add(boid.getAcceleration())).getVector()[1]);
			boid.setVelocity(velocity);
			boid.limitVelocity(this.vlim);
			Vector2D nextPosition=boid.getPosition().add(boid.getVelocity());
			boid.setPosition(nextPosition);
		}
		
	}
	@Override
    public void drawBoids() {
		
		for(Boid boid:this.boids) {
			boid.drawBoid(this.gui);
		}
		
		this.birdsFood.drawFood();
	}
}
