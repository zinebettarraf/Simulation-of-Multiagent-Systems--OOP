package CodeSrc;

import java.awt.Color;
import java.util.*;
import gui.*;

public class ShellingEvent extends Event{
	SchellingCells  cells;
	GUISimulator gui;
	LinkedList<String> vacantCells;
	public ShellingEvent(SchellingCells cells,long date,GUISimulator gui,LinkedList<String> vacantCells) {
		super(date);
		this.cells=cells;
		this.gui=gui;
		this.vacantCells=vacantCells;
	}
     void execute() {
    	 for(int i = 0; i < cells.getWidth(); i += cells.getRect_size()) {
 			for(int j = 0; j < cells.getHeigth(); j += cells.getRect_size()) {
 				if(this.cells.pre.get(i + "," + j) != 0) {		
 					
 					if(cells.willChangeState(i, j) && !this.vacantCells.isEmpty()){
 						Random rand=new Random();
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

}
