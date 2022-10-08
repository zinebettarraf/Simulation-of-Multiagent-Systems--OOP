package CodeSrc;
import java.util.Random;

import gui.GUISimulator;

public class Preys extends Boids {
	Boids predators;
	int detectionRadius;
	boolean Flockonrisk;
	
	public Preys(int nbrBoids, GUISimulator gui, int detectionRadius,int r,int reaction,int vlim,Boids predators) {
		super(nbrBoids,gui,r,reaction,vlim);
		this.predators=predators;
		this.detectionRadius=detectionRadius;
		this.Flockonrisk=false;
		for (int i=0; i<nbrBoids; i++) {
			int x=new Random().nextInt(this.gui.getPanelWidth()/5)+50;
			int y= new Random().nextInt(this.gui.getPanelHeight()/5)+50;
			int angle =new Random().nextInt(360);
	        this.boids[i] = new Prey(new Vector2D(x, y),angle,this,this.predators);
	        this.boidsInit[i] = new Prey (new Vector2D(x, y),angle,this,this.predators);
		}
	    this.nbrBoids = nbrBoids;
	}
	
	public void riskOnPreys() {
		for (Boid element:this.boids) {
			Prey prey=(Prey)element;
			Vector2D posprey=prey.screenPosition(prey);
			for (Boid predator:this.predators.boids) {
				Vector2D pospredator=predator.screenPosition(predator);
				if (posprey.norm(pospredator)<this.detectionRadius) {
					this.Flockonrisk=true;
					break;
				}
			}
		}
		
	}
	public boolean getFlockonrisk() {
		return Flockonrisk;
	}

	public void setFlockonrisk(boolean flockonrisk) {
		Flockonrisk = flockonrisk;
	}
	public Boids getPredators() {
		return predators;
	}

	public void setPredators(Boids predators) {
		this.predators = predators;
	}

	  @Override 
	    public void moveBoids() {
	    	this.riskOnPreys();
			Vector2D v1=new Vector2D(0,0);
			Vector2D v2=new Vector2D(0,0);
	    	if(!this.Flockonrisk ) {
	    		for(Boid boid:this.boids) {
	    			v1=boid.rule1(boid);
	    			v2=boid.rule2(boid);
	    			boid.setAcceleration(v1.add(v2));
	    			Vector2D velocity = new Vector2D ((boid.getVelocity().add(boid.getAcceleration())).getVector()[0], (boid.getVelocity().add(boid.getAcceleration())).getVector()[1]);
	    			if (velocity.getVector()[0] >= this.vlim) {
	    				velocity.setX(this.vlim);
	    			}
	    			if (velocity.getVector()[1] >= this.vlim) {
	    				velocity.setY(this.vlim);
	    			}
	    			boid.setVelocity(velocity);
	    			Vector2D nextPosition=boid.getPosition().add(boid.getVelocity());
	    			boid.setPosition(nextPosition);
	    		}	
	    	}
	    	else {
	    		for(Boid boid:this.boids) {
				v1=boid.rule2(boid);
				v2=boid.rule3(boid);
				boid.setAcceleration(v1.add(v2));		
				Vector2D velocity = new Vector2D ((boid.getVelocity().add(boid.getAcceleration())).getVector()[0], (boid.getVelocity().add(boid.getAcceleration())).getVector()[1]);
				boid.setVelocity(velocity);
				if (velocity.getVector()[0] >= boid.getContainingFlock().vlim) {
					velocity.setX(boid.getContainingFlock().vlim);
				}
				if (velocity.getVector()[1] >= boid.getContainingFlock().vlim) {
					velocity.setY(boid.getContainingFlock().vlim);
				}		
				Vector2D nextPosition=boid.getPosition().add(boid.getVelocity());
				boid.setPosition(nextPosition);
			
			}
	    	}

}}
