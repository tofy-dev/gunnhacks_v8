import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public abstract class Movable{
	
	private int x;
	private int y;
	private int dx;
	private int dy;
	
	private JPanel panel;
	//private Graphics g;
	
	public Movable(int tx, int ty, int tdx, int tdy, JPanel panel) {
		x = tx;
		y = ty;
		dx = tdx;
		dy = tdy;
		this.panel = panel;
		//g = panel.getGraphics();
	}
	
	public void setX(int z) {
		x = z;
	}
	
	public int getX(){
		return x;
	}
	
	public void setY(int z){
		y = z;
	}
	
	public int getY(){
		return y;
	}
	
	public void setDx(int z){
		dx = z;
	}
	
	public int getDx(){
		return dx;
	}
	
	public void setDy(int z) {
		dy = z;
	}

	public int getDy(){
		return dy;
	}
	
	public JPanel getPanel() {
		return panel;
	}

	public abstract int getType();
	
	public void draw(Graphics g) {
		g.drawImage(new ImageIcon("sprites/asteroid.png").getImage(), x, y, null);
	}
	
	public void notifyIfHit() {
		// implement later
	}
	
	public void remove() {
		// implement later
	}
	
	public void move() {
		x += dx;
		y += dy;
	}
	
	
	
}