package CodeSrc;
import gui.*;
import java.util.*;



public class PreysPredatorsSimulator implements Simulable {
	Boids[] flocks;
	GUISimulator window;
	EventManager manager;
	
	public PreysPredatorsSimulator(GUISimulator window, int nbrBoids,int nbrFlock) {
		this.manager = new EventManager();
		this.flocks=new Boids[nbrFlock];
		// predators
		this.flocks[0]=new Predators(3, window,new Random().nextInt(30)+10,new Random().nextInt(20)+10,15);
		for(int i=1;i<nbrFlock;i++) {
			// preys
			this.flocks[i]=new Preys(5,window,400,new Random().nextInt(30)+5,0,20,this.flocks[0]);
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
		/// faut traiter les predateurs séparement car pour les proies il faut restart leurs etas tous comme non died
		
		// predators
		
		Boid[] predators=flocks[0].boids;
		Boid[] predatorsInit=flocks[0].boidsInit;
		for(int j=0;j<predators.length;j++) {
			predators[j].setPosition(predatorsInit[j].getPosition());
			predators[j].setVelocity(predatorsInit[j].getVelocity());
		}
		flocks[0].getGui().reset();
	
		/// cas des proies
		
		for(int i=1;i<this.flocks.length;i++) {
			Boid[] flock=flocks[i].boids;
			Boid[] flockInit=flocks[i].boidsInit;
			for(int j=0;j<flock.length;j++) {
				flock[j].setPosition(flockInit[j].getPosition());
				flock[j].setVelocity(flockInit[j].getVelocity());
				Prey prey=(Prey)flock[j];
				prey.setEtatDied(0);
			}
			flocks[i].getGui().reset();
		}
		
		for(Boids flock:this.flocks) {
			flock.drawBoids();
		}
		// rependre les memes evenement passés
		this.manager.restart();

	}

}
