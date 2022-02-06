import javax.swing.*;
import java.awt.*;
	

public class Driver{

	private static Panel panel;
	private JFrame frame;
	private static Graphics2D g2;
	private static Graphics g;
	
	public Driver() {
		frame = new JFrame("'Sup?");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(0, 0, 700, 700);
		
		panel = new Panel();
		panel.setBackground(Color.BLACK);
		
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
		g2.setBackground(Color.WHITE);
		g2.fillRect(50, 100, 100, 100);
		
		
		g = panel.getGraphics();
		g.setColor(Color.BLACK);
		
	}
	
	
	public static void main(String[] args) {
		new Driver();
		while(true) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			panel.repaint();
		}
	}
	public class Panel extends JPanel{

		private int x = 5;
		
		 protected void paintComponent(Graphics g){
	         super.paintComponent(g);
	         g.setColor(Color.RED);
	     	 g.drawImage(new ImageIcon("sprites/asteroid.png").getImage(), x, 100, 50, 50, null);
	     	 x+=5;

	     }
	}

}

