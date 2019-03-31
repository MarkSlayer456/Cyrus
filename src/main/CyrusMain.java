package main;

import java.awt.Dimension;

import main.managers.ChatManager;
import main.managers.FileManager;
import main.managers.FrameRateManager;
import main.managers.ImageLoader;
import main.managers.InputManager;
import main.utilities.Calculator;

public class CyrusMain implements Runnable {
	
	/* Notes:
	 * Each frame needs to have it's own inputmanager other wise they will get confused and
	 * when you are in one window it will allow you to type on the other
	 * 
	 * 
	 */
	
	
	
	
	public final static String VERSION = "Version: 1.3.0 Pre-Alpha";
	
	private FileManager fileManager = FileManager.getInstance();
	private InputManager inputManager = InputManager.getInstance();
	private ImageLoader imageLoader = ImageLoader.getInstance();
	
	public static Thread mainThread;
	
	public static AI cyrus;
	public static Frame mainFrame;
	public static Frame calcFrame;
	public static Calculator calc;
	
	public static Frame activeFrame;
	
	private boolean running;
	
	
	public CyrusMain() {
		running = true;
	}
	
	/**
	 * Setups the program, adding listeners and setting visibility if needed
     */
	public void init() {
		fileManager.setup();
		mainFrame.getJFrame().setVisible(true);
		mainFrame.getJFrame().addKeyListener(inputManager);
		calc.getFrame().getJFrame().addKeyListener(inputManager); // this makes it so you can still type while the calculator is open
		calc.getFrame().getJFrame().addMouseListener(inputManager);
		mainFrame.getJFrame().addMouseListener(inputManager);
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
	
	private void doLogic() {
		if(mainFrame.isActive()) {
			activeFrame = mainFrame; // hope this makes a pointer
		} else {
			activeFrame = calc.getFrame(); // hope this makes a pointer
		}
	}
	
	@Override
	public void run() {
		this.init();
		while(this.running) {
			doLogic();
			if(mainFrame.isDrawing()) {
				mainFrame.draw();
				mainFrame.doLogic();
			}
			if(calcFrame.isDrawing()) {
				calc.update();
			}
		}
	}
	
	public static void main(String[] args) {
		CyrusMain cyrusMain = new CyrusMain();
		mainFrame = new Frame(new Dimension(800, 300), "Cyrus", new FrameRateManager(), true); //TODO get screen size but this will do for now
		calcFrame = new Frame(new Dimension(800, 500), "Calculator", new FrameRateManager(), false);
		calc = new Calculator(false, calcFrame);
		cyrus = new AI("Cyrus", new ChatManager(50, 10, 0, 25)); // Creating Cyrus
		mainThread = new Thread(cyrusMain, "main");
		mainThread.setPriority(5);
		mainThread.start();
	}
	
}
