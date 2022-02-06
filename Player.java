import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Player extends Movable{
	
	private double dir;
	private int speed;
	private int numLives = 3;
	
	public Player(int tx, int ty, int width, int height, int speed, double dir, JPanel panel) {
		super(tx, ty, (int) (Math.sqrt((speed*speed)/(1+Math.tan(dir)*Math.tan(dir)))), 
				(int) ((Math.sqrt((speed*speed)/(1+Math.tan(dir)*Math.tan(dir)))*Math.tan(dir))), 
				width, height, panel);
		this.dir = dir;
		this.speed = speed;
	}
	
	public void setdir(double z) {
		dir = z;
	}
	
	public double getdir() {
		return dir;
	}
	
	public void setLives(int z) {
		numLives = z;
	}
	
	public int getLives() {
		return numLives;
	}
	
	public int getType() {
		return 1;
	}
	
	@Override
	public void wasHit(Movable hitter) {
		// Because the player shot the laser
		if(!hitter.getClass().equals(Laser.class)) {
			numLives --;
			// flashing maybe?, or remove, wait 0.5 seconds, and then reappear?
		}
		
	}

	@Override
	public void remove() {
		// Implement GameOver
	}
	
	@Override
	public void draw(Graphics g) {
		g.drawImage(new ImageIcon("sprites/player.png").getImage(), getX(), getY(), (int)getHitBox().getWidth(), (int)getHitBox().getHeight(), null);
	}
	
}