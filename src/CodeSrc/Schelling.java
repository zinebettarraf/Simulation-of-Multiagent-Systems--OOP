package CodeSrc;
import gui.*;

import java.awt.Color;
import java.util.*;



public class Schelling implements Simulable{
	
	private GUISimulator gui;
	private int width;
	private int heigth;
	private int number_of_states;
	
	
	private final int RECT_SIZE = 25;
	
	private SchellingCells cells;
	private SchellingCells cellsSaver;
	private Random rand = new Random();
	private LinkedList<String> vacantCells;
	private List<Color> colors = new ArrayList<>();
	private LinkedList<String> vacantCellsSaver;
	
    public Schelling(GUISimulator window,int w, int h, int nb, int k) {
		super();
		this.width  = w;
		this.heigth = h;
		this.vacantCells = new LinkedList<>();
		this.vacantCellsSaver = new LinkedList<>();
		this.gui = window;
		this.colors.add(Color.WHITE);
		this.number_of_states = nb;
		for (int i = 0; i <number_of_states; i++) {
	        this.colors.add(new Color(rand.nextInt(0xFFFFFF)));
	    }	
		this.cells = new SchellingCells(width, heigth, RECT_SIZE, number_of_states, colors, k);
		this.cellsSaver = new SchellingCells(width, heigth, RECT_SIZE, number_of_states, colors, k);
		
		this.cells.initialise();
		for (int i = 0; i < width ; i += RECT_SIZE) {  {
			for (int j = 0; j < heigth ; j += RECT_SIZE) { 
				int randstate = rand.nextInt(number_of_states)+1;
				this.cells.changeState(gui, this.cells.getColors().get(randstate), i, j);
				this.cellsSaver.changeState(gui, this.cells.getColors().get(randstate), i, j);
			  } 
		  }}
		
		for (int i1 = 0; i1 < width ; i1 += RECT_SIZE) {  {
			for (int j1 = 0; j1 < heigth ; j1 += RECT_SIZE) { 
				int a = rand.nextInt(4)+1;
				if ( a == 1) {
					this.vacantCells.add(i1 + ","+ j1);
					this.vacantCellsSaver.add(i1 + ","+ j1);
					
					this.cellsSaver.changeState(gui, Color.WHITE, i1, j1);
					this.cells.changeState(gui, Color.WHITE, i1, j1);
				}
			  } 
		}}
		this.cells.nextStep(); 
		this.cellsSaver.nextStep();
		
    }

    
	@Override
	public void next() {
		
		gui.reset();
		for(int i = 0; i < width; i += RECT_SIZE) {
			for(int j = 0; j < heigth; j += RECT_SIZE) {
				if(this.cells.pre.get(i + "," + j) != 0) {		
					
					if(cells.willChangeState(i, j) && !vacantCells.isEmpty()){

						int rmd = rand.nextInt(vacantCells.size());
						String vacant = this.vacantCells.get(rmd);
						this.vacantCells.remove(vacant);
						
						String[] parts = vacant.split(",");
						int i0 = Integer.valueOf(parts[0]);
						int j0 = Integer.valueOf(parts[1]);
						
						cells.changeState(gui, this.cells.getColors().get(cells.pre.get(i + "," + j)), i0, j0);	
						
						this.vacantCells.add(i+","+j);
						cells.changeState(gui, Color.WHITE, i, j);}
					
					
				
					else {cells.changeState(gui, this.cells.getColors().get(cells.pre.get(i + "," + j)), i, j);}
			    }
			}
			
			
		}
		
		Iterator<String>  itr = vacantCells.iterator();
		while(itr.hasNext()) {
			String vacantcell = itr.next();
			String[] parts = vacantcell.split(",");
			int vacanti = Integer.valueOf(parts[0]);
			int vacantj = Integer.valueOf(parts[1]);
			cells.changeState(gui, Color.WHITE, vacanti, vacantj);	
			
		}
			
	
			 
		this.cells.nextStep();	 
		}




	@Override
	public void restart() {
		gui.reset();
		cells.copySavedState(cellsSaver);
		this.cells.nextStep(); 
		this.vacantCells = new LinkedList<>();
		Iterator<String>  iterator = vacantCellsSaver.iterator();
		while(iterator.hasNext()) {vacantCells.add(iterator.next());}
		
		for (int i = 0; i < width ; i += RECT_SIZE) {  {
			for (int j = 0; j < heigth ; j += RECT_SIZE) { 
				this.cells.changeState(gui, this.cells.getColors().get(cellsSaver.post.get(i + "," + j)), i, j);
			  } 
		  }
		}
	}
}