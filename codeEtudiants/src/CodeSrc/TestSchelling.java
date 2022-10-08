package CodeSrc;

import gui.GUISimulator;
import java.awt.*;

public class TestSchelling {
	public static void main(String[] args) {
		GUISimulator gui = new GUISimulator(1000, 1000, Color.BLACK);
		Schelling testing = new Schelling(gui, 1000, 1000, 4, 3);
		gui.setSimulable(testing);
	}
}