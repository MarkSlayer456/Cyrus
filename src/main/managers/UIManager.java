package main.managers;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import main.AI;
import main.Frame;
import main.utilities.Button;

public class UIManager {
		
	InputManager inputManager = InputManager.getInstance();
	
	private Graphics2D graphics;
	private Dimension size;
	
	
	public UIManager(Dimension size, Graphics2D graphics) {
		this.size = size;
		this.graphics = graphics;
	}
	
	/**
	 * Draws the console UI
	 * @param ai the AI that you want to draw the console for
	 */
	public void drawConsole(AI ai) { //TODO make everything change with the screen size
		///// Command Line /////
		// what the user is typing on
		this.graphics.setFont(new Font("Cyrus Commandline", 1, 20));
		//g.setFont(normal);
		this.graphics.setColor(Color.BLACK);
		this.graphics.fillRect(0, 0, Frame.mainFrame.getWidth(), Frame.mainFrame.getHeight());
		this.graphics.setColor(Color.CYAN);
		this.graphics.drawString("--/>", 20, 275);
		this.graphics.setColor(Color.CYAN);
		int iterator = 0;
		for(int j = ai.getChatManager().getConsoleLines().size() - 1; j >= 0; j--) { // drawing the string
			iterator++;
			String str = ai.getChatManager().getConsoleLines().get(j);
			this.graphics.drawString(str, 50, 275 - iterator*ai.getChatManager().getSpaceInbetweenLines());
		}
		inputManager.drawWhatUserIsCurrentlyTyping(this.graphics);
		//////////////////////////////////////
		
		///// Version /////
		this.graphics.setColor(Color.WHITE);
		this.graphics.setFont(new Font("Cyrus Version", 1, 25));
		this.graphics.drawString(Frame.mainFrame.getVersion(), Frame.mainFrame.getWidth() - 300, 75);
		///////////////////////////////////////
	}
	
	public void drawCalc(ArrayList<Button> buttons) {
		this.getGraphics().setColor(Color.BLACK);
		this.getGraphics().fillRect(0, 0, Frame.calcFrame.getWidth(), Frame.calcFrame.getHeight());
		this.getGraphics().setColor(Color.WHITE);
		for(int i = 0; i < buttons.size(); i++) { //TODO will be updated
			this.graphics.fill(buttons.get(i).getRect());
		}
		
		
		
		//TODO finish this
		this.getGraphics().fillRect(1, 100, 75, 50);
		this.getGraphics().fillRect(77, 100, 75, 50);
		this.getGraphics().fillRect(153, 100, 75, 50);
		
	}
	
	///// Setter /////
	public void setGraphics(Graphics2D graphics) {
		this.graphics = graphics;
	}
	
	public void setSize(int width, int height) {
		this.size = new Dimension(width, height);
	}
	
	///// Getter /////
	public Graphics getGraphics() {
		return this.graphics;
	}
	
	public Dimension getSize() {
		return this.size;
	}
}
