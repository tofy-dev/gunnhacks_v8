import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Driver {

	private static Panel panel;
	private static JFrame frame;
	private static Graphics2D g2;
	private static Graphics g;

	private static ArrayList<Asteroids> asteroids;
	private static ArrayList<Laser> lasers;
	private static Player player;
	private static boolean isGameOver;

	public static void main(String[] args) {

		frame = new JFrame("AstBlast");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(0, 0, 700, 700);

		panel = new Panel();
		panel.setBackground(Color.GRAY);

		panel.setLayout(null);

		frame.add(panel);

		frame.setVisible(true);

		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		g2 = (Graphics2D) panel.getGraphics();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		// g2.setBackground(Color.WHITE);
		// g2.fillRect(50, 100, 100, 100);

		g = panel.getGraphics();
		g.setColor(Color.BLACK);

		asteroids = new ArrayList<Asteroids>();
		for (int i = 0; i < 5; i++) {
			int speedx = (int) (Math.random() * 8) - 4;
			if (speedx == 0) {
				speedx = 1;
			}
			int speedy = (int) (Math.random() * 8) - 4;
			if (speedy == 0) {
				speedy = 1;
			}
			int size = (int) (Math.random() * 30) + 20;
			asteroids.add(new Asteroids((int) (Math.random() * panel.getWidth()),
					(int) (Math.random() * panel.getHeight()), speedx, speedy, size, panel));
		}
		lasers = new ArrayList<Laser>();

		player = new Player(350, 600, 50, 50, 5, panel);
		panel.setFocusable(true);
		panel.requestDefaultFocus();

		panel.addKeyListener(player);

		while (!isGameOver) {

			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			panel.repaint();
		}
	}

	public static class Panel extends JPanel {

		private int x = 5;

		protected void paintComponent(Graphics g) {
			if (!isGameOver) {
				super.paintComponent(g);
				// g.setColor(Color.RED);
				if (player != null && player.getLives() <= 0) {
					setGameOver(true);
				}
				managePlayer(g);
				manageAsteroids(g);
				manageLasers(g);

				x += 5;
			}

		}
	}

	public static void manageAsteroids(Graphics g) {
		if (asteroids == null)
			return;
		for (int i = 0; i < asteroids.size(); i++) {
			asteroids.get(i).draw(g);
			asteroids.get(i).move();
		}
	}

	public static ArrayList<Asteroids> getAsteroids() {
		return asteroids;
	}

	public static ArrayList<Laser> getLasers() {
		return lasers;
	}

	public static void managePlayer(Graphics g) {
		if (player == null)
			return;
		player.draw(g);

		player.move();
		//if (player.isUpPressed()) {
			//player.move();
		//}
		if (player.isDownPressed()) {
			player.shoot();
		}
		if (player.isLeftPressed()) {
			player.setdir(player.getdir() - 0.1);
		}
		if (player.isRightPressed()) {
			player.setdir(player.getdir() + 0.1);
			;
		}
	}

	public static void manageLasers(Graphics g) {
		if (lasers == null)
			return;
		Laser l;
		for (int i = 0; i < lasers.size(); i++) {
			l = lasers.get(i);
			l.draw(g);
			l.move();
		}
	}

	public static void setGameOver(boolean x) {
		isGameOver = x;

	}

	public static void lostLife() {
		// Possibly add Special effect?
		player.setX(panel.getWidth() / 2 - player.getWidth() / 2);
		player.setY(panel.getHeight() / 2 - player.getHeight() / 2);
	}

}
