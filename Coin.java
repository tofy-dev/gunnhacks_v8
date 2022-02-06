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

    public int setValue(tvalue) {
        value = tvalue;
    }

    public void wasHit(Movable hitter) {
        if (hitter.getType() == 1) {
            // remove();
        }
    }

    public remove() {
        // remove the thing
    }
}