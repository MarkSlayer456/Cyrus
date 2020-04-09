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
import main.utilities.Calculator;

public class UIManager {
		
	private InputManager inputManager;
	private FileManager file = FileManager.getInstance();
	private CyrusMain cyrusMain;
	
	private Graphics2D graphics;
	private Dimension size;
	private FrameRateManager frManager;
	private int offset;
	/**
	 * Creates UIManager
	 * @param size - The size of the frame
	 * @param frManager - The frameRateManager
	 */
	public UIManager(Dimension size, FrameRateManager frManager) {
		this.size = size;
		this.graphics = null;
		this.frManager = frManager;
		this.cyrusMain = CyrusMain.getInstance();
		this.inputManager = InputManager.getInstance();
		this.offset = 0;
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
		Frame mainFrame = cyrusMain.getMainFrame();
		int heightOfText = this.graphics.getFontMetrics().getHeight();
		this.graphics.setColor(Color.BLACK);
		this.graphics.fillRect(0, 0, mainFrame.getJFrame().getWidth(), mainFrame.getJFrame().getHeight());
		this.graphics.setColor(new Color(153, 0, 255));
		this.graphics.drawString("--/>", 20, mainFrame.getJFrame().getHeight() - heightOfText);
		this.graphics.setColor(new Color(153, 0, 255));
		int iterator = 0;
		for(int j = ai.getChatManager().getConsoleLines().size() - 1 - this.offset; j >= 0; j--) { // drawing the string
			iterator++;
			String str = ai.getChatManager().getConsoleLines().get(j);
			this.graphics.drawString(str, 50, mainFrame.getJFrame().getHeight() - heightOfText - iterator*heightOfText);
		}
		String temp = "";
		for(int i = 0; i < inputManager.getCurrentCharacters().size(); i++) {
			char z = inputManager.getCurrentCharacters().get(i);
			temp += z;
			this.graphics.drawString(temp, 53, mainFrame.getJFrame().getHeight() - heightOfText);
		}
		//////////////////////////////////////
		
		///// Version /////
		this.graphics.setColor(Color.WHITE);
		this.graphics.setFont(new Font("Cyrus Version", 1, 25));
		this.graphics.drawString(CyrusMain.VERSION, mainFrame.getJFrame().getWidth() - 300, 75);
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
		Frame mainFrame = cyrusMain.getMainFrame();
		Calculator calc = cyrusMain.getCalc();
		
		this.getGraphics().setColor(Color.BLACK);
		this.getGraphics().fillRect(0, 0, 8000, 8000); //TODO change these
		int fontSize = 25;
		this.graphics.setFont(new Font("Calculator Numbers", 1, fontSize));
		for(int i = 0; i < buttons.size(); i++) { //TODO will be updated
			Button b = buttons.get(i);
			this.getGraphics().setColor(Color.WHITE);
			this.graphics.fill(b.getRect());
			this.getGraphics().setColor(Color.ORANGE);
			int widthOfText = this.graphics.getFontMetrics().stringWidth(b.getText());
			int heightOfText = this.graphics.getFontMetrics().getHeight();
			this.graphics.drawString(b.getText(), (float) (b.getRect().getX() + b.getRect().getWidth() / 2 - widthOfText / 2),
					(float) (b.getRect().getY() + (b.getRect().getHeight() / 2) + (heightOfText / 2) / 2));
			
		}
		this.graphics.setColor(Color.ORANGE);
		this.graphics.drawString(calc.getDisplayText(), 
				mainFrame.getJFrame().getWidth() - (100 + this.graphics.getFontMetrics().stringWidth(calc.getDisplayText())), 75);
		
		
		
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
	 * Gets the size of the frame
	 * @return - The size of the frame
	 */
	public Dimension getSize() {
		return this.size;
	}
	/**
	 * Gets the frame rate manager
	 * @return - The framerate manager
	 */
	public FrameRateManager getFrManager() {
		return this.frManager;
	}
	
	public int getOffset() {
		return this.offset;
	}
	
	public void setOffset(int numb) {
		this.offset = numb;
	}
	
	public void scrollup(AI ai) {
		if(this.offset != ai.getChatManager().getConsoleLines().size()-1) {
			this.offset++;	
		}
	}
	
	public void scrolldown(AI ai) {
		if(this.offset != 0) {
			this.offset--;
		}
	}
	
}
