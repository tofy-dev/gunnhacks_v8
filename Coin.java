import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Coin extends Movable {
    private int radius;
    private int value;

    public Coin(int tx, int ty, int tdx, int tdy, int tradius, int tvalue, JPanel panel) {
        super(tx,ty,tdx,tdy, 2*tradius, 2*tradius, panel);
		radius = tradius;
        value = tvalue;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int z) {
        value = z;
    }

    public void wasHit(Movable hitter) {
        if (hitter.getType() == 1) {
            remove();
        }
    }

    public void remove() {
    	//System.out.println(getValue());
        Driver.updateScore(getValue());
        Driver.getCoins().remove(this);
    }

	@Override
	public int getType() {
		return 5;
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(new ImageIcon("sprites/coin.png").getImage(), getX(), getY(), (int)getHitBox().getWidth(), (int)getHitBox().getHeight(), null);
	}
}