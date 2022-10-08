package CodeSrc;

import java.util.*;
import gui.*;

public class FoodList {
	
	private int nbParticles;
	private Food[] FoodList;
	GUISimulator window;
	
	public FoodList (int nbParticles, GUISimulator gui) {
		/** Defines a list of Food */
		this.nbParticles = nbParticles;
		this.FoodList = new Food[nbParticles];
		this.window = gui;
		for (int i=0; i<nbParticles ; i++) {
			int posx = new Random().nextInt(gui.getPanelWidth()/2)+70;
			int posy = new Random().nextInt(gui.getPanelHeight()/2)+70;
			FoodList[i] = new Food (posx, posy, this.window, i+1);
		}
	}
	
	public void drawFoodList() {
		/** Draws all the elements of the list of Food*/
		for (int i=0; i<this.nbParticles ; i++) {
			Food element = FoodList[i];
			if (!element.isEaten()) {
				element.drawFood();
			}
		}
	}

	public Food[] getFoodList() {
		/** getter of the list of Food */
		return FoodList;
	}
	
}
