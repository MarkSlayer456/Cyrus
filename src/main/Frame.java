package main;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import main.managers.ChatManager;
import main.managers.FileManager;
import main.managers.FrameRateManager;
import main.managers.ImageLoader;
import main.managers.InputManager;
import main.managers.UIManager;
import main.utilities.Calculator;


public class Frame implements Runnable {
	
	private FileManager fileManager = FileManager.getInstance();
	private InputManager inputManager = InputManager.getInstance();
	private ImageLoader imageLoader = ImageLoader.getInstance();
	
	///// Static Variables /////
	public static Thread mainFrameT;
	public static Thread cyrusCalc;
	
	public static AI cyrus;
	public static Frame mainFrame;
	public static Frame calcFrame;
	public static Calculator calc;
	//////////////////////////////////////////////
	
	private Graphics2D g;
	private BufferStrategy bs;
	private static String version;
	private boolean drawing = true; // Used if you ever want to stop drawing for some reason	
	
	private final Dimension size; // this will be able to change later
	private final JFrame frame;
	private final UIManager uiManager;
	
	@SuppressWarnings("static-access")
	public Frame(Dimension s, String name, FrameRateManager frManager) {
		this.g = null;
		this.bs = null;
		this.size = s;
		this.frame = new JFrame(name);
		this.uiManager = new UIManager(s, null, frManager);
		this.frame.setSize(size.width, size.height);
		this.frame.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width - size.width, 0);
		this.frame.setUndecorated(false);
		if(this.frame.getName().equalsIgnoreCase("Cyrus")) this.frame.setDefaultCloseOperation(this.frame.EXIT_ON_CLOSE);
		else this.frame.setDefaultCloseOperation(this.frame.HIDE_ON_CLOSE);
		
		//SystemTray.getSystemTray().add(trayIcon); neat
		
		//System.out.println("setting icon image");
		//this.frame.setIconImage(imageLoader.getImage("logo.png"));
		this.frame.setTitle(name);

		//this.frame.setVisible(true);
		this.frame.setResizable(true);
		this.frame.setMinimumSize(new Dimension(800, 300));
	}
	
	//TODO move to main class
	 public static void main(String[] args) { // Program begins
		mainFrame = new Frame(new Dimension(800, 300), "Cyrus", new FrameRateManager()); //TODO get screen size but this will do for now
		calcFrame = new Frame(new Dimension(800, 500), "Calculator", new FrameRateManager());
		calc = new Calculator(false, calcFrame);
		cyrus = new AI("Cyrus", new ChatManager(50, 10, 0, 25)); // Creating Cyrus
		cyrusCalc = new Thread(calc, "cyrus");
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

	/**
	 * Sets up the buffer strategy
	 */
	public void setupBufferStrategy() { //TODO maybe move to UIManager
		if(this.getBS() == null) { this.getJFrame().createBufferStrategy(3); }
		this.setBS(this.getJFrame().getBufferStrategy());
		this.setGraphics((Graphics2D) this.getBS().getDrawGraphics());
		this.uiManager.setGraphics(this.getGraphics());
		this.getGraphics().clearRect(0, 0, this.getWidth(), this.getHeight());
	}
	
	/**
	 * Disposes the current frame and shows the next frame
	 */
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
		this.uiManager.drawConsole(cyrus);
		////////////////////////////////////
		disposeAndShow();
	}

	/**
	 * Setups the program, adding listeners and setting visibility if needed
     */
	private void setup() {
        setVersion("Version: 1.3.0 Pre-Alpha");
		fileManager.setup();
		this.frame.setVisible(true);
		this.frame.addKeyListener(inputManager);
		cyrus.setup();
		Command.discoverCommands();
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
	/**
	 * Set the width of the frame
	 * @param width - The width you want to set
	 */
	public void setWidth(int width) {
		this.size.width = width;
		this.uiManager.setSize(width, this.uiManager.getSize().height);
	}
	/**
	 * Set the height of the frame
	 * @param height - The height you want to set
	 */
	public void setHeight(int height) {
		this.size.height = height;
	}
	/**
	 * Sets the BufferStategy for the frame
	 * @param bufferStr - The buffer strategy you want to set
	 */
	public void setBS(BufferStrategy bufferStr) {
		this.bs = bufferStr;
	}
	/**
	 * Sets the graphcis for the frame
	 * @param graphics - The graphics you want to use
	 */
	public void setGraphics(Graphics2D graphics) {
		this.g = graphics;
	}
	/**
	 * Sets the window to visible to the user or not
	 * @param bool - true visible; false invisible
	 */
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
	
	/**
	 * Gets width
	 * @return - The length of the width of the frame
	 */
	public int getWidth() {
		return this.size.width;
	}
	/**
	 * Gets height
	 * @return - The length of the height of the frame
	 */
	public int getHeight() {
		return this.size.height;
	}
	/**
	 * Gets BufferStrategy
	 * @return - The BufferStrategy for the given frame  
	 */
	public BufferStrategy getBS() {
		return this.bs;
	}
	/**
	 * Gets JFrame
	 * @return - The JFrame for the given frame
	 */
	public JFrame getJFrame() {
		return this.frame;
	}
	/**
	 * Gets graphics
	 * @return - The 2DGraphics for the given frame
	 */
	public Graphics2D getGraphics() {
		return this.g;
	}
	/**
	 * Gets UIManager
	 * @return - The UIManager for the given frame
	 */
	public UIManager getUIManager() {
		return this.uiManager;
	}
	
	/**
	 * Gets version
	 * @return - The version the program is currently running 
	 */
	public String getVersion() {
		return version;
	}
	
}
