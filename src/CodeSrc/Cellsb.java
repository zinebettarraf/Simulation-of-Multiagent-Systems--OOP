package CodeSrc;

import java.awt.Color;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gui.GUISimulator;
import gui.Rectangle;

public abstract class Cellsb {
	
	
	Map<String, Integer> post = new HashMap<String, Integer>();
	Map<String, Integer> pre = new HashMap<String, Integer>();
	
	private  int width;
	private  int heigth;
	private  int rect_size;
	private  int number_of_states;
	private  int threshold;
	
	private List<Color> colors;
	
	

	public Cellsb(int w, int h, int r){
		this.width = w;
		this.heigth = h;
		this.rect_size = r;
	}
	
	public Cellsb(int w, int h, int r, int nb, List<Color> c){
		this.width = w;
		this.heigth = h;
		this.rect_size = r;
		this.number_of_states = nb;
		this.colors = c;
		
	}
	public Cellsb(int w, int h, int r, int nb, List<Color> c, int k){
		this.width = w;
		this.heigth = h;
		this.rect_size = r;
		this.number_of_states = nb;
		this.colors = c;
		this.threshold = k;
		
	}
	
	
	
	
	
	
	
	public abstract int numberofValidNeighbors(int i, int j);
	
	public abstract boolean willChangeState(int i, int j);
	
	public abstract void changeState(GUISimulator window, Color color, int i, int j);
	
	
	/*
	 * Méthode qui permet d'initialiser les listes des cellules
	 */
	
	public void initialise(){
		for(int i = 0; i < width; i+= getRect_size()) {
			for(int j = 0; j < heigth; j += getRect_size()) {
				this.post.put(i + "," +  j, 0);
				this.pre.put(i + "," +  j, 0);
			}
		}	
	}
	
	/*
	 * Méthode qui retourne l'état courrant de la cellule (i,j)
	 * codé par un entier qui indique la couleur;
	 * */
	public Integer currentState(int i, int j) {
		int i0 = (i == -getRect_size() )? width - getRect_size() :((i == width)? 0: i);
		int j0 = (j == -getRect_size() )? heigth - getRect_size() :((j == heigth)? 0: j);
		return this.pre.get(i0 + "," +  j0);
	}
	
	/*
	 * Méthode qui permet de dessiner une grille ; 
	 * */
	
	public void drawGrid(GUISimulator window) {
		for (int i =0; i <heigth; i += getRect_size()) {
			for (int j =0; j <width; j += getRect_size()) {
			window.addGraphicalElement( new Rectangle(i, j, Color.WHITE, Color.BLACK, getRect_size()));
			}
	    }
	}
	
	
	/*
	 * Méthode qui permet de passer au temps t+1 tout en sauvegardant les états des cellules à l'instant t
	 * */
	public void nextStep() {
		for(String s : pre.keySet()) {
			pre.put(s,post.get(s));
		}
	}
	

	
	
	
	/*
	 * Méthode qui permet de remplir une rectangle (i;j) avec une couleur donnée
	 * */
	public void fillRectangle(GUISimulator window, Color color, int i, int j ) {
		window.addGraphicalElement(new Rectangle(i, j, Color.WHITE, color, this.getRect_size()));
	}
	
	public void deadState(GUISimulator window, Color color, int i, int j) {
		this.fillRectangle(window, color, i, j);
		this.post.put(i + "," + j, 0);		
		
	}
	
	/*
	 *Permet de copier le contenur d une cellule à l autre
	 * */
	public void copySavedState(Cellsb c) {
		for(String s : c.pre.keySet()) {
			this.pre.put(s,c.pre.get(s));
		}
		for(String s : c.post.keySet()) {
			this.post.put(s,c.post.get(s));
		}
	}

	public int getRect_size() {
		return rect_size;
	}

	public int getNumber_of_states() {
		return number_of_states;
	}

	public List<Color> getColors() {
		return colors;
	}

	public int getThreshold() {
		return threshold;
	}
	public int getWidth() {
		return width;
	}

	public int getHeigth() {
		return heigth;
	}




	
}