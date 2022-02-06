import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Asteroids extends Movable{
	
	private int radius;
	public Asteroids(int tx, int ty, int tdx, int tdy, int tradius, JPanel panel) {
		super(tx,ty,tdx,tdy, 2*tradius, 2*tradius, panel);
		radius = tradius;
	}
	
	
	public void setRadius(int z){
		radius = z;
	}
	
	public int getRadius(){
		return radius;
	}

	public void notifyIfHit(){

	}

	public int getType(){
		return 3;
	}

	@Override
	public void remove(){

	}
	
	@Override
	public void draw(Graphics g) {
		g.drawImage(new ImageIcon("sprites/asteroid.png").getImage(), getX(), getY(), radius/2, radius/2, null);
	}

	
	@Override
	public void wasHit(Movable hitter) {
		if(hitter.getType()==2) { // If hitter is a laser
			if(getRadius() >= 4) {
				Asteroids a = new Asteroids(getX(), getY(), getDx(), getDy(), radius/2, getPanel());
				Driver.getAsteroids().add(a);
				Asteroids b = new Asteroids(getX(), getY(), -getDx(), -getDy(), radius/2, getPanel());
				Driver.getAsteroids().add(b);
				
			}
			
			// Create coin
			remove();
		}else{ // If hitter is an asteroid
			setDx(0- getDx());
			setDy(0- getDy());
		}
	}
	
	
}