package CodeSrc;

import gui.GUISimulator;
import java.awt.*;

public class TestImmigration {
	public static void main(String[] args) {
		GUISimulator gui = new GUISimulator(1000, 1000, Color.BLACK);
		ImmigrationSimulator testing = new ImmigrationSimulator(1000, 1000, gui, 4);
		gui.setSimulable(testing);
	}
}