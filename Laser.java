import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Laser extends Movable{

	private double dir; // Angle facing in radians
	
	private Graphics2D g2;
	private BufferedImage image;
	private int lifeTime = 100;
	
	public Laser(int tx, int ty, int width, int height, double dir, int speed, JPanel panel) {
		super(tx, ty, (int) (Math.cos(dir-Math.PI/2)*speed), (int)(Math.sin(dir-Math.PI/2)*speed), width, height, panel);
		this.dir = dir-Math.PI/2;
		
		try {
			image = ImageIO.read(new File("sprites/laser.png"));
			g2 = image.createGraphics();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
		Driver.getLasers().remove(this);
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
		if(lifeTime<0) {
			remove();
		}
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
		g.drawImage(rotated, getX(), getY(), (int)getHitBox().getWidth(), (int)getHitBox().getHeight(), null);
		
		lifeTime --;
	}
	

}
