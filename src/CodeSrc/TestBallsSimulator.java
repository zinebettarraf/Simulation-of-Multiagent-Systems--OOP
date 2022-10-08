package CodeSrc;
import gui . GUISimulator ;
import java . awt . Color ;



public class TestBallsSimulator {
	
	public static void main ( String [] args ) {
		/** Construction of the Simulation of 20 random balls just moving around the screen with a shift of (10, 10)*/
		GUISimulator gui = new GUISimulator (1000 , 1000 , Color.DARK_GRAY ) ;
		BallsSimulator newSimulator = new BallsSimulator (20, gui);
		newSimulator.setGui (gui);
		gui . setSimulable (newSimulator);
		
	}
}
