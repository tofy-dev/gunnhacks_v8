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
	private static ArrayList<Coin> coins;
	private static Player player;
	private static boolean isGameOver = false;
	private static boolean spawnOnce = true;
	private static int score = 0;

	private static long astCooldown = System.currentTimeMillis();

	public static void main(String[] args) {

		frame = new JFrame("AstBlast");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(0, 0, 700, 700);

		panel = new Panel();
		// panel.setBackground(Color.GRAY);

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
		lasers = new ArrayList<Laser>();
		coins = new ArrayList<Coin>();

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
		drawGameOver(g);
	}

	public static class Panel extends JPanel {

		private int x = 5;

		protected void paintComponent(Graphics g) {
			
			if(!spawnOnce && asteroids != null && asteroids.size() <= 1) {
				spawnAsteroids();
			}
			if(spawnOnce && System.currentTimeMillis() - astCooldown >= 1000) {
				spawnOnce = false;
				spawnAsteroids();
			}
			if (!isGameOver) {
				super.paintComponent(g);
				
				g.drawImage(new ImageIcon("sprites/background.jpg").getImage(), 0, 0, panel.getWidth(),
						panel.getHeight(), null);
				// g.setColor(Color.RED);
				if (player != null && player.getLives() <= 0) {
					setGameOver(true);
				}
				managePlayer(g);
				manageAsteroids(g);
				manageLasers(g);
				
				drawText(g);
				
				x += 5;
			} else {
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
	
	public static ArrayList<Coin> getCoins(){
		return coins;
	}

	public static void managePlayer(Graphics g) {
		if (player == null)
			return;
		player.draw(g);

		player.move();
		// if (player.isUpPressed()) {
		// player.move();
		// }
		if (player.isDownPressed()) {
			player.shoot();
		}
		if (player.isLeftPressed()) {
			player.setdir(player.getdir() - 0.1);
		}
		if (player.isRightPressed()) {
			player.setdir(player.getdir() + 0.1);

		}
		if(player.isSlashPressed()) {
			player.shootMissile();
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

	public static void drawGameOver(Graphics g) {
		g.setColor(Color.GREEN);
		g.setFont(new Font("Times", Font.BOLD, 80));
		g.drawString("GAME OVER", 100, 350);
	}
	
	public static void drawText(Graphics g) {
		if(g==null) return;
		g.setColor(Color.GREEN);
		g.setFont(new Font("Times", Font.BOLD, 30));
		g.drawString("Score: " + score, 5, 30);
		
		g.drawString("Lives: " + player.getLives(), panel.getWidth()-130, 30);
	}
	
	public static void updateScore(int i) {
		score += i;
	}

	public static void spawnAsteroids() {
		for (int i = 0; i < 6; i++) {
			int speedx = (int) (Math.random() * 8) - 4;
			if (speedx == 0) {
				speedx = 1;
			}
			int speedy = (int) (Math.random() * 8) - 4;
			if (speedy == 0) {
				speedy = 1;
			}
			int size = (int) (Math.random() * 20) + 10;
			asteroids.add(new Asteroids((int) (Math.random() * panel.getWidth()),
					(int) (Math.random() * panel.getHeight()), speedx, speedy, size, panel));
		}
	}
}
