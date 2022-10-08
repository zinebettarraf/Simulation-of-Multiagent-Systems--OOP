package CodeSrc;
import gui.*;
import java.util.*;

public class SimulatorHungryBoids  implements Simulable{
	HungryBoids[] flocks;
	GUISimulator window;
	EventManager manager;
	public SimulatorHungryBoids(GUISimulator window, int nbrBoids,int nbrFlock) {
		this.manager = new EventManager();
		this.window = window;
		this.flocks=new HungryBoids[nbrFlock];
		FoodList aFoodList = new FoodList(nbrFlock, this.window);
		for(int i=0;i<nbrFlock;i++) {
			HungryBoids newBoids = new HungryBoids(nbrBoids, window,new Random().nextInt(40)+10,new Random().nextInt(10), 30);
			newBoids.setBirdsFood(aFoodList.getFoodList()[i]);
			this.flocks[i]= newBoids;
		}
		
	}
	@Override
	public void next() {
		/** Passes to the next state of the system*/
		this.window.reset();
		
		for(Boids flock:this.flocks) {
			this.manager.addEvent(new EventFlock(flock,this.manager.currentDate+flock.reaction));;
			flock.drawBoids();
		} 
         this.manager.next();
	}
	@Override
	public void restart() {
		/// reprendre les positions initiales pour chaque boid 
		
		for(Boids flockparcours:this.flocks) {
			Boid[] flock=flockparcours.boids;
			Boid[] flockInit=flockparcours.boidsInit;
			// parcours des boidsInit pour chaque boids
			for(int j=0;j<flock.length;j++) {
				flock[j].setPosition(flockInit[j].getPosition());
				flock[j].setVelocity(flockInit[j].getVelocity());
			}
			flockparcours.getGui().reset();
		}
		
		for(Boids flock:this.flocks) {
			flock.drawBoids();
		}
		/** rependre les mêmes evenements passés **/
		this.manager.restart();
	}
	
}
