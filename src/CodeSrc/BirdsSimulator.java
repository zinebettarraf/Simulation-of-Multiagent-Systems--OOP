package CodeSrc;
import gui.*;
import java.util.*;



public class BirdsSimulator implements Simulable {
	Boids[] flocks;
	GUISimulator window;
	EventManager manager;
	
	public BirdsSimulator(GUISimulator window, int nbrBoids,int nbrFlock) {
		this.manager = new EventManager();
		this.flocks=new Boids[nbrFlock];
		for(int i=0;i<nbrFlock;i++) {
			this.flocks[i]=new Birds(nbrBoids, window,new Random().nextInt(40)+10,new Random().nextInt(10),new Random().nextInt(60)+30);
		}
		this.window = window;	
	}

	@Override
	public void next() {
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
