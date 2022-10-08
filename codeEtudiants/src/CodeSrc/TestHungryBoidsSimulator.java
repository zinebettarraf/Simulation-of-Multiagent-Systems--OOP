package CodeSrc;

import java.awt.Color;
import gui.GUISimulator;


public class TestHungryBoidsSimulator {
	public static void main(String[] args) {
		GUISimulator gui = new GUISimulator (1000 , 1000, Color.BLACK) ;
		SimulatorHungryBoids sim=new SimulatorHungryBoids(gui, 6, 3); // here 6 is the nb of elements per flock, and 3 is the number of flocks
		for(Boids flock:sim.flocks) flock.drawBoids();
		gui.setSimulable(sim);	
	}

}
