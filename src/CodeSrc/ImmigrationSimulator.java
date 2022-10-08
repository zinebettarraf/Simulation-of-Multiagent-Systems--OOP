package CodeSrc;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import gui.*;





public class ImmigrationSimulator implements Simulable {
	
	
	private int number_of_states;
	private int width;
	private int heigth;
	private final int RECT_SIZE = 50;
	private ImmigrationCells cells;
	public  GUISimulator gui;;
	private Random rand = new Random();
	private ImmigrationCells cellsSaver;
	private List<Color> colors = new ArrayList<>();
	private EventManager manager;

	
	
	public ImmigrationSimulator(int w, int h, GUISimulator window, int nb){
		super();
		this.width  = w;
		this.heigth = h;
		this.gui = window;
		this.number_of_states = nb;
		this.manager = new EventManager();
		Color color = new Color(rand.nextInt(0xFFFFFF));
		for (int i = 0; i <number_of_states; i++) {
			int red = (int) Math.round(Math.max(0, color.getRed() - 255 * (double) i/ (double)number_of_states));
		    int green = (int) Math.round(Math.max(0, color.getGreen() - 255 * (double) i/ (double)number_of_states));
		    int blue = (int) Math.round(Math.max(0, color.getBlue() - 255 * (double) i/ (double)number_of_states));
		    int alpha = color.getAlpha();
	        this.colors.add(new Color(red, green, blue, alpha));
	    }	

		this.cells = new ImmigrationCells(width, heigth, RECT_SIZE, number_of_states, colors);
		this.cellsSaver = new ImmigrationCells(width, heigth, RECT_SIZE, number_of_states, colors);
		
		this.cells.initialise();
		for (int i = 0; i < width ; i += RECT_SIZE) {  {
			for (int j = 0; j < heigth ; j += RECT_SIZE) { 
				int randstate = rand.nextInt(number_of_states);
				this.cells.changeState(gui, this.cells.getColors().get(randstate), i, j);
				this.cellsSaver.changeState(gui, this.cells.getColors().get(randstate), i, j);
			  } 
		  }
		this.cells.nextStep(); 
		this.cellsSaver.nextStep();

		 }
	}
	
	
	@Override
	public void next() {
		gui.reset();
		this.manager.addEvent(new EventImmigration(this.cells,this.manager.currentDate,this.gui));
		this.manager.next();

	}


	@Override
	public void restart() {
		gui.reset();
		/** reprendre l'état initial pour chaque cell **/
		cells.copySavedState(cellsSaver);
		for (int i = 0; i < width ; i += RECT_SIZE) {  {
			for (int j = 0; j < heigth ; j += RECT_SIZE) { 
				this.cells.changeState(gui, this.cells.getColors().get(cellsSaver.post.get(i + "," + j)), i, j);
				this.cells.nextStep(); 
				
			  } 
		  }
		}
		/** rependre les mêmes evenements passés **/
		this.manager.restart();
	}

	public int getNumber_of_states() {
		return number_of_states;
	}


}