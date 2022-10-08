package CodeSrc;

import java.awt.Color;
import java.util.List;

import gui.GUISimulator;

public class SchellingCells extends Cellsb {

	public SchellingCells(int w, int h, int r, int nb, List<Color> c, int k) {
		super(w, h, r, nb, c, k);
	}

	@Override
	public int numberofValidNeighbors(int i, int j) {
		int count = 0;
		if (currentState(i - getRect_size(), j - getRect_size()) != this.pre.get(i + "," +  j)) {count+=1;}
		if (currentState(i - getRect_size(), j) != this.pre.get(i + "," +  j)) {count+=1;} 
		if (currentState(i - getRect_size(), j + getRect_size()) != this.pre.get(i + "," +  j)) {count+=1;}
		
		if (currentState(i, j - getRect_size()) != this.pre.get(i + "," +  j)) {count+=1;}
		if (currentState(i, j + getRect_size()) != this.pre.get(i + "," +  j)) {count+=1;}

		if (currentState(i + getRect_size(), j - getRect_size()) != this.pre.get(i + "," +  j)) {count+=1;}
		if (currentState(i + getRect_size(), j) != this.pre.get(i + "," +  j)) {count+=1;} 
		if (currentState(i + getRect_size(), j + getRect_size()) != this.pre.get(i + "," +  j)) {count+=1;}
		
		return count;
	}

	@Override
	public boolean willChangeState(int i, int j) {
		int numberofValidNeighbors = this.numberofValidNeighbors(i, j);
		if (numberofValidNeighbors > 8 - this.getThreshold()){
			return true ;
	      }
		else {
			return false;
			}
	}

	@Override
	public void changeState(GUISimulator window, Color color, int i, int j) {
		this.fillRectangle(window,color , i, j);
		this.post.put(i + "," + j, getColors().indexOf(color));	
		
	}

}