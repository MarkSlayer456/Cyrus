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
	///// Static Variables /////
	public static Thread cyrusT;
	public static Thread mainFrameT;
	public static Thread cyrusCalc;
	
	public static AI cyrus;
	public static Frame mainFrame;
	public static Frame calcFrame;
	public static Calculator calc;
	//////////////////////////////////////////////
	
	private Graphics g;
	private BufferStrategy bs;
	private String version;
	private boolean calcB; // is this the calculator frame?
	private boolean drawing = true; // Used if you ever want to stop drawing for some reason	
	
	private Dimension size;
	private JFrame frame;
	
	
	
	// add the private vars on to the frame class
	@SuppressWarnings("static-access")
	public Frame(Dimension s, Boolean cb, String name) {
		this.size = s;
		this.g = null;
		this.bs = null;
		this.calcB = cb;
		this.setVersion("Version: 1.2.0 Pre-Alpha");
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
		cyrus = new AI("Cyrus", new InputManager(), new UIManager(), new ChatManager(75, 10, 0, 25), new FileManager(), calc); // Creating Cyrus
		cyrusT = new Thread(cyrus, "cyrus");
		cyrusCalc = new Thread(calc, "cyrus");
		cyrusT.setPriority(5); // 10 is max priority
		cyrusT.start();
		mainFrameT = new Thread(mainFrame, "frame");
		mainFrameT.setPriority(5);
		mainFrameT.start();
	}
	 
	public void doLogic() { 
	// check if program is still running	
		if(!this.frame.isShowing()) { 
			//TODO don't use static make each thread check back to see if main thread is still running
			quit();
			/*drawing = false;
			AI.thinking = false;
			Calculator.running = false;*/
		}
	}
	
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
	
	public static void quit() { // very useful
		System.exit(0);
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
