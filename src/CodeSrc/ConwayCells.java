package CodeSrc;


import java.awt.Color;

import gui.GUISimulator;

public class ConwayCells extends Cellsb{
	
	

	public ConwayCells(int w, int h, int r){
		super(w, h, r);	
	}

	
	@Override
	public int numberofValidNeighbors(int i, int j) {
		int count = 0;
		if (currentState(i - getRect_size(), j - getRect_size()) == 1) {count+=1;}
		if (currentState(i - getRect_size(), j) == 1) {count+=1;} 
		if (currentState(i - getRect_size(), j + getRect_size()) == 1) {count+=1;}
		
		if (currentState(i, j - getRect_size()) == 1) {count+=1;}
		if (currentState(i, j + getRect_size()) == 1) {count+=1;}

		if (currentState(i + getRect_size(), j - getRect_size()) == 1) {count+=1;}
		if (currentState(i + getRect_size(), j) == 1) {count+=1;} 
		if (currentState(i + getRect_size(), j + getRect_size()) == 1) {count+=1;}
		return count;
	}
	

	@Override
	public boolean willChangeState(int i, int j) {
		int numberofValidNeighbors = this.numberofValidNeighbors(i, j);
		if (currentState(i , j) == 1) {
			if ((numberofValidNeighbors == 3) || (numberofValidNeighbors == 2)) {
				return true;
		      }
			else {
				return false;
				}
		}
		else {
			if (numberofValidNeighbors == 3){
				return true;
		      }
			else {
				return false;
				}
		
		}
	}

	@Override
	public void changeState(GUISimulator window, Color color, int i, int j) {
		this.fillRectangle(window, color, i, j);
		this.post.put(i + "," + j, 1);		
	}
	
}