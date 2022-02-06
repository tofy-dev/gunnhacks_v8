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
	
	public Player(int tx, int ty, int width, int height, int speed, JPanel panel) {
		super(tx, ty, (int) (Math.cos(0)*speed), (int)(Math.sin(0)*speed), width, height, panel);
		this.dir = Math.PI/2;
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
	public void move() {
		setDx((int) (Math.cos(dir)*speed));
		setDy((int) (Math.sin(dir)*speed));
		
		System.out.println("dx: " + getDx() + ", dy: " + getDy());
		
		setX(getX() + getDx());
		setY(getY() + getDy());
//		if(getX() < -getWidth()/4) {
//			setX(getPanel().getWidth() + getWidth()/4);
//			System.out.println("1");
//		}
//		if(getY() < -getHeight()/4) {
//			setY(getPanel().getHeight() + getHeight()/4);
//			System.out.println("2");
//		}
//		if(getX() > getPanel().getWidth() + getWidth()/4) {
//			setX( -getWidth()/4);
//			System.out.println("3");
//		}
//		if(getY() > getPanel().getHeight() + getHeight()/4) {
//			setY( -getHeight()/4);
//			System.out.println("4");
//		}
//		
//		
//		getHitBox().setLocation(getX(), getY());
//		ArrayList<Asteroids> asteroids = Driver.getAsteroids();
//		for(int i = 0; i<asteroids.size(); i++) {
//			if(getHitBox().contains(asteroids.get(i).getHitBox())) {
//				asteroids.get(i).notifyIfHit(this);
//				wasHit(asteroids.get(i));
//			}
//		}
	}

	@Override
	public void remove() {
		// Implement GameOver
	}
	
	@Override
	public void draw(Graphics g) {
		double sin = Math.abs(Math.sin(Math.toRadians(dir)));
		double cos = Math.abs(Math.cos(Math.toRadians(dir)));
		int rW = (int) Math.floor(image.getWidth()*cos + image.getHeight()*sin);
		int rH = (int) Math.floor(image.getHeight()*cos + image.getWidth()*sin);
		    BufferedImage rotated = new BufferedImage(rW, rH, image.getType());
		    g2 = rotated.createGraphics();
		    g2.translate((rW-image.getWidth())/2, (rH-image.getHeight())/2);
		    g2.rotate(dir, image.getWidth()/2, image.getHeight()/2);
		    g2.drawRenderedImage(image, null);
		    g2.dispose();
		//g.drawImage(new ImageIcon("sprites/player.png").getImage(), getX(), getY(), (int)getHitBox().getWidth(), (int)getHitBox().getHeight(), null);
		g.drawImage(rotated, getX(), getY(), (int)(getHitBox().getWidth()), (int)(getHitBox().getHeight()), null);
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		if(dir>Math.PI*2) {
			dir -= Math.PI*2;
		}else if(dir<0-Math.PI*2) {
			dir += Math.PI*2;
		}
		int typed = e.getKeyCode();
		System.out.println(typed + ", " + (180*dir/Math.PI));
			
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