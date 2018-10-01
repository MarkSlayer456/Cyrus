package main.managers;

import java.awt.Color;
import java.awt.Graphics;

import main.Frame;

public class UIManager extends ChatManager {
	
	public static void drawUI(Graphics g) {
		///// Command Line /////
		// what the user is typing on
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Frame.mainFrame.getSize().width, Frame.mainFrame.getSize().height);
		g.setColor(Color.CYAN);
		g.drawString("--/>", 20, 275);
		draw(g, Color.CYAN);
		//////////////////////////////////////
	}

}
