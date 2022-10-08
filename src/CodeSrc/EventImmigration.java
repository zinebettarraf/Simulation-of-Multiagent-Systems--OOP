package CodeSrc;

//import java.awt.Color;
//import java.util.*;
import gui.*;

public class EventImmigration extends Event{
	ImmigrationCells cells;
	GUISimulator gui;
	public EventImmigration(ImmigrationCells cells,long date,GUISimulator gui) {
		super(date);
		this.cells=cells;
		this.gui=gui;
	}
     void execute() {
 		for(int i = 0; i < this.cells.getWidth(); i += cells.getRect_size()) {
			for(int j = 0; j < this.cells.getHeigth(); j += cells.getRect_size()) {
				if(cells.willChangeState(i, j)){
					cells.changeState(gui, this.cells.getColors().get((cells.pre.get(i + "," + j) + 1) % this.cells.getNumber_of_states()), i, j);
				}
				else {
					cells.changeState(gui, this.cells.getColors().get(cells.pre.get(i + "," + j)), i, j);
				}
			}		
		}
		
		this.cells.nextStep();
     }

}
