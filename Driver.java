import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class Driver {

	private static Panel panel;
	private static JFrame frame;
	private static JFrame gameFrame;
	private static Graphics2D g2;
	private static Graphics g;
	private static StartPanel startPanel;
	private static JButton button;

	private static ArrayList<Asteroids> asteroids;
	private static ArrayList<Laser> lasers;
	private static ArrayList<Coin> coins;
	private static ArrayList<Heart> hearts;
	private static Player player;
	private static boolean isGameOver = false;
	private static boolean spawnOnce = true;
	private static int score = 0;
	private static boolean shouldStartGame = false;

	private static long astCooldown = System.currentTimeMillis();

	public static void main(String[] args) {

		frame = new JFrame("AstBlast");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(0, 0, 400, 200);
		
		startPanel = new StartPanel();
		startPanel.setLayout(null);
		frame.add(startPanel);
		
		button = new JButton();
		button.setText("START GAME");
		button.setBounds(40, 40, 300, 100);
		button.setBackground(Color.WHITE);
		button.addActionListener(startPanel);
		
		startPanel.add(button);
		
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		frame.setVisible(true);
		
		
		while(true) {
			init();
			button.setText("Start Game");
			//System.out.println(true);
		}
	}
	
	public static void init() {
		while(!shouldStartGame) {
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		// panel.setBackground(Color.GRAY);
		
		
		//System.out.println(1);
		gameFrame = new JFrame();
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setBounds(0, 0, 700, 700);
		
		button.setText("End Game");
		
		panel = new Panel();
		panel.setLayout(null);
		
		gameFrame.requestFocus();
		gameFrame.setVisible(true);
		
		gameFrame.add(panel);

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
		hearts = new ArrayList<Heart>();

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
			
			if(asteroids != null && asteroids.size() <= 1) {
				spawnAsteroids(6);
			}
			
			if (!isGameOver) {
				super.paintComponent(g);
				
				g.drawImage(new ImageIcon("sprites/background.jpg").getImage(), 0, 0, panel.getWidth(),
						panel.getHeight(), null);
				// g.setColor(Color.RED);
				if (player != null && player.getLives() <= 0) {
					setGameOver(true);
				}
				manageHearts(g);
				managePlayer(g);
				manageAsteroids(g);
				manageLasers(g);
				manageCoins(g);		
				
				drawText(g);
				
				x += 5;
			}else {
			}

		}
	}
	
	public static class StartPanel extends JPanel implements ActionListener{

		//private int x = 5;

		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
		}
		public void actionPerformed(ActionEvent e) {
			if(Driver.shouldStartGame==false) {
				Driver.startGame();
			}else {
				Driver.gameFrame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
				//Driver.gameFrame.dispose();
			}
			//System.out.println(shouldStartGame);
		}
	}

	public static void manageAsteroids(Graphics g) {
		if (asteroids == null)
			return;
		
		if(Math.random()<0.0001) {
			spawnAsteroids(1);
		}
		
		System.out.println(asteroids.size());
		for (int i = 0; i < asteroids.size(); i++) {
			asteroids.get(i).draw(g);
			asteroids.get(i).move();
		}
	}
	
	public static Player getPlayer() {
		return player;
	}
	
	public static ArrayList<Heart> getHearts(){
		return hearts;
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
		int origSize = lasers.size();
		for (int i = 0; i < lasers.size(); i++) {
			l = lasers.get(i);
			l.draw(g);
			l.move();
			if(origSize != lasers.size()) {
				origSize = lasers.size();
				i--;
			}
		}
	}
	
	public static void manageCoins(Graphics g) {
		if (coins == null)
			return;
		//System.out.println(coins.size());
		Coin c;
		int origSize = coins.size();
		for (int i = 0; i < coins.size(); i++) {
			c = coins.get(i);
			c.draw(g);
			c.move();
			if(origSize != coins.size()) {
				origSize = coins.size();
				i--;
			}
		}
	}
	
	public static void manageHearts(Graphics g) {
		if (hearts == null)
			return;
		//System.out.println(coins.size());
		if(Math.random()<0.001) {
			int speedx = (int) (Math.random() * 8) - 4;
			if (speedx == 0) {
				speedx = 1;
			}
			int speedy = (int) (Math.random() * 8) - 4;
			if (speedy == 0) {
				speedy = 1;
			}
			hearts.add(new Heart((int) (Math.random() * panel.getWidth()),
					(int) (Math.random() * panel.getHeight()), speedx, speedy, 10, panel));		
			}
		
		Heart h;
		int origSize = hearts.size();
		for (int i = 0; i < hearts.size(); i++) {
			h = hearts.get(i);
			h.draw(g);
			h.move();
			if(origSize != hearts.size()) {
				origSize = hearts.size();
				i--;
			}
		}
	}

	public static void setGameOver(boolean x) {
		isGameOver = x;

	}

	public static void lostLife() {
		if(player.getLives()>0) {
			player.setX(panel.getWidth() / 2 - player.getWidth() / 2);
			player.setY(panel.getHeight() / 2 - player.getHeight() / 2);
		}
	}
	
	public static void addLife() {
		if(player.getLives()>=9) return;
		player.setLives(player.getLives()+1);
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
		
		double missileCooldown = player.getMissileCooldown()/1000;
		if(missileCooldown>5) {
			missileCooldown = 5;
		}
		g.drawString("Missile Cooldown: " + (5 - missileCooldown), 5, panel.getHeight()-30);
	}
	
	public static void updateScore(int i) {
		score += i;
	}

	public static void spawnAsteroids(int num) {
		for (int i = 0; i < num; i++) {
			int speedx = (int) (Math.random() * 8) - 4;
			if (speedx == 0) {
				speedx = 1;
			}
			int speedy = (int) (Math.random() * 8) - 4;
			if (speedy == 0) {
				speedy = 1;
			}
			int size = (int) (Math.random() * 30) + 10;
			asteroids.add(new Asteroids((int) (Math.random() * panel.getWidth()),
					(int) (Math.random() * panel.getHeight()), speedx, speedy, size, panel));
		}
	}
	
	protected static void startGame() {
		shouldStartGame = true;
	}
}
