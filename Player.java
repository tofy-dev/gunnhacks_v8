import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Player extends Movable implements KeyListener{
	
	private double dir;
	private int speed;
	private int numLives = 3;
	
	private Graphics2D g2;
	private BufferedImage image;
	
	public Player(int tx, int ty, int width, int height, int speed, double dir, JPanel panel) {
		super(tx, ty, (int) (Math.sqrt((speed*speed)/(1+Math.tan(dir)*Math.tan(dir)))), 
				(int) ((Math.sqrt((speed*speed)/(1+Math.tan(dir)*Math.tan(dir)))*Math.tan(dir))), 
				width, height, panel);
		this.dir = dir;
		this.speed = speed;
		
		try {
			image = ImageIO.read(new File("sprites/player.png"));
			g2 = image.createGraphics();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
		if(g2!=null) g2.rotate(dir);
		//g.drawImage(new ImageIcon("sprites/player.png").getImage(), getX(), getY(), (int)getHitBox().getWidth(), (int)getHitBox().getHeight(), null);
		g.drawImage(image, getX(), getY(), (int)getHitBox().getWidth(), (int)getHitBox().getHeight(), null);
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		int typed = e.getKeyCode();
		System.out.println(typed);
			
		if(typed==KeyEvent.VK_LEFT) {
			dir-=0.1;
		}
		else if(typed==KeyEvent.VK_RIGHT) {
			dir+=0.1;
		}
		else if(typed==KeyEvent.VK_DOWN) {
			shoot();
		}else if(typed==KeyEvent.VK_UP) {
			move();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}
	
	public void shoot() {
		// Implement
	}
	
}