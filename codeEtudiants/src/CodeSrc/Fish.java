package CodeSrc;

import java.awt.Color;
import java.awt.Polygon;

import gui.GraphicalElement;

public class Fish  implements GraphicalElement{
	private Color color;
	public Fish(Color color) {
		this.color=color;
	}
	@Override
	public void paint(java.awt.Graphics2D g2d) {
		
		int[] xpoints=new int[] {200,300,200};
		int[] ypoints=new int[] {200,150,100};
		Polygon polygon =new Polygon(xpoints, ypoints,3);
		int[] xpoints1=new int[] {300,500,700,500,300};
		int[] ypoints1=new int[] {150,250,150,50};
		Polygon polygon1 =new Polygon(xpoints1, ypoints1,4);
		g2d.drawPolygon(polygon);
		g2d.setColor(this.color);
		g2d.fillPolygon(polygon);
		g2d.drawPolygon(polygon1);
		g2d.setColor(this.color);
		g2d.fillPolygon(polygon1);



}}
