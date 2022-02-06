import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Missile extends Laser{
	
	public Missile(int tx, int ty, int width, int height, double dir, int speed, JPanel panel) {
		super(tx, ty, width, height, dir, speed, panel);
		int rand = (int) (Math.random() * 11);
		rand -= 5;
		setDir(getDir()+ rand);
	}
	
	public void draw(Graphics g) {
		g.drawImage(new ImageIcon("sprites/missile.png").getImage(), getX(), getY(), (int)getHitBox().getWidth(), (int)getHitBox().getHeight(), null);
	}
	
	
	public int getType() {
		return 4;
	}
	
}
