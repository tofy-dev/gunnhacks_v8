public class Coin extends Movable {
    public Coin(int tx, int ty, int tdx, int tdy, int tradius, JPanel panel) {
        super(tx,ty,tdx,tdy, 2*tradius, 2*tradius, panel);
		radius = tradius;
    }
}