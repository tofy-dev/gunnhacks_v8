import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Asteroids extends Movable{
	
	private int radius;
	public Asteroids(int tx, int ty, int tdx, int tdy, int tradius, JPanel panel) {
		super(tx,ty,tdx,tdy, 2*tradius, 2*tradius, panel);
		radius = tradius;
	}
	
	
	public void setRadius(int z){
		radius = z;
	}
	
	public int getRadius(){
		return radius;
	}
	
	public int getType(){
		return 3;
	}

	@Override
	public void remove(){
		Driver.getAsteroids().remove(this);
	}
	
	@Override
	public void draw(Graphics g) {
		String x = "sprites/asteroid3,png";
		if(radius <= 15) {
			x = "sprites/asteroid.png";
		}else if(radius <= 21) {
			x = "sprites/asteroid2.png";
		}
		g.drawImage(new ImageIcon(x).getImage(), getX(), getY(), radius*2, radius*2, null);
	}

	
	@Override
	public void wasHit(Movable hitter) {
		//System.out.println("was hit");
		if(hitter.getType()==2) { // If hitter is a laser
			if(getRadius() >= 15) {
				Asteroids a = new Asteroids(getX(), getY(), getDx(), getDy(), 2*radius/3, getPanel());
				Driver.getAsteroids().add(a);
				Asteroids b = new Asteroids(getX(), getY(), -getDx(), -getDy(), 2*radius/3, getPanel());
				Driver.getAsteroids().add(b);
			}
			int x = (int)(Math.random()*30);
			if(x == 1) {
				createHeart();
			}else{
				createCoin();
			}
			remove();
		}else if(hitter.getType() == 4) {
			createCoin();
			remove();
		}else{ // If hitter is an asteroid
//			setDx(0- getDx());
//			setDy(0- getDy());
		}
	}
	
	public void createHeart() {
		Heart heart = new Heart(getX(), getY(), getDx()/2, getDy()/2, 10, getPanel());
		Driver.getHearts().add(heart);
	}
	
	public void createCoin() {
		Coin coin = new Coin(getX(), getY(), getDx()/2, getDy()/2, 10, 20, getPanel());
		Driver.getCoins().add(coin);
	}
	
}