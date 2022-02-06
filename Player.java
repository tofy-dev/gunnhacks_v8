import javax.swing.JPanel;

public class Player extends Movable{
	
	int angle = 0;
	int numLives = 3;
	public Player(int tx, int ty, int tdx, int tdy, int width, int height, JPanel panel) {
		super(tx, ty, tdx, tdy, width, height, panel);
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
	
}