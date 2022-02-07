import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

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
	//private Graphics g;
	
	public Movable(int tx, int ty, int tdx, int tdy, int twidth, int theight, JPanel panel) {
		x = tx;
		y = ty;
		dx = tdx;
		dy = tdy;
		width = twidth;
		height = theight;
		this.panel = panel;
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
	
	public abstract void draw(Graphics g);
	
	public abstract void remove();
	
	// (0, 0) is upper left corner
	public void move() {
		x += dx;
		y += dy;
		if(getX() < -getWidth()) {
			setX(getPanel().getWidth() + getWidth()/4);
			//System.out.println("1");
		}
		if(getY() < -getHeight()) {
			setY(getPanel().getHeight() + getHeight()/4);
			//System.out.println("2");
		}
		if(getX() > getPanel().getWidth() + getWidth()/4) {
			setX( -getWidth()/4);
			//System.out.println("3");
		}
		if(getY() > getPanel().getHeight() + getHeight()/4) {
			setY( -getHeight()/4);
			//System.out.println("4");
		}
		
		
		hitBox.setLocation(x, y);
		ArrayList<Asteroids> asteroids = Driver.getAsteroids();
		Asteroids a;
		int origSize = asteroids.size();
		for(int i = 0; i<asteroids.size(); i++) {
			a = asteroids.get(i);
			if(getHitBox().intersects(a.getHitBox()) && !a.equals(this)) {
				a.wasHit(this);
				wasHit(a);
				if(asteroids.size()!=origSize) {
					i--;
					origSize = asteroids.size();
				}
			}
		}
	}
	
	public abstract void wasHit(Movable hitter);
	
	
	
}