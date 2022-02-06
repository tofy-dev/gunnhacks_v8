import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Laser extends Movable{

	private double dir; // Angle facing in radians
	
	public Laser(int tx, int ty, int width, int height, double dir, int speed, JPanel panel) {
		super(tx, ty, (int) (Math.sqrt((speed*speed)/(1+Math.tan(dir)*Math.tan(dir)))), 
				(int) ((Math.sqrt((speed*speed)/(1+Math.tan(dir)*Math.tan(dir)))*Math.tan(dir))), 
				width, height, panel);
		this.dir = dir;
	}

	@Override
	public int getType() {
		return 2;
	}

//	@Override
//	public void notifyIfHit(Movable hitter) {
//		// Call wasHit on hitter
//	}

	@Override
	public void remove() {
		
	}

	public void setDir(double d) {
		dir = d;
	}
	
	public double getDir() {
		return dir;
	}
	
	
	@Override
	public void wasHit(Movable hitter) {
		remove();
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(new ImageIcon("sprites/laser.png").getImage(), getX(), getY(), (int)getHitBox().getWidth(), (int)getHitBox().getHeight(), null);
	}
	

}
