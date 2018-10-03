package main.managers;

import java.awt.Color;
import java.awt.Graphics;

import main.AI;
import main.Frame;

public class UIManager {
	
	public void drawUI(Graphics g, AI ai) {
		///// Command Line /////
		// what the user is typing on
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Frame.mainFrame.getSize().width, Frame.mainFrame.getSize().height);
		g.setColor(Color.WHITE);
		g.drawString(Frame.mainFrame.getVersion(), Frame.mainFrame.getSize().width - 150, 50);
		g.setColor(Color.CYAN);
		g.drawString("--/>", 20, 275);
		ai.getChatManager().draw(g, Color.CYAN);
		ai.getInputManager().drawWhatUserIsCurrentlyTyping(g);
		//////////////////////////////////////
	}

}
