package main.managers;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import main.Frame;

public class UIManager {
	
	public static void drawUI(Graphics g) {
		///// Command Line /////
		// what the user is typing on
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Frame.mainFrame.getSize().width, Frame.mainFrame.getSize().height);
		g.setColor(Color.CYAN);
		g.drawString("--/>", 20, 275);
		ChatManager.draw(g, Color.CYAN);
		drawWhatUserIsCurrentlyTyping(InputManager.currentCharacters, g);
		//////////////////////////////////////
	}

	public static void drawWhatUserIsCurrentlyTyping(ArrayList<Character> a, Graphics g) { // Rename this
		String temp = "";
		for(int i = 0; i < a.size(); i++) {
			char z = a.get(i);
			temp += z;
			g.drawString(temp, 40, 275);
		}
	}
	
}
