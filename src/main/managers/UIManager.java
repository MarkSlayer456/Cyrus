package main.managers;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import main.AI;
import main.Frame;

public class UIManager {
	
	//TODO this file should be completely reworked

	private Graphics graphics;
	private Dimension size;
	
	
	public UIManager(Dimension size, Graphics graphics) {
		this.size = size;
		this.graphics = graphics;
	}
	
	/**
	 * Draws the console UI
	 */
	public void drawConsole(AI ai) {
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
		ai.getInputManager().drawWhatUserIsCurrentlyTyping(this.graphics);
		//////////////////////////////////////
		
		///// Version /////
		this.graphics.setColor(Color.WHITE);
		this.graphics.setFont(new Font("Cyrus Version", 1, 25));
		this.graphics.drawString(Frame.mainFrame.getVersion(), Frame.mainFrame.getWidth() - 300, 75);
		///////////////////////////////////////
	}
	
	public void drawCalc() { //TODO this is just drawing not VISUAL EFFECT ONLY
		this.getGraphics().setColor(Color.BLACK);
		this.getGraphics().fillRect(0, 0, Frame.calcFrame.getWidth(), Frame.calcFrame.getHeight());
		this.getGraphics().setColor(Color.WHITE);
		//TODO finish this
		this.getGraphics().fillRect(1, 100, 75, 50);
		this.getGraphics().fillRect(77, 100, 75, 50);
		this.getGraphics().fillRect(153, 100, 75, 50);
		
	}
	
	///// Setter /////
	public void setGraphics(Graphics graphics) {
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
