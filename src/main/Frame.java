package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;


public class Frame implements Runnable {
	
	public static boolean drawing = true; // Used if you ever want to stop drawing for some reason	
	
	private Dimension size;
	private JFrame frame;
	
	@SuppressWarnings("static-access")
	public Frame(Dimension s) {
		this.size = s;
		JFrame f = new JFrame("Cyrus");
		this.frame = f;
		this.frame.setSize(size.width, size.height);
		this.frame.setUndecorated(false); // change this to true
		this.frame.setDefaultCloseOperation(this.frame.EXIT_ON_CLOSE);
		this.frame.setVisible(true);
	}
	
	public static void main(String[] args) { // Program begins
		Frame mainFrame = new Frame(new Dimension(800, 600));
		AI cyrus = new AI("Cyrus"); // Creating Cyrus
		Thread cyrusT = new Thread(cyrus, "cyrus");
		cyrusT.setPriority(9); // 10 is max priority
		cyrusT.start();
		Thread mainFrameT = new Thread(mainFrame, "frame");
		mainFrameT.setPriority(10);
		mainFrameT.start();
	}
	
	
	public void draw() { // What to display from Cyrus thoughts
		try {
			if(this.frame.getBufferStrategy() == null) { this.frame.createBufferStrategy(3); }
			BufferStrategy bs = this.frame.getBufferStrategy();
			Graphics g = bs.getDrawGraphics();
			////////////////////////////////////
			g.clearRect(0, 0, this.size.width, this.size.height);
			g.setColor(Color.RED);
			g.fillRect(100, 400, 200, 200);
			
			
			g.dispose();
			bs.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void run() {
		while(drawing) {
			draw();
		}
		
	}
	
	
	
	
	

}
