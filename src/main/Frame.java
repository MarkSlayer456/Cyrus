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
	private static String version;
	private boolean drawing = true; // Used if you ever want to stop drawing for some reason	
	
	private Dimension size; // this will be able to change later
	private final JFrame frame;
	private UIManager uiManager;
	
	
	
	@SuppressWarnings("static-access")
	public Frame(Dimension s, String name) {
		this.g = null;
		this.bs = null;
		this.size = s;
		this.frame = new JFrame(name);
		this.uiManager = new UIManager(s, null);
                
		this.frame.setSize(size.width, size.height);
		this.frame.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width - size.width, 0);
		this.frame.setUndecorated(false);
		if(this.frame.getName().equalsIgnoreCase("Cyrus")) this.frame.setDefaultCloseOperation(this.frame.EXIT_ON_CLOSE);
		else this.frame.setDefaultCloseOperation(this.frame.HIDE_ON_CLOSE);
		//this.frame.setVisible(true);
		this.frame.setResizable(false);
	}
	
	 public static void main(String[] args) { // Program begins
		mainFrame = new Frame(new Dimension(800, 300), "Cyrus"); //TODO get screen size but this will do for now
		calcFrame = new Frame(new Dimension(600, 500), "Calculator");
		calc = new Calculator(false, calcFrame);
		cyrus = new AI("Cyrus", new InputManager(), new ChatManager(50, 10, 0, 25), new FileManager(), calc); // Creating Cyrus
		cyrusT = new Thread(cyrus, "cyrus");
		cyrusCalc = new Thread(calc, "cyrus");
		cyrusT.setPriority(5); // 10 is max priority
		cyrusT.start();
		mainFrameT = new Thread(mainFrame, "frame");
		mainFrameT.setPriority(5);
		mainFrameT.start();
	}
	 /**
      * Contains all the logic for the given frame
      */
	public void doLogic() { 
		if(!this.frame.isShowing()) { // is program still running
			quit();
		}
	}

	public void setupBufferStrategy() {
		if(this.getBS() == null) { this.getJFrame().createBufferStrategy(3); }
		this.setBS(this.getJFrame().getBufferStrategy());
		this.setGraphics(this.getBS().getDrawGraphics());
		this.uiManager.setGraphics(this.getGraphics());
		this.getGraphics().clearRect(0, 0, this.getWidth(), this.getHeight());
	}
	
	public void disposeAndShow() {
		this.g.dispose();
		this.bs.show();
	}
	
	
	 /**
      * Draws all the information for the given frame
      */
	public void draw() { // What to display from Cyrus thoughts
		setupBufferStrategy();
		////////////////////////////////////
		cyrus.greet();
		this.uiManager.drawConsole(cyrus);
		////////////////////////////////////
		disposeAndShow();
	}

	/**
	 * Setups the program, adding listeners and setting visibility if needed
     */
	private void setup() {
        setVersion("Version: 1.2.2 Pre-Alpha");
		cyrus.getFileManager().setup();
		this.frame.setVisible(true);
		this.frame.addKeyListener(cyrus.getInputManager());
		Command.setup(cyrus);
	}
	/**
     * Quits the program, closing all open windows and shutting down
     * the process
     */
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
	
	///// Setters /////
	
	public void setWidth(int width) {
		this.size.width = width;
		this.uiManager.setSize(width, this.uiManager.getSize().height);
	}
	
	public void setHeight(int height) {
		this.size.height = height;
	}
	
	public void setBS(BufferStrategy bufferStr) {
		this.bs = bufferStr;
	}
	
	public void setGraphics(Graphics graphics) {
		this.g = graphics;
	}
	
	public void setVisible(boolean bool) {
		this.frame.setVisible(bool);
	}
	
	/**
     * Changes the version number of the program
     * @param newVersion the version you want to change too
     */
    public void setVersion(String newVersion) {
        version = newVersion;
    }
	
	///// Getters /////
	
	public int getWidth() {
		return this.size.width;
	}
	
	public int getHeight() {
		return this.size.height;
	}
	
	public BufferStrategy getBS() {
		return this.bs;
	}
	
	public JFrame getJFrame() {
		return this.frame;
	}
	
	public Graphics getGraphics() {
		return this.g;
	}
	
	public UIManager getUIManager() {
		return this.uiManager;
	}
	
	/**
	 * 
	 * @return the version of the program is currently running 
	 */
	public String getVersion() {
		return version;
	}
	
	
	
	
	
	
	

    
        
        

}
