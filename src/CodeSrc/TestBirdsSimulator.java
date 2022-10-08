package CodeSrc;

import java.awt.Color;

import gui.GUISimulator;

public class TestBirdsSimulator {
	public static void main(String[] args) {
		GUISimulator gui = new GUISimulator (1000 , 1000, Color.BLACK) ;
		BirdsSimulator sim=new BirdsSimulator(gui,4,2);
		for(Boids flock:sim.flocks) flock.drawBoids();
		gui.setSimulable(sim);	
	}

}
