package CodeSrc;

import java.awt.Color;
import java.util.Random;

import gui.GUISimulator;

public class Predators extends Boids{
	public Predators(int nbrBoids, GUISimulator gui, int r,int reaction,int vlim) {
		super(nbrBoids,gui,r,reaction,vlim);
		this.color=Color.RED;
		int x=new Random().nextInt(this.gui.getPanelWidth())+100;
		int y= new Random().nextInt(this.gui.getPanelHeight())+100;
		for (int i=0; i<nbrBoids; i++) {
			int angle =new Random().nextInt(360);
			this.boids[i] = new Predator(new Vector2D(x+100*i, y+100*i),angle,this);
			this.boidsInit[i] = new Predator (new Vector2D(x+100*i, y+100*i),angle,this);				
			}

		}
	
	}


