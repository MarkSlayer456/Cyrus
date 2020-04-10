package main;

import java.awt.Dimension;

import main.managers.ChatManager;
import main.managers.FileManager;
import main.managers.FrameRateManager;
import main.managers.ImageLoader;
import main.managers.InputManager;
import main.managers.NoteManager;
import main.managers.TodoManager;
import main.utilities.Calculator;

public class CyrusMain implements Runnable {
	
	/* Notes:
	 * Each frame needs to have it's own inputmanager other wise they will get confused and
	 * when you are in one window it will allow you to type on the other
	 * 
	 * 
	 */
	
	public static CyrusMain cyrusMain;
	
	public static CyrusMain getInstance() {
		return cyrusMain;
	}
	
	
	public final static String VERSION = "Version: 1.3.1 Pre-Alpha";
	
	private FileManager fileManager = FileManager.getInstance();
	private InputManager inputManager;
	private ImageLoader imageLoader = ImageLoader.getInstance();
	
	private static Thread mainThread;
	
	private AI cyrus;
	private Frame mainFrame;
//	public static Frame calcFrame;
	private Calculator calc;
	
	private Frame activeFrame;
	
	private boolean running;
	
	
	public CyrusMain() {
		
	}
	
	/**
	 * Setups the program, adding listeners and setting visibility if needed
     */
	private void init() {
		// This code can't be used in constuctor because the frames use the getinstance and the 
		// CyrusMain object needs to be created for that to work properly
		InputManager.inputManager = new InputManager();
		inputManager = InputManager.getInstance();
		NoteManager noteManager = new NoteManager(fileManager);
		TodoManager todoManager = new TodoManager(fileManager);
		
		this.mainFrame = new Frame(new Dimension(800, 300), "Cyrus", new FrameRateManager(), true); //TODO get screen size but this will do for now
		Frame calcFrame = new Frame(new Dimension(800, 500), "Calculator", new FrameRateManager(), false);
		this.calc = new Calculator(false, calcFrame);
		this.cyrus = new AI("Cyrus", new ChatManager(50, 10, 0), noteManager, todoManager); // Creating Cyrus
		running = true;
		
		
		fileManager.setup();
		mainFrame.getJFrame().setVisible(true);
		mainFrame.getJFrame().addKeyListener(inputManager);
		mainFrame.getJFrame().addMouseWheelListener(inputManager);
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
	
	/**
	 * Calculates all the logic in the program.
	 * At the moment this just does logic for the main class
	 */
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
			if(calc.getFrame().isDrawing()) {
				calc.update();
			}
		}
	}
	
	public static void main(String[] args) {
		cyrusMain = new CyrusMain();
		mainThread = new Thread(cyrusMain, "main");
		mainThread.setPriority(5);
		mainThread.start();
	}
	
	///// Getters /////
	/**
	 * Gets the main calculator object.
	 * @return - The main calculator object
	 */
	public Calculator getCalc() {
		return this.calc;
	}
	
	/**
	 * Gets the active frame, the frame that is currently focused.
	 * @return - The active frame
	 */
	public Frame getActiveFrame() {
		return this.activeFrame;
	}
	
	/**
	 * Gets the mainFrame of the program.
	 * @return - The mainFrame object
	 */
	public Frame getMainFrame() {
		return this.mainFrame;
	}
	
	/**
	 * Gets the main AI, cyrus.
	 * @return - The cyrus object
	 */
	public AI getCyrus() {
		return this.cyrus;
	}
	
	///// Setter ///// 
	
}
