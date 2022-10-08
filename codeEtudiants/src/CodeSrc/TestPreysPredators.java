
package CodeSrc;

import java.awt.Color;

import gui.GUISimulator;

public class TestPreysPredators {
	public static void main(String[] args) {
		GUISimulator gui = new GUISimulator (1000 , 1000, Color.BLACK) ;
		PreysPredatorsSimulator sim=new PreysPredatorsSimulator (gui,4,4);
		for(Boids flock:sim.flocks) flock.drawBoids();
		gui.setSimulable(sim);	
	}
}
