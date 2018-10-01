package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;
import main.managers.ChatManager;
import main.managers.InputManager;
import main.managers.UIManager;


public class Frame implements Runnable {
	
	// Removed a static from drawing as it's not needed
	public boolean drawing = true; // Used if you ever want to stop drawing for some reason	
	public static AI cyrus;
	public static Frame mainFrame;
	private Graphics g;
	private BufferStrategy bs;
	
	private Dimension size;
	private JFrame frame;
	
	@SuppressWarnings("static-access")
	public Frame(Dimension s) {
		this.size = s;
		this.g = null;
		this.bs = null;
		JFrame f = new JFrame("Cyrus");
		this.frame = f;
		this.frame.setSize(size.width, size.height);
		this.frame.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width - size.width, 0);
		this.frame.setUndecorated(false); //TODO change this to true
		this.frame.setDefaultCloseOperation(this.frame.EXIT_ON_CLOSE);
		this.frame.setVisible(true);
		this.frame.setResizable(true);
	}
	
	public void close() { // Please note this method just makes the frame not visible and doesn't close the application
		this.frame.setVisible(false);
	}
	
	public Dimension getSize()  {
		return this.size;
	}
	
	public Graphics getGraphics() {
		return this.g;
	}
	
	
	 public static void main(String[] args) { // Program begins
		mainFrame = new Frame(new Dimension(800, 300)); //TODO get screen size but this will do for now
		cyrus = new AI("Cyrus"); // Creating Cyrus
		Thread cyrusT = new Thread(cyrus, "cyrus");
		cyrusT.setPriority(9); // 10 is max priority
		cyrusT.start();
		Thread mainFrameT = new Thread(mainFrame, "frame");
		mainFrameT.setPriority(10);
		mainFrameT.start();
	}
	 
	public void doLogic() {
		ChatManager.doLogic();
		InputManager.doLogic();
	}
	
	public void draw() { // What to display from Cyrus thoughts
		try {
			if(mainFrame.frame.getBufferStrategy() == null) { mainFrame.frame.createBufferStrategy(3); }
			this.bs = mainFrame.frame.getBufferStrategy();
			this.g = bs.getDrawGraphics();
			this.g.clearRect(0, 0, mainFrame.size.width, mainFrame.size.height);
			//////////////////////////////////// 
			// all drawing should be done between these /'s
			cyrus.greet();
			UIManager.drawUI(this.g);
			////////////////////////////////////
			this.g.dispose();
			this.bs.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	private void setup() {
		mainFrame.frame.addKeyListener(new InputManager());
		ChatManager.setup();
	}
	
	@Override
	public void run() {
		mainFrame.setup();
		while(drawing) {
			draw();
			doLogic();
		}
		
	}
	
	
	
	
	

}
