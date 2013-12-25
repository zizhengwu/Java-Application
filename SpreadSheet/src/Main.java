import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import javax.swing.*;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;


public class Main extends JApplet {
	Sheet sheet = new Sheet();
	static Vector<Vector<String>> s = new Vector<Vector<String>>();
	static Vector<Vector<TextField>> t = new Vector<Vector<TextField>>();
	static int row = 0;
	static int column = 0;
	
	private static CSVReader reader;
	
	public Main() {
//		save
		JPanel s = new JPanel();
        s.add(new JLabel("Save"));
        SaveButton saveIt = new SaveButton();
        s.add(saveIt);
//        layout
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(sheet, BorderLayout.CENTER);
        this.getContentPane().add(s, BorderLayout.SOUTH);
    }
	
	public static void main(String[] args) throws IOException {
		read();
		JFrame f = new JFrame("SpreadSheet");
		Main mainFrame = new Main();
		f.getContentPane().add(mainFrame, BorderLayout.CENTER);
		f.pack();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}
	
	public static void read() throws IOException {
	    reader = new CSVReader(new FileReader("yourfile.csv"));
	    String [] nextLine;
	    int rowCount = -1;
	    while ((nextLine = reader.readNext()) != null) {
	    	rowCount = rowCount + 1;
	    	int i = 0;
	    	s.add(new Vector<String>());
	    	t.add(new Vector<TextField>());
	        for (i = 0; i < nextLine.length; i++) {
	        	s.get(rowCount).add(nextLine[i]);
	        	t.get(rowCount).add(new TextField(nextLine[i]));
			}
	        if (i > column) {
				column = i;
			}
	    }
	    row = rowCount;
	}
	
	public class Sheet extends JPanel {
		public Sheet() {
			this.setLayout(new GridLayout(row,column));
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < column; j++) {
					t.get(i).add(new TextField(""));
					this.add(t.get(i).get(j));
				}
			}

		}
	}
	
    public class SaveButton extends JButton {
    	public SaveButton() {
    		this.setText("click");
			addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					try {
						write();
						JOptionPane.showMessageDialog(null,"saved");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			});
    	}
    }
	
    public static void write() throws IOException {
    	CSVWriter writer = new CSVWriter(new FileWriter("saved.csv"), ',');
    	for (int i = 0; i < row; i++) {
        	String[] entries = new String [column];
        	for (int j = 0; j < column; j++) {
				entries[j] = t.get(i).get(j).getText();
			}
            writer.writeNext(entries);
		}

        writer.close();
    }
}