package CodeSrc;

import gui.GUISimulator;
import java.awt.*;

public class TestConway {
	public static void main(String[] args) {
		GUISimulator gui = new GUISimulator(1000, 1000, Color.BLACK);
		Conway testing = new Conway(1000, 1000, gui);
		gui.setSimulable(testing);
	}
}