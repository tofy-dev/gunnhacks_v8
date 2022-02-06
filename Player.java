import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageFilter;
import java.awt.image.RGBImageFilter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Player extends Movable implements KeyListener {

	private double dir;
	private double speed;
	private int numLives = 3;

	private Graphics2D g2;
	private BufferedImage normalImage;
	private BufferedImage currentImage;
	private BufferedImage hurtImage;

	private boolean upPressed = false;
	private boolean downPressed = false;
	private boolean leftPressed = false;
	private boolean rightPressed = false;
	private long cooldown = System.currentTimeMillis();
	private double acceleration = 0.25;
	private double deacceleration = 0.1;
	
	private int hurtCount = 0;
	private boolean isHurting = false;
	
	private final double DEFAULT_ACCELERATION = 0.25;
	private final double DEFAULT_DEACCELERATION = 0.1;
	
	public Player(int tx, int ty, int width, int height, int speed, JPanel panel) {
		super(tx, ty, (int) (Math.cos(0) * speed), (int) (Math.sin(0) * speed), width, height, panel);
		this.dir = 0;
		this.speed = speed;

		try {
			normalImage = ImageIO.read(new File("sprites/player.png"));
			g2 = normalImage.createGraphics();
			hurtImage = ImageIO.read(new File("sprites/playerHurt.png"));
//			for(int i = 0; i<hurtImage.getWidth(); i++) {
//				for(int j = 0; i<hurtImage.getHeight(); i++) {
//					int pixel = hurtImage.getRGB(i, j);
//					Color c = new Color(pixel, true);
//					int r = c.getRed();
//					int g = c.getGreen();
//					int b = c.getBlue();
//					int a = c.getAlpha();
//					Color nC= new Color(50, g, b, a);
//					hurtImage.setRGB(i, j, nC.getRGB());
//				}
//			}
//			currentImage = hurtImage;
			currentImage = normalImage;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		isHurting = true;

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
		if (hitter.getType()==3 && !isHurting) {
			numLives--;
			Driver.lostLife();
			isHurting = true;
			// flashing maybe?, or remove, wait 0.5 seconds, and then reappear?
		}

	}
	
	public boolean isMoving() {
		//System.out.println(isDownPressed() || isUpPressed() || isLeftPressed() || isRightPressed());
		//return isDownPressed() || isUpPressed() || isLeftPressed() || isRightPressed();
		return isUpPressed();
	}

	@Override
	public void move() {
		if(isMoving() && speed<20) {
			speed+=acceleration;
			acceleration += 0.0001;
			deacceleration = DEFAULT_DEACCELERATION;
		}else if(speed>0.25){
			speed-=deacceleration;
			acceleration = DEFAULT_ACCELERATION;
		}
		setDx((int) (Math.cos(dir - Math.PI / 2) * speed));
		setDy((int) (Math.sin(dir - Math.PI / 2) * speed));
		// System.out.println("dx: " + getDx() + ", dy: " + getDy());

		setX(getX() + getDx());
		setY(getY() + getDy());
		if (getX() < -getWidth()) {
			setX(getPanel().getWidth() + getWidth());
			//System.out.println("1");
		}
		if (getY() < -getHeight()) {
			setY(getPanel().getHeight() + getHeight());
			//System.out.println("2");
		}
		if (getX() > getPanel().getWidth() + getWidth()) {
			setX(-getWidth());
			//System.out.println("3");
		}
		if (getY() > getPanel().getHeight() + getHeight()) {
			setY(-getHeight());
			//System.out.println("4");
		}

		getHitBox().setLocation(getX(), getY());
		
		ArrayList<Asteroids> asteroids = Driver.getAsteroids();
		Asteroids temp;
		for (int i = 0; i < asteroids.size(); i++) {
			if (!isHurting && getHitBox().intersects(asteroids.get(i).getHitBox())) {
				temp = asteroids.get(i);
				// System.out.println("sdf");
				temp.wasHit(this);
				wasHit(temp);
				i--;
			}
		}
		
		ArrayList<Coin> coins = Driver.getCoins();
		Coin c;
		for(int i = 0; i<coins.size(); i++) {
			if(getHitBox().intersects(coins.get(i).getHitBox())) {
				c = coins.get(i);
				c.wasHit(this);
				i--;
			}
		}
	}

	@Override
	public void remove() {
		// Driver handles
	}

	@Override
	public void draw(Graphics g) {
		if(isHurting) {
			if(hurtCount/4%2==0) {
				currentImage = hurtImage;
			}else {
				currentImage = normalImage;
			}
			hurtCount++;
		}
		if(hurtCount>70) {
			hurtCount = 0;
			isHurting = false;
			currentImage = normalImage;
		}
		double sin = Math.abs(Math.sin(Math.toRadians(dir)));
		double cos = Math.abs(Math.cos(Math.toRadians(dir)));
		int rW = (int) Math.floor(currentImage.getWidth() * cos + currentImage.getHeight() * sin);
		int rH = (int) Math.floor(currentImage.getHeight() * cos + currentImage.getWidth() * sin);
		BufferedImage rotated = new BufferedImage(rW, rH, currentImage.getType());
		g2 = rotated.createGraphics();
		g2.translate((rW - currentImage.getWidth()) / 2, (rH - currentImage.getHeight()) / 2);
		g2.rotate(dir, currentImage.getWidth() / 2, currentImage.getHeight() / 2);
		g2.drawRenderedImage(currentImage, null);
		g2.dispose();
		// g.drawImage(new ImageIcon("sprites/player.png").getImage(), getX(), getY(),
		// (int)getHitBox().getWidth(), (int)getHitBox().getHeight(), null);
		g.drawImage(rotated, getX(), getY(), (int) (getHitBox().getWidth()), (int) (getHitBox().getHeight()), null);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		int typed = e.getKeyCode();
		if (typed == KeyEvent.VK_DOWN) {
			shoot();
		}
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
		} else if (typed == KeyEvent.VK_UP) {
			upPressed = true;
		}

		if (typed == KeyEvent.VK_DOWN) {
			downPressed = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int typed = e.getKeyCode();
		if (typed == KeyEvent.VK_LEFT) {
			leftPressed = false;
		} else if (typed == KeyEvent.VK_RIGHT) {
			rightPressed = false;
		} else if (typed == KeyEvent.VK_UP) {
			upPressed = false;
		}
		if (downPressed == true) {
			downPressed = false;
			shoot();
		}
	}

	public void shoot() {
//		int r = getWidth()/2;
//		int nX = getX() + getWidth()/2 + (int) (r * Math.cos(dir));
//		int nY = getY() - getHeight()/2 + (int) (r * Math.sin(dir));
		if (System.currentTimeMillis() - cooldown >= 300) {
			int r = getWidth() / 2;

			double tDir = dir - Math.PI / 2;
			if (!upPressed) {
				setX(getX() - getDx());
				setY(getY() - getDy());
			}
			int nX = getX() + getWidth() / 2 + (int) (r * Math.cos(tDir));
			int nY = getY() + getHeight() / 2 + (int) (r * Math.sin(tDir));
			Driver.getLasers().add(new Laser(nX, nY, 5, 5, dir, 10, getPanel()));
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