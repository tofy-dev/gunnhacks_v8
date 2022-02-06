import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Asteroids extends Movable{
	
	int radius;
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

	public void remove(){

	}
	
	@Override
	public void draw(Graphics g) {
		g.drawImage(new ImageIcon("sprites/asteroid.png").getImage(), getX(), getY(), radius/2, radius/2, null);
	}
	
	
}