import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Start {

	private static Panel panel;
	private static JFrame frame;
	private static Graphics2D g2;
	private static Graphics g;

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
		
		
		

	}

	public static class Panel extends JPanel {

		private int x = 5;

		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(new ImageIcon("sprites/background.jpg").getImage(), 0, 0, panel.getWidth(), panel.getHeight(),
					null);
			
		}

	}
}
