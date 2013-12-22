package miniCAD;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;


public class MiniCAD extends JApplet{

	BufferedImage img;
	static int w;
	static int h;

    public MiniCAD() {
    	ImageCanvas ic = new ImageCanvas();
    	this.getContentPane().add(ic);
    }
    
	public class ImageCanvas extends JApplet{
	
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