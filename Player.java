import java.util.ArrayList;

import javax.swing.JPanel;

public class Player extends Movable{
	
	private int angle = 0;
	private int numLives = 3;
	public Player(int tx, int ty, int tdx, int tdy, int width, int height, JPanel panel, ArrayList<Asteroids> arr) {
		super(tx, ty, tdx, tdy, width, height, panel, arr);
	}
	
	public void setAngle(int z) {
		angle = z;
	}
	
	public int getAngle() {
		return angle;
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
		numLives --;
		// flashing maybe?, or remove, wait 0.5 seconds, and then reappear?
		
	}

	@Override
	public void remove() {
		
		
	}
	
}