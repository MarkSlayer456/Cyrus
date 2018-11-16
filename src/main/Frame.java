package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;
import main.managers.ChatManager;
import main.managers.FileManager;
import main.managers.InputManager;
import main.managers.UIManager;
import main.utilities.Calculator;


public class Frame implements Runnable {
	
	//TODO try to get rid of lots of the static methods
	
	// Removed a static from drawing as it's not needed
	public boolean drawing = true; // Used if you ever want to stop drawing for some reason	
	public static AI cyrus;
	public static Frame mainFrame;
	public static Frame calcFrame;
	public static Calculator calc;
	public static Thread cyrusCalc;
	
	private Graphics g;
	private BufferStrategy bs;
	private String version;
	private boolean calcB; // is this the calculator frame?
	
	private Dimension size;
	private JFrame frame;
	
	
	@SuppressWarnings("static-access")
	public Frame(Dimension s, Boolean cb, String name) {
		this.size = s;
		this.g = null;
		this.bs = null;
		this.calcB = cb;
		this.setVersion("Version: 1.1.1 Pre-Alpha");
		this.frame = new JFrame(name);
		this.frame.setSize(size.width, size.height);
		this.frame.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width - size.width, 0);
		this.frame.setUndecorated(false); //TODO change this to true
		if(this.frame.getName().equalsIgnoreCase("Cyrus")) this.frame.setDefaultCloseOperation(this.frame.EXIT_ON_CLOSE);
		else this.frame.setDefaultCloseOperation(this.frame.HIDE_ON_CLOSE);
		//this.frame.setVisible(true);
		this.frame.setResizable(false);
	}
	
	
	
	public JFrame getFrame() {
		return this.frame;
	}
	
	public Dimension getSize()  {
		return this.size;
	}
	
	public Graphics getGraphics() {
		return this.g;
	}
	
	
	 public static void main(String[] args) { // Program begins
		mainFrame = new Frame(new Dimension(800, 300), false, "Cyrus"); //TODO get screen size but this will do for now
		calcFrame = new Frame(new Dimension(600, 500), true, "Calculator");
		calc = new Calculator(false);
		cyrus = new AI("Cyrus", new InputManager(), new UIManager(), new ChatManager(100, 10, 0, 25), new FileManager(), calc); // Creating Cyrus
		Thread cyrusT = new Thread(cyrus, "cyrus");
		cyrusCalc = new Thread(calc, "cyrus");
		cyrusT.setPriority(9); // 10 is max priority
		cyrusT.start();
		Thread mainFrameT = new Thread(mainFrame, "frame");
		mainFrameT.setPriority(10);
		mainFrameT.start();
	}
	 
	public void doLogic() { } // probably will need this later
	
	public void draw() { // What to display from Cyrus thoughts
		try {
			if(this.frame.getBufferStrategy() == null) { this.frame.createBufferStrategy(3); }
			this.bs = this.frame.getBufferStrategy();
			this.g = bs.getDrawGraphics();
			this.g.clearRect(0, 0, this.size.width, this.size.height);
			//////////////////////////////////// 
			// all drawing should be done between these /'s
			if(this.calcB) { // won't this need to be on the frame class and no the calc class for this to work
				calc.drawCalc(g, this);
			} else {
				cyrus.greet();
				cyrus.getUIManager().drawUI(this.g, cyrus);
			}
			////////////////////////////////////
			this.g.dispose();
			this.bs.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	private void setup() {
		cyrus.getFileManager().setup();
		this.frame.setVisible(true);
		this.frame.addKeyListener(cyrus.getInputManager());
		Command.setup(cyrus);
	}
	
	@Override
	public void run() {
		this.setup();
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
