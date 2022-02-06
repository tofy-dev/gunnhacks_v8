import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public abstract class Movable{
	
	private int x;
	private int y;
	private int dx;
	private int dy;
	private int width;
	private int height;
	private Rectangle hitBox;
	
	private JPanel panel;
	private ArrayList<Asteroids> asteroids;
	//private Graphics g;
	
	public Movable(int tx, int ty, int tdx, int tdy, int twidth, int theight, JPanel panel, ArrayList<Asteroids> asteroids) {
		x = tx;
		y = ty;
		dx = tdx;
		dy = tdy;
		width = twidth;
		height = theight;
		this.panel = panel;
		this.asteroids = asteroids;
		hitBox = new Rectangle(x, y, width, height);
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
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	
	public JPanel getPanel() {
		return panel;
	}

	public abstract int getType();
	
	public Rectangle getHitBox() {
		return hitBox;
	}
	
	public void draw(Graphics g) {
		g.drawImage(new ImageIcon("sprites/asteroid.png").getImage(), x, y, null);
	}
	
	public abstract void notifyIfHit(Movable hitter);
	
	public void remove() {
		// implement later
	}
	
	public void move() {
		x += dx;
		y += dy;
		if(getX() < -getWidth()/4) {
			setX(getPanel().getWidth() + getWidth()/4);
		}
		if(getY() < -getHeight()/4) {
			setY(getPanel().getHeight() + getHeight()/4);
		}
		if(getX() > getPanel().getWidth() + getWidth()/4) {
			setX( -getWidth()/4);
		}
		if(getY() > getPanel().getHeight() + getHeight()/4) {
			setY( -getHeight()/4);
		}
		
		
		hitBox.setLocation(x, y);
		for(int i = 0; i<asteroids.size(); i++) {
			asteroids.get(i).notifyIfHit(this);
		}
	}
	
	
	
}