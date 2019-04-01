package main;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import main.managers.FrameRateManager;
import main.managers.UIManager;


public class Frame {
	
	private CyrusMain cyrusMain;
	
	private Graphics2D g;
	private BufferStrategy bs;
	
	private final Dimension size; // this will be able to change later
	private final JFrame frame;
	private final UIManager uiManager;
	private boolean drawing;
	
	public Frame(Dimension s, String name, FrameRateManager frManager, boolean drawing) {
		this.g = null;
		this.bs = null;
		this.size = s;
		this.drawing = drawing;
		this.frame = new JFrame(name);
		this.uiManager = new UIManager(s, null, frManager);
		this.frame.setSize(size.width, size.height);
		this.frame.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width - size.width, 0);
		this.frame.setUndecorated(false);
		if(this.frame.getName().equalsIgnoreCase("Cyrus")) this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		else this.frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		//SystemTray.getSystemTray().add(trayIcon); neat
		
		//System.out.println("setting icon image");
		//this.frame.setIconImage(imageLoader.getImage("logo.png"));
		this.frame.setTitle(name);

		//this.frame.setVisible(true);
		this.frame.setResizable(true);
		this.frame.setMinimumSize(new Dimension(800, 300));
		cyrusMain = CyrusMain.getInstance();
	}
	
	 /**
      * Contains all the logic for the given frame
      */
	public void doLogic() {
		if(!this.frame.isShowing()) { // is program still running
			CyrusMain.quit();
		}
	}

	/**
	 * Sets up the buffer strategy
	 */
	public void setupBufferStrategy() { //TODO maybe move to UIManager
		if(this.getBS() == null) { 
			this.frame.setVisible(true); // this stops an error from occuring
			this.getJFrame().createBufferStrategy(3);
		}
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
		this.uiManager.draw(cyrusMain.getCyrus());
		////////////////////////////////////
		disposeAndShow();
	}

	//////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////
	
	
	///// Setters /////
	public void setDrawing(boolean bool) {
		this.drawing = bool;
	}
	
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
//    public void setVersion(String newVersion) {
//        version = newVersion;
//    }
	
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
	 * Checks to see if the given frame is drawing to the screen or not.
	 * @return - True if drawing, false if not
	 */
	public boolean isDrawing() {
		return this.drawing;
	}
	
	/**
	 * Checks to see if the frame is active.
	 * @return - True if the frame is active, false if not
	 */
	public boolean isActive() {
		return this.getJFrame().isActive();
	}
	
}
