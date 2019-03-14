package main.managers;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import main.AI;
import main.CyrusMain;
import main.Frame;
import main.utilities.Button;

public class UIManager {
		
	InputManager inputManager = InputManager.getInstance();
	FileManager file = FileManager.getInstance();
	
	private Graphics2D graphics;
	private Dimension size;
	private FrameRateManager frManager;
	/**
	 * Creates UIManager
	 * @param size - The size of the frame
	 * @param graphics - The graphics 
	 * @param frManager - The frameRateManager
	 */
	public UIManager(Dimension size, Graphics2D graphics, FrameRateManager frManager) {
		this.size = size;
		this.graphics = graphics;
		this.frManager = frManager;
	}
	
	/**
	 * Draws the console UI.
	 * @param ai - The AI that you want to draw the console for
	 */
	public void draw(AI ai) {
		///// Frame Rate Limiter /////
		this.frManager.setStartingTime(System.currentTimeMillis());
		/////////////////////////////
		///// Command Line /////
		// what the user is typing on
		this.graphics.setFont(new Font("Cyrus Commandline", 1, 20));
		//g.setFont(normal);
		this.graphics.setColor(Color.BLACK);
		this.graphics.fillRect(0, 0, CyrusMain.mainFrame.getJFrame().getWidth(), CyrusMain.mainFrame.getJFrame().getHeight());
		this.graphics.setColor(new Color(153, 0, 255));
		this.graphics.drawString("--/>", 20, CyrusMain.mainFrame.getJFrame().getHeight() - 25);
		this.graphics.setColor(new Color(153, 0, 255));
		int iterator = 0;
		for(int j = ai.getChatManager().getConsoleLines().size() - 1; j >= 0; j--) { // drawing the string
			iterator++;
			String str = ai.getChatManager().getConsoleLines().get(j);
			this.graphics.drawString(str, 50, CyrusMain.mainFrame.getJFrame().getHeight() - 25 - iterator*ai.getChatManager().getSpaceInbetweenLines());
		}
		String temp = "";
		for(int i = 0; i < inputManager.getCurrentCharacters().size(); i++) {
			char z = inputManager.getCurrentCharacters().get(i);
			temp += z;
			this.graphics.drawString(temp, 53, CyrusMain.mainFrame.getJFrame().getHeight() - 25);
		}
		//////////////////////////////////////
		
		///// Version /////
		this.graphics.setColor(Color.WHITE);
		this.graphics.setFont(new Font("Cyrus Version", 1, 25));
		this.graphics.drawString(CyrusMain.VERSION, CyrusMain.mainFrame.getJFrame().getWidth() - 300, 75);
		///////////////////////////////////////
		this.frManager.setEndingTime(System.currentTimeMillis());
	}
	
	/**
	 * Draws the calculator
	 * @param buttons - array of buttons to display on the calculator.
	 */
	public void draw(ArrayList<Button> buttons) { // TODO needs to adjust with frame
		///// Frame Rate Limiter /////
		this.frManager.setStartingTime(System.currentTimeMillis());
		/////////////////////////////		
		this.getGraphics().setColor(Color.BLACK);
		this.getGraphics().fillRect(0, 0, 8000, 8000); //TODO change these
		this.getGraphics().setColor(Color.WHITE);
		for(int i = 0; i < buttons.size(); i++) { //TODO will be updated
			this.graphics.fill(buttons.get(i).getRect());
		}
		this.graphics.setColor(Color.WHITE);
		this.graphics.setFont(new Font("Calculator Numbers", 1, 25));
		this.graphics.drawString("0", CyrusMain.mainFrame.getJFrame().getWidth() - 100, 75);
		
		
		
		//TODO finish this
		this.getGraphics().fillRect(1, 100, 75, 50);
		this.getGraphics().fillRect(77, 100, 75, 50);
		this.getGraphics().fillRect(153, 100, 75, 50);
		
		this.frManager.setEndingTime(System.currentTimeMillis());
	}
	
	///// Setter /////
	/**
	 * Sets the graphics for the given UIManager.
	 * @param graphics - The graphics you want to set 
	 */
	public void setGraphics(Graphics2D graphics) {
		this.graphics = graphics;
	}
	/**
	 * Sets the size of the 
	 * @param width
	 * @param height 
	 */
	public void setSize(int width, int height) {
		this.size = new Dimension(width, height);
	}
	
	///// Getter /////
	/**
	 * Gets the graphics for the current given UIManager
	 * @return - The graphics for the current UIManager
	 */
	public Graphics getGraphics() {
		return this.graphics;
	}
	/**
	 * 
	 * @return 
	 */
	public Dimension getSize() {
		return this.size;
	}
	/**
	 * 
	 * @return 
	 */
	public FrameRateManager getFrManager() {
		return this.frManager;
	}
}
