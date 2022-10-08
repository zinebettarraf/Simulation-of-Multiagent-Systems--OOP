package CodeSrc;

public class Follower extends Boid{
	public Follower(Vector2D position,int angle,Boids containingFlock) {
		super(position,angle,containingFlock);
	}
	public DoubleVector2D seek (Vector2D target) {
		double maxForce = 22;
		DoubleVector2D theTarget = new DoubleVector2D (target.getVector()[0], target.getVector()[1]);
		Vector2D screenLocation=this.screenPosition(this);
		DoubleVector2D thePosition = new DoubleVector2D (screenLocation.getVector()[0], screenLocation.getVector()[1]); // au pire just modify screenLoation to position
		DoubleVector2D theVelocity = new DoubleVector2D (this.getVelocity().getVector()[0], this.getVelocity().getVector()[1]);
		DoubleVector2D difference = theTarget.substract(thePosition);
		double norm = difference.norm(difference);
		difference.setX((int) (difference.getVector()[0] / norm));
		difference.setY((int) (difference.getVector()[1] / norm));
		DoubleVector2D Vlim = new DoubleVector2D (this.getContainingFlock().vlim, this.getContainingFlock().vlim);
		difference.mult(Vlim);
		DoubleVector2D steer = difference.substract(theVelocity);
		steer.limitVector(maxForce);
		return steer;
	}
	
}
