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
		g.drawImage(new ImageIcon("sprites/asteroid.png").getImage(), getX(), getY(), radius*2, radius*2, null);
	}

	
	@Override
	public void wasHit(Movable hitter) {
		//System.out.println("was hit");
		if(hitter.getType()==2) { // If hitter is a laser
			if(getRadius() >= 10) {
				Asteroids a = new Asteroids(getX(), getY(), getDx(), getDy(), 2*radius/3, getPanel());
				Driver.getAsteroids().add(a);
				Asteroids b = new Asteroids(getX(), getY(), -getDx(), -getDy(), 2*radius/3, getPanel());
				Driver.getAsteroids().add(b);
			}
			
			createCoin();
			remove();
		}else if(hitter.getType() == 4) {
			createCoin();
			remove();
		}else{ // If hitter is an asteroid
//			setDx(0- getDx());
//			setDy(0- getDy());
		}
	}
	
	public void createCoin() {
		Coin coin = new Coin(getX(), getY(), 0, 0, 10, 20, getPanel());
		Driver.getCoins().add(coin);
	}
	
}