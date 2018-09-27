package main.managers;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class ChatManager {
	
	public static int numberOfCharsPerLine = 30; //TODO keep at 30 for testing
	public static int maxLines = 10; // the maximum number of lines shown at a time
	public static int spaceInbetweenLines = 25;
	
	/*
	 * TODO is it wise to draw strings in here?
	 * if so make this method void
	 **/
	
	public static ArrayList<String> consoleLines = new ArrayList<String>(); // Whatever values are in this 
	public static ArrayList<String> allConsoleLines = new ArrayList<String>(); // Stores all lines even the ones off screen
	
	public static void seperateLines(String s, Graphics g) { //TODO BUG: lines can still start with spaces
		int sl = s.length();
		ArrayList<String> words = new ArrayList<String>();
		ArrayList<String> lines = new ArrayList<String>();
		String tempWord = "";
		String tempLine = "";
		int currentLineLength = 0;
		
		
		///// adds all the words into the words array /////
		for(int i = 0; i < sl; i++) { // loops through all the characters in the string
			char a = s.charAt(i); // gets the next letter in the string
			if(a == ' ') {
				words.add(tempWord);
				tempWord = " "; // this way there are still spaces
				words.add(tempWord);
				tempWord = "";
			} else {
				tempWord += a;
				if(i == sl - 1) {
					words.add(tempWord);
					tempWord = ""; // this isn't needed just helps me sleep at night
				}
			}
		}
		///////////////////////////////////////////////////
		
		
		///// adds words into lines to make lines that are less than or equal to the line default value /////
		for(int i = 0; i < words.size(); i++) {
			String word = words.get(i); // gets the word
			int wordLength = word.length();
			if(!(wordLength + currentLineLength > numberOfCharsPerLine)) {
				tempLine += word;
				currentLineLength += wordLength;
				if(i == words.size() - 1) {
					lines.add(tempLine);
					tempLine = "";
					currentLineLength = 0;
					tempLine += word;
					currentLineLength += wordLength;
				} 
			} else {
				lines.add(tempLine);
				tempLine = "";
				currentLineLength = 0;
				tempLine += word;
				currentLineLength += wordLength;
			}
			
		}
		drawChat(g, lines);
	}
	
	public static void doLogic() {
		if(consoleLines.size() >= 10) {
			consoleLines.remove(0); // will remove the last line
		}
	}
	
	private static void drawChat(Graphics g, ArrayList<String> lines) {
		///////////////////////////////////////////////////
		g.setColor(Color.CYAN);
		///// draws the string to the screen /////
		if(!consoleLines.contains(lines.get(0))) {
			for(int i = 0; i < lines.size(); i++) {
				String str = lines.get(i);
				consoleLines.add(str);
				allConsoleLines.add(str);
			}
		}
		//TODO this method will be removed later
		//TODO add lines to consoleLines
		//TODO probably can just get rid of consoleLines and just use allConsoleLines
		int iterator = 0;
		for(int j = consoleLines.size() - 1; j >= 0; j--) { // drawing the string
			iterator++;
			String str = consoleLines.get(j);
			g.drawString(str, 50, 275 - iterator*spaceInbetweenLines);
		}
		//////////////////////////////////////////////////
	}
	
	
	
}
