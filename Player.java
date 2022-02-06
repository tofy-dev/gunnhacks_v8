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

public class Player extends Movable implements KeyListener {

	private double dir;
	private int speed;
	private int numLives = 3;

	private Graphics2D g2;
	private BufferedImage image;

	private boolean upPressed = false;
	private boolean downPressed = false;
	private boolean leftPressed = false;
	private boolean rightPressed = false;
	private long cooldown = System.currentTimeMillis();

	public Player(int tx, int ty, int width, int height, int speed, JPanel panel) {
		super(tx, ty, (int) (Math.cos(0) * speed), (int) (Math.sin(0) * speed), width, height, panel);
		this.dir = 0;
		this.speed = speed;

		try {
			image = ImageIO.read(new File("sprites/player.png"));
			g2 = image.createGraphics();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public boolean isUpPressed() {
		return upPressed;
	}

	public boolean isDownPressed() {
		return downPressed;
	}

	public boolean isLeftPressed() {
		return leftPressed;
	}

	public boolean isRightPressed() {
		return rightPressed;
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
		if (!hitter.getClass().equals(Laser.class)) {
			numLives--;
			// flashing maybe?, or remove, wait 0.5 seconds, and then reappear?
		}

	}

	@Override
	public void move() {
		setDx((int) (Math.cos(dir - Math.PI / 2) * speed));
		setDy((int) (Math.sin(dir - Math.PI / 2) * speed));

		// System.out.println("dx: " + getDx() + ", dy: " + getDy());

		setX(getX() + getDx());
		setY(getY() + getDy());
		if (getX() < -getWidth() / 4) {
			setX(getPanel().getWidth() + getWidth());
			System.out.println("1");
		}
		if (getY() < -getHeight() / 4) {
			setY(getPanel().getHeight() + getHeight());
			System.out.println("2");
		}
		if (getX() > getPanel().getWidth() + getWidth()) {
			setX(-getWidth() / 4);
			System.out.println("3");
		}
		if (getY() > getPanel().getHeight() + getHeight()) {
			setY(-getHeight() / 4);
			System.out.println("4");
		}

		getHitBox().setLocation(getX(), getY());
		ArrayList<Asteroids> asteroids = Driver.getAsteroids();
		Asteroids temp;
		for (int i = 0; i < asteroids.size(); i++) {
			if (getHitBox().intersects(asteroids.get(i).getHitBox())) {
				temp = asteroids.get(i);
				// System.out.println("sdf");
				temp.notifyIfHit(this);
				wasHit(temp);
			}
		}
	}

	@Override
	public void remove() {

	}

	@Override
	public void draw(Graphics g) {
		double sin = Math.abs(Math.sin(Math.toRadians(dir)));
		double cos = Math.abs(Math.cos(Math.toRadians(dir)));
		int rW = (int) Math.floor(image.getWidth() * cos + image.getHeight() * sin);
		int rH = (int) Math.floor(image.getHeight() * cos + image.getWidth() * sin);
		BufferedImage rotated = new BufferedImage(rW, rH, image.getType());
		g2 = rotated.createGraphics();
		g2.translate((rW - image.getWidth()) / 2, (rH - image.getHeight()) / 2);
		g2.rotate(dir, image.getWidth() / 2, image.getHeight() / 2);
		g2.drawRenderedImage(image, null);
		g2.dispose();
		// g.drawImage(new ImageIcon("sprites/player.png").getImage(), getX(), getY(),
		// (int)getHitBox().getWidth(), (int)getHitBox().getHeight(), null);
		g.drawImage(rotated, getX(), getY(), (int) (getHitBox().getWidth()), (int) (getHitBox().getHeight()), null);
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (dir > Math.PI * 2) {
			dir -= Math.PI * 2;
		} else if (dir < 0 - Math.PI * 2) {
			dir += Math.PI * 2;
		}
		int typed = e.getKeyCode();
		if (typed == KeyEvent.VK_LEFT) {
			leftPressed = true;
		} else if (typed == KeyEvent.VK_RIGHT) {
			rightPressed = true;
		} else if (typed == KeyEvent.VK_DOWN) {
			downPressed = true;
		} else if (typed == KeyEvent.VK_UP) {
			upPressed = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int typed = e.getKeyCode();
		if (typed == KeyEvent.VK_LEFT) {
			leftPressed = false;
		} else if (typed == KeyEvent.VK_RIGHT) {
			rightPressed = false;
		} else if (typed == KeyEvent.VK_DOWN) {
			downPressed = false;
		} else if (typed == KeyEvent.VK_UP) {
			upPressed = false;
		}
	}

	public void shoot() {
//		int r = getWidth()/2;
//		int nX = getX() + getWidth()/2 + (int) (r * Math.cos(dir));
//		int nY = getY() - getHeight()/2 + (int) (r * Math.sin(dir));
		if (System.currentTimeMillis() - cooldown >= 1000) {
			System.out.println("over");
			int r = getWidth() / 2;

			double tDir = dir - Math.PI / 2;

			int nX = getX() + getWidth() / 2 + (int) (r * Math.cos(tDir));
			int nY = getY() + getHeight() / 2 + (int) (r * Math.sin(tDir));
			Driver.getLasers().add(new Laser(nX, nY, 10, 5, dir, 8, getPanel()));
			cooldown = System.currentTimeMillis();
		}
	}

	/*
	 * @Override public void keyPressed(KeyEvent e) { if(dir>Math.PI*2) { dir -=
	 * Math.PI*2; }else if(dir<0-Math.PI*2) { dir += Math.PI*2; } int typed =
	 * e.getKeyCode(); //System.out.println(typed + ", " + (180*dir/Math.PI));
	 * 
	 * if(typed==KeyEvent.VK_LEFT) { dir-=0.1; } else if(typed==KeyEvent.VK_RIGHT) {
	 * dir+=0.1; } else if(typed==KeyEvent.VK_DOWN) { shoot(); }else
	 * if(typed==KeyEvent.VK_UP) { move(); } }
	 */

}