package CodeSrc;

import java.awt.Color;
import java.util.*;
import gui.*;

public class EventConway extends Event{
	ConwayCells cells;
	GUISimulator gui;
	public EventConway(ConwayCells cells,long date,GUISimulator gui) {
		super(date);
		this.cells=cells;
		this.gui=gui;
	}
     void execute() {

    	Random rand=new Random();
 		Color color = new Color(rand.nextInt(0xFFFFFF));
 		for(int i = 0; i < this.cells.getWidth(); i += cells.getRect_size()) {
 			for(int j = 0; j < this.cells.getHeigth(); j += cells.getRect_size()) {
 				if(cells.willChangeState(i, j)){
 					this.cells.changeState(gui, color, i, j);
 				}	
 				else {
 					this.cells.deadState(gui, Color.BLACK, i, j);
 					}
 			}		
 		}
 		
 		this.cells.nextStep();
     }

}
