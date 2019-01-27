package main.managers;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import main.AI;
import main.Frame;

public class UIManager {
	
	public void drawUI(Graphics g, AI ai) {
		///// Command Line /////
		// what the user is typing on
		g.setFont(new Font("Cyrus Commandline", 1, 20));
		//g.setFont(normal);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Frame.mainFrame.getWidth(), Frame.mainFrame.getHeight());
		g.setColor(Color.CYAN);
		g.drawString("--/>", 20, 275);
		ai.getChatManager().draw(g, Color.CYAN);
		ai.getInputManager().drawWhatUserIsCurrentlyTyping(g);
		//////////////////////////////////////
		
		///// Version /////
		g.setColor(Color.WHITE);
		g.setFont(new Font("Cyrus Version", 1, 25));
		g.drawString(Frame.mainFrame.getVersion(), Frame.mainFrame.getWidth() - 300, 75);
		///////////////////////////////////////
	}

}
