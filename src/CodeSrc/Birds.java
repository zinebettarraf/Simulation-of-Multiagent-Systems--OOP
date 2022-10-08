package CodeSrc;
import java.util.Random;

import gui.GUISimulator;

public class Birds extends Boids {
	
	public Birds(int nbrBoids, GUISimulator gui, int r,int reaction,int vlim) {
		super(nbrBoids,gui,r,reaction,vlim);
		for (int i=0; i<nbrBoids; i++) {
			int x=new Random().nextInt(this.gui.getPanelWidth());
			int y= new Random().nextInt(this.gui.getPanelHeight());
			int angle =new Random().nextInt(360);
			this.boids[i] = new Bird(new Vector2D(x, y),angle,this);
			this.boidsInit[i] = new Bird (new Vector2D(x, y),angle,this);				
			}

		}

}
