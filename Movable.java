public class Movable{
	
	// Not sure how to add the image and the Jpanel
	int x;
	int y;
	int dx;
	int dy;
	public Movable(int tx, int ty, int tdx, int tdy) {
		x = tx;
		y = ty;
		dx = tdx;
		dy = tdy;
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int z) {
		x = z;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int z) {
		y = z;
	}
	
	public void setDx(int z) {
		dx = z;
	}
	
	public int getDx() {
		return dx;
	}
	
	public void setDy(int z) {
		dy = z;
	}
	
	public int getDy() {
		return dy;
	}
	
	public int getType() {
		
	}
	
	public void draw() {
		
	}
	
	public void notifyIfHit() {
		
	}
	
	public void remove() {
		
	}
	
	public void move() {
		x += dx;
		y += dy;
	}
	
	
	
}