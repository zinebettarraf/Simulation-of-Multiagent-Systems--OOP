package CodeSrc;
import gui.GUISimulator;

public class Prey extends Boid  {
	Boids predators;
	int died;
	public Prey(Vector2D position,int angle,Boids containingFlock,Boids predators) {
		super(position,angle,containingFlock);
		this.predators=predators;
		this.died=0;
	}

	public Vector2D rule3 (Boid element) {
		// go away from the percieved center negates the rule 1
		Vector2D perceivedCenter = this.perceivedCenter();
		for (Boid fellow:this.getContainigFlock().getBoids()) {
			if (!(element.equals(fellow))) {
				perceivedCenter=perceivedCenter.add(fellow.getPosition());
			}
		}
		perceivedCenter=perceivedCenter.divide(this.getContainigFlock().getNbrBoids()-1);
		perceivedCenter=perceivedCenter.substract(element.getPosition());
		return perceivedCenter.divide(-100);
	} 
	public Vector2D rule4(Boid element) {
//		negating the second rule (avoiding nearby objects) will simply cause the boids to actively run into each other
		Vector2D result = new Vector2D(0, 0);
		for (Boid fellow : this.getContainigFlock().getBoids()) {
			if(!(element.equals(fellow))) {
				if (element.getPosition().norm(fellow.getPosition()) < this.getContainigFlock().getRadius()) {
					Vector2D vector = element.getPosition().substract(fellow.getPosition());
					result = result.substract (vector);
				}
			}
		}
		
		return result.mult_nbr(-1);
		
	}
	
	public void isDie() {
		for (Boid predator:this.predators.getBoids()) {	
			Vector2D pospredator=predator.screenPosition(predator);
			Vector2D posboid=this.screenPosition(this);
			if(this.died==0 && pospredator.norm(posboid)<60) {
				this.died=1;			
			}
			}
		}


	@Override 
	public void moveBoid(Boid boid) {
		Prey prey=(Prey)boid;
		Preys preys =(Preys)prey.getContainigFlock();
		Vector2D v1=new Vector2D(0,0);
		Vector2D v2=new Vector2D(0,0);
		Vector2D v3=new Vector2D(0,0);

		/// Flocking 
		if (!preys.getFlockonrisk()){
			System.out.println("flocking");
			v1=boid.rule1(prey);
			v2=boid.rule2(prey);
			v3=boid.rule3(prey);
			prey.setAcceleration((v1.add(v2)).add(v3));		
			Vector2D velocity = new Vector2D ((prey.getVelocity().add(prey.getAcceleration())).getVector()[0], (prey.getVelocity().add(prey.getAcceleration())).getVector()[1]);
			prey.setVelocity(velocity);
			if (velocity.getVector()[0] >= preys.vlim) {
				velocity.setX(preys.vlim);
			}
			if (velocity.getVector()[1] >= preys.vlim) {
				velocity.setY(preys.vlim);
			}
			
			Vector2D nextPosition=prey.getPosition().add(prey.getVelocity());
//			System.out.println("there is no risk");
			prey.setPosition(nextPosition);		
		}
		// anti Flocking
		else {
//			System.out.println("there is a risk");
			v1=boid.rule2(boid);
			v2=boid.rule3(boid);
			boid.setAcceleration(v1.add(v2));		
			Vector2D velocity = new Vector2D ((boid.getVelocity().add(boid.getAcceleration())).getVector()[0], (boid.getVelocity().add(boid.getAcceleration())).getVector()[1]);
			boid.setVelocity(velocity);
			if (velocity.getVector()[0] >= prey.getContainingFlock().vlim) {
				velocity.setX(prey.getContainingFlock().vlim);
			}
			if (velocity.getVector()[1] >= prey.getContainingFlock().vlim) {
				velocity.setY(prey.getContainingFlock().vlim);
			}
			
			Vector2D nextPosition=boid.getPosition().add(boid.getVelocity());
			boid.setPosition(nextPosition);
			
		}
			
		}
		

	@Override
	public void drawBoid (GUISimulator gui) {
		this.isDie();
		if (this.died==0) {
			// ne dessiner que les boids vivants
			int x = this.getPosition().getVector()[0];
			int y =  this.getPosition().getVector()[1];		
			int width = this.getContainingFlock().gui.getPanelWidth();
			int height = this.getContainingFlock().gui.getPanelHeight();
			
			
			int posx; int posy;
			int quotientx = x / width;
			int quotienty = y / height;
			if (quotientx % 2 == 0) {
				posx = Math.abs(x % width);
			}
			else {
				posx = width - Math.abs(x % width);
			}
			if (quotienty % 2 == 0) {
				posy = Math.abs(y % height);
			}
			else {
				posy = height -  Math.abs(y % height) ;
			}
			gui.addGraphicalElement(new Triangle(posx,posy, this.getDirection(),this.getColor()));			
		}
	
	}	
	public void setEtatDied(int died) {
		this.died=died;
	}
		
	
}
