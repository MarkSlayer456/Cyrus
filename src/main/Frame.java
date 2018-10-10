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
	
	//TODO try to get rid of lots of the static methods
	
	// Removed a static from drawing as it's not needed
	public boolean drawing = true; // Used if you ever want to stop drawing for some reason	
	public static AI cyrus;
	public static Frame mainFrame;
	private Graphics g;
	private BufferStrategy bs;
	private String version;
	
	private Dimension size;
	private JFrame frame;
	
	@SuppressWarnings("static-access")
	public Frame(Dimension s) {
		this.size = s;
		this.g = null;
		this.bs = null;
		this.setVersion("Version: 1.1.0 Pre-Alpha");
		JFrame f = new JFrame("Cyrus");
		this.frame = f;
		this.frame.setSize(size.width, size.height);
		this.frame.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width - size.width, 0);
		this.frame.setUndecorated(false); //TODO change this to true
		this.frame.setDefaultCloseOperation(this.frame.EXIT_ON_CLOSE);
		this.frame.setVisible(true);
		this.frame.setResizable(false);
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
		cyrus = new AI("Cyrus", new InputManager(), new UIManager(), new ChatManager(30, 10, 0, 25)); // Creating Cyrus
		Thread cyrusT = new Thread(cyrus, "cyrus");
		cyrusT.setPriority(9); // 10 is max priority
		cyrusT.start();
		Thread mainFrameT = new Thread(mainFrame, "frame");
		mainFrameT.setPriority(10);
		mainFrameT.start();
	}
	 
	public void doLogic() { } // probably will need this later
	
	public void draw() { // What to display from Cyrus thoughts
		try {
			if(mainFrame.frame.getBufferStrategy() == null) { mainFrame.frame.createBufferStrategy(3); }
			this.bs = mainFrame.frame.getBufferStrategy();
			this.g = bs.getDrawGraphics();
			this.g.clearRect(0, 0, mainFrame.size.width, mainFrame.size.height);
			//////////////////////////////////// 
			// all drawing should be done between these /'s
			cyrus.greet();
			cyrus.getUIManager().drawUI(this.g, cyrus);
			////////////////////////////////////
			this.g.dispose();
			this.bs.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	private void setup() {
		mainFrame.frame.addKeyListener(cyrus.getInputManager());
		Command.setup();
	}
	
	@Override
	public void run() {
		mainFrame.setup();
		while(drawing) {
			draw();
			doLogic();
		}
		
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
	
	
	
	

}
