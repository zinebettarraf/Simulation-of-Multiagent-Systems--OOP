package CodeSrc;
import java.awt.Color;
import java.util.Random;

import gui.GUISimulator;
import gui.Simulable;

public class Conway implements Simulable{
	
	private int width;
	private int heigth;
	
	private final int RECT_SIZE = 50;
	private final int COMPLEXITY = 15;
	private final int RAND_LVL = 6; 
	public  GUISimulator gui;
	private Random rand = new Random();
	private ConwayCells cells;
	private ConwayCells cellsSaver;
	private EventManager manager;

	

	
	public Conway(int w, int h, GUISimulator window){
		super();
		this.gui = window;
		this.heigth = h;
		this.width = w;
		this.manager = new EventManager();
		this.cells = new ConwayCells(width, heigth, RECT_SIZE);
		this.cellsSaver = new ConwayCells(width, heigth, RECT_SIZE);
		this.cells.initialise();
		this.cellsSaver.initialise();
		this.cells.drawGrid(gui);
		  for (int i = 0; i < COMPLEXITY ; i++) {
			int devx = rand.nextInt(RAND_LVL) - RAND_LVL/2;
			int devy = rand.nextInt(RAND_LVL) - RAND_LVL/2;
			int i0 = (width/2) + devx * RECT_SIZE ;
			int j0 = (heigth/2) + devy * RECT_SIZE ;
			if( cells.post.get(i0 + "," +  j0) == 0) {
				this.cellsSaver.changeState(gui, Color.BLUE, i0, j0);
				this.cells.changeState(gui, Color.BLUE, i0, j0);
			}
		  }
		this.cells.nextStep();
		this.cellsSaver.nextStep();
		  
	}
	@Override
	public void next() {

		gui.reset();
   	    this.cells.drawGrid(gui);
		this.manager.addEvent(new EventConway(this.cells,this.manager.currentDate,this.gui));
		this.manager.next();
		
	}
			

	@Override
	public void restart() {
		gui.reset();
		/** reprendre les meme etat pur chaque cell*/
		this.cells.drawGrid(gui);
		cells.copySavedState(cellsSaver);
		for(int i = 0; i < width; i += RECT_SIZE) {
			for(int j = 0; j < heigth; j += RECT_SIZE) {
				if(cells.post.get(i + "," +  j) == 1){
					this.cells.changeState(gui, Color.BLUE, i, j);
				}
				}

			}
		/** rependre les mêmes evenements passés **/
		this.manager.restart();
    }
	


}