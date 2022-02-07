import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class Heart extends Movable{

	public Heart(int tx, int ty, int tdx, int tdy, int tradius, JPanel panel) {
		super(tx,ty,tdx,tdy,tradius*2, tradius*2,panel);
	}

	@Override
	public int getType() {
		return 6;
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(new ImageIcon("sprites/heart.png").getImage(), getX(), getY(), (int)getHitBox().getWidth(), (int)getHitBox().getHeight(), null);
	}

	@Override
	public void remove() {
		Driver.getPlayer().setLives(Driver.getPlayer().getLives() + 1);
		Driver.getHearts().remove(this);
	}

	@Override
	public void wasHit(Movable hitter) {
		if (hitter.getType() == 1) {
            remove();
        }
	}
	
	
}
