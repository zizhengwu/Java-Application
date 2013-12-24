package miniCAD;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;

import javax.imageio.*;
import javax.swing.*;


public class MiniCAD extends JApplet{

	private int currentType = 0;
	private Buttons[] c = new Buttons[4];
	KeyListener keyListener;
	BufferedImage img;
	static int w;
	static int h;
	Graphics2D g;
	ImageCanvas canvas = new ImageCanvas();
	
    public MiniCAD() {
		JPanel p = new JPanel();
		p.setBackground(Color.BLACK);
		p.setLayout(new FlowLayout(FlowLayout.LEFT));
		for (int i = 0; i < 4; i++)
			p.add(c[i] = new Buttons(i));
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(p, BorderLayout.NORTH);
		this.getContentPane().add(canvas, BorderLayout.CENTER);
    }
    
	public class ImageCanvas extends JApplet{
		int endX;
		int endY;
		private Point start = new Point(20, 20);
		
		public void paint(Graphics g) {
			g.drawImage(img, 0, 0, null);
		}

		public ImageCanvas() {
			try {
				img = ImageIO.read(new File("image.jpg"));
			} catch (IOException e) {
			}
	        w = img.getWidth(null);
	        h = img.getHeight(null);
	        g = img.createGraphics();
	        
	        keyListener = new KeyAdapter() {
				public void keyPressed(KeyEvent e) {
					char keyChar = e.getKeyChar();
					g.drawString(String.valueOf(keyChar), start.x, start.y);
					System.out.println("Key pressed");
				}
			};

			
			addMouseMotionListener(new MouseMotionAdapter() {
				public void mouseDragged(MouseEvent e) {
					endX = e.getX();
					endY = e.getY();
				}
			});

			addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					start.move(e.getX(), e.getY());
				}
			});
			
			
			addMouseListener(new MouseAdapter() {
				public void mouseReleased(MouseEvent e) {

					switch (currentType) {
					case 0:
						g.drawLine(start.x, start.y, e.getX(), e.getY());
						break;
					case 1:
						g.drawRect(start.x, start.y, e.getX() - start.x,
								e.getY() - start.y);
						break;
					case 2:
						g.drawOval(start.x, start.y, e.getX() - start.x,
								e.getY() - start.y);
						break;
					}
				
					repaint();
				}
			});
			

		}

		public Dimension getPreferredSize() {
			if (img == null) {
				return new Dimension(100, 100);
			} else {
				return new Dimension(img.getWidth(null), img.getHeight(null));
			}
		}
		
		
	}

	public class Buttons extends JPanel{
		private int type = 0;


		public Buttons(int t) {
			this.type = t;
			addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					if ((currentType == 3) && (type != 3))
						canvas.removeKeyListener(keyListener);

					if ((currentType != 3) && (type == 3)) {
						canvas.addKeyListener(keyListener);
						canvas.requestFocus();
						System.out.println("Key listener added");
					}

					if (currentType != type) {
						c[currentType].setBackground(Color.cyan);
						c[type].setBackground(Color.red);
						currentType = type;
					}
				}
			});
		}
		
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			int width = getSize().width;
			int height = getSize().height;

			switch (type) {
			case 0:
				g.drawLine(10, height - 10, width - 10, 10);
				break;
			case 1:
				g.drawRect(10, 10, width - 20, height - 20);
				break;
			case 2:
				g.drawOval(10, 10, width - 20, height - 20);
				break;
			case 3:
				g.setFont(new Font("TimesRoman", Font.PLAIN, 24));
				g.drawString("A", 10, 30);
			}
		}

		public Dimension getPreferredSize() {
			return new Dimension(40, 40);
		}
	}
	
	public static void main(String[] args) {
		JFrame f = new JFrame("miniCAD");
		MiniCAD mainFrame = new MiniCAD();
		f.getContentPane().add(mainFrame, BorderLayout.CENTER);
		f.pack();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null); // Center the frame
		f.setVisible(true);
	}
}