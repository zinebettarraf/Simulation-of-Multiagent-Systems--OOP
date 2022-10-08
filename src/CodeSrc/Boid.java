package CodeSrc;

import java.awt.Color;
import gui.*;

public abstract class Boid {
	private Vector2D position;
	private Vector2D velocity;
	private Vector2D acceleration;
	private Color color;
	private int[] direction;
	private Boids containingFlock;
	
	public Boid (Vector2D position,int angle,Boids containingFlock) {
		
		this.position = position;
		this.containingFlock=containingFlock;
		this.velocity=new Vector2D(0,0);
		this.acceleration=new Vector2D(0,0);
		this.direction=new int[] {0,0};
		this.color=this.containingFlock.getColor();
	}


	public Vector2D screenPosition(Boid boid) {
		/** this function returns the position of a boid on the screen **/
		
		int x = this.position.getVector()[0];
		int y =  this.position.getVector()[1];
		
		int width = this.containingFlock.gui.getPanelWidth();
		int height = this.containingFlock.gui.getPanelHeight();
		
		
		int posx; int posy;
		int quotientx = x / width;
		int quotienty = y / height;
		int factx; int facty;
		if (quotientx % 2 == 0) {
			posx = Math.abs(x % width);
			factx = 1;
		}
		else {
			posx = width - Math.abs(x % width);
			factx = -1;
		}
		if (quotienty % 2 == 0) {
			posy = Math.abs(y % height);
			facty = 1;
		}
		else {
			posy = height -  Math.abs(y % height) ;
			facty = -1;
		}
		this.direction[0] = factx*(this.velocity.getVector()[0]);
		this.direction[1] = facty*this.velocity.getVector()[1];
		return new Vector2D(posx,posy);
		
	}
	public void limitVelocity (int vlim) {
		this.velocity.limitVector(vlim);
	}
	
	public void drawBoid (GUISimulator gui) {
		/*** this function draw a taken boid on the screen**/
		
		Vector2D screenpos=screenPosition(this);
		gui.addGraphicalElement(new Triangle(screenpos.getVector()[0],screenpos.getVector()[1], this.direction,this.containingFlock.color));
}

	public Vector2D perceivedCenter() {
		/**  It computes the centre of masse of  all the other boids, not including itself **/
		
		Vector2D result=new Vector2D(0,0);
		for(Boid boid:this.containingFlock.boids) {
			if(!(boid.equals(this))) {
				result= result.add(boid.getPosition());
			}
			
		}
		return result.divide(this.containingFlock.nbrBoids-1);
	}
	
	public Vector2D perceivedVelocity() {
		/** It computes the average of all the other boids velocities , not including itself **/
		
		Vector2D result=new Vector2D(0,0);
		for(Boid boid:this.containingFlock.boids) {
			if(!(boid.equals(this))) {
				result= result.add(boid.getVelocity());
			}
			
		}
		return result.divide(this.containingFlock.nbrBoids-1);
	}

	// the three standard  rules
	
	public Vector2D rule1 (Boid element) {	
        /** Cohesion an agent moves towards the average position (center of mass) of its neighbors **/
		
		Vector2D perceivedCenter = this.perceivedCenter();
		for (Boid fellow:this.getContainigFlock().getBoids()) {
			if (!(element.equals(fellow))) {
				perceivedCenter=perceivedCenter.add(fellow.getPosition());
			}
		}
		perceivedCenter=perceivedCenter.divide(this.getContainigFlock().getNbrBoids()-1);
		perceivedCenter=perceivedCenter.substract(element.getPosition());
		return perceivedCenter.divide(100);
	}

	public Vector2D rule2(Boid element) {	
		/** Alignment an agent tends to move in the same direction as its neighbors**/
		
		Vector2D result = new Vector2D(0, 0);
		for (Boid fellow : this.getContainigFlock().getBoids()) {
			if(!(element.equals(fellow))) {
				if (element.getPosition().norm(fellow.getPosition()) < this.getContainigFlock().getRadius()) {
					Vector2D vector = element.getPosition().substract(fellow.getPosition());
					result = result.substract (vector);
				}
			}
		}
		
		return result;
		
	}
	

	public Vector2D rule3(Boid element) {
		/** Separation agents too close repel each other, to avoid collisions **/
		
		Vector2D perceivedVelocity = this.perceivedVelocity ();
		for (Boid fellow:this.getContainigFlock().getBoids()) {
			if (!(element.equals(fellow))) {
				 perceivedVelocity= perceivedVelocity.add(fellow.getVelocity());
			}
		}
		perceivedVelocity=perceivedVelocity.divide(this.getContainigFlock().getNbrBoids()-1);
		perceivedVelocity=perceivedVelocity.substract(element.getVelocity());
		return perceivedVelocity.divide(8);
		
	}
	public DoubleVector2D seek (Vector2D target) {
		/** returns the force drawing the boids towards our target*/
		double maxForce = 22;
		Vector2D screenLocation=this.screenPosition(this);
		DoubleVector2D theTarget = new DoubleVector2D (target.getVector()[0], target.getVector()[1]);
		DoubleVector2D thePosition = new DoubleVector2D (screenLocation.getVector()[0], screenLocation.getVector()[1]); // au pire just modify screenLoation to position
		DoubleVector2D theVelocity = new DoubleVector2D (this.velocity.getVector()[0], this.velocity.getVector()[1]);
		DoubleVector2D difference = theTarget.substract(thePosition);
		double norm = difference.norm(difference);
		difference.setX((int) (difference.getVector()[0] / norm));
		difference.setY((int) (difference.getVector()[1] / norm));
		DoubleVector2D Vlim = new DoubleVector2D (this.containingFlock.vlim, this.containingFlock.vlim);
		difference.mult(Vlim);
		DoubleVector2D steer = difference.substract(theVelocity);
		steer.limitVector(maxForce);
		return steer;
	}
    public void moveBoid(Boid boid) {
    	/*** this function move a taken boid according to the three standard rules if we want to change rules we should just override this function **/
    	
		Vector2D v1=new Vector2D(0,0);
		Vector2D v2=new Vector2D(0,0);
		Vector2D v3=new Vector2D(0,0);
		v1=boid.rule1(boid);
		v2=boid.rule2(boid);
		v3=boid.rule3(boid);
		boid.setAcceleration((v1.add(v2)).add(v3));		
		Vector2D velocity = new Vector2D ((boid.getVelocity().add(boid.getAcceleration())).getVector()[0], (boid.getVelocity().add(boid.getAcceleration())).getVector()[1]);
		boid.setVelocity(velocity);
		if (velocity.getVector()[0] >= this.containingFlock.vlim) {
			velocity.setX(this.containingFlock.vlim);
		}
		if (velocity.getVector()[1] >= this.containingFlock.vlim) {
			velocity.setY(this.containingFlock.vlim);
		}
		
		Vector2D nextPosition=boid.getPosition().add(boid.getVelocity());
		boid.setPosition(nextPosition);
    	
    }
	
	// setters and getters
    
	public Vector2D getPosition() {
		return this.position;
	}

	public void setPosition(Vector2D position) {
		this.position = position;
	}

	public Vector2D getVelocity() {
		return this.velocity;
	}

	public void setVelocity(Vector2D velocity) {
		this.velocity = velocity;
	}

	public Vector2D getAcceleration() {
		return this.acceleration;
	}

	public void setAcceleration(Vector2D acceleration) {
		this.acceleration = acceleration;
	}
	public Boids getContainigFlock() {
		return this.containingFlock;
	}

	public void setContainingFlock(Boids containingFlock) {
		this.containingFlock = containingFlock;
	}
	public Boids getContainingFlock() {
		return this.containingFlock;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public Color getColor() {
		return this.color;
	}
	public int[] getDirection() {
		return this.direction;
	}


	public void setDirection(int[] direction) {
		this.direction = direction;
	}


	// test the equality of two boids
	
	public boolean equals(Object o) {
        if( ! (o instanceof Boid)) {
                return false ;
        }
        Boid other = (Boid) o;
        return other.position == this.position && other.velocity== this.velocity &&  other.acceleration== this.acceleration &&  other.containingFlock== this.containingFlock;
    }
	@Override
	public String toString() {
		return "Boid [position=" + position + ", velocity=" + velocity + ", acceleration=" + acceleration + "]";
	}
	



}
