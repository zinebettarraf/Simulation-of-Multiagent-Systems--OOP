package CodeSrc;

import java.util.Random;

import gui.GUISimulator;

public class Followers extends Boids {
	private Path birdsPath;
	public Followers (int nbrBoids, GUISimulator gui, int r,int reaction,int vlim) {
		super(nbrBoids,gui,r,reaction,vlim);
		for (int i=0; i<nbrBoids; i++) {
			int x=new Random().nextInt(this.gui.getPanelWidth());
			int y= new Random().nextInt(this.gui.getPanelHeight());
			int angle =new Random().nextInt(360);
			this.boids[i] = new Follower(new Vector2D(x, y),angle,this);
			this.boidsInit[i] = new Follower(new Vector2D(x, y),angle,this);				
			}
	}
	public Path getBirdsPath() {
		return birdsPath;
	}
	public void setBirdsPath(Path birdsPath) {
		this.birdsPath = birdsPath;
	}
   @Override 
   public void moveBoids() {
		
		Vector2D v1=new Vector2D(0,0);
		Vector2D v2=new Vector2D(0,0);
		Vector2D v3=new Vector2D(0,0);
		
		for(Boid boid:this.boids) {
			//int maxForce = 6;
			
			v1=boid.rule1(boid);
			v2=boid.rule2(boid);
			v3=boid.rule3(boid);
			
			boid.setAcceleration((v1.add(v2)).add(v3));
			
			Vector2D velocity = boid.getVelocity();
			Path path = this.birdsPath;
			int abscisse = path.getCorner().getVector()[1] + (path.getRadius() / 2);
			Vector2D target = new Vector2D (boid.getPosition().getVector()[0], abscisse);
			Vector2D ScreenLocation=boid.screenPosition(boid);
			double distance =  Math.abs(ScreenLocation.getVector()[1]  - abscisse );  
			Vector2D vectSteer;
			if (distance>this.birdsPath.getRadius()) {
				DoubleVector2D steer = boid.seek(target);
				vectSteer = new Vector2D ((int) steer.getVector()[0], (int) steer.getVector()[1]);
			}
			else {vectSteer = new Vector2D (0, 0);}
			boid.setAcceleration((v1.add(v2)).add(v3).add(vectSteer));
			velocity = velocity.add(boid.getAcceleration());
			boid.setVelocity(velocity);
			boid.limitVelocity(this.vlim);
			Vector2D nextPosition=boid.getPosition().add(boid.getVelocity());
			boid.setPosition(nextPosition);

		}	
	}

}
