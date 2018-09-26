package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import javax.swing.JFrame;

import main.managers.ChatManager;


public class Frame implements Runnable {
	
	// Removed a static from drawing as it's not needed
	public boolean drawing = true; // Used if you ever want to stop drawing for some reason	
	public static AI cyrus;
	public static Frame mainFrame;
	
	
	private Dimension size;
	private JFrame frame;
	
	@SuppressWarnings("static-access")
	public Frame(Dimension s) {
		this.size = s;
		JFrame f = new JFrame("Cyrus");
		this.frame = f;
		this.frame.setSize(size.width, size.height);
		this.frame.setUndecorated(false); //TODO change this to true
		this.frame.setDefaultCloseOperation(this.frame.EXIT_ON_CLOSE);
		this.frame.setVisible(true);
	}
	
	 public static void main(String[] args) { // Program begins
		mainFrame = new Frame(new Dimension(800, 600)); //TODO get screen size but this will do for now
		cyrus = new AI("Cyrus"); // Creating Cyrus
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
			g.clearRect(0, 0, this.size.width, this.size.height);
			//////////////////////////////////// 
			// all drawing should be done between these /'s
			
			//TODO remove this; just testing
			//TODO move this loop to logic
			g.setColor(Color.RED);
			ChatManager.seperateLines(cyrus.greet(), g);
			//ChatManager.convertToMultipleLines(cyrus.greet(), g);
			g.drawString(cyrus.greet(), 100, 400);
			//TODO remove this ^; just testing
			
			////////////////////////////////////
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
