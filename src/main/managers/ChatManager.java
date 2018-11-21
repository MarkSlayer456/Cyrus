package main.managers;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class ChatManager {
	
	private int numberOfCharsPerLine;
	private int maxLines; // the maximum number of lines shown at a time
	private int currentLine; // which line the user is currently on (will be displayed at the bottom of the console)
	private int spaceInbetweenLines;
	
	//TODO FIX: both of these aren't needed
	private ArrayList<String> consoleLines; // Whatever values are in this 
	
	public ChatManager(Integer charsPerLine, Integer ml, Integer cl, Integer sil) {
		this.numberOfCharsPerLine = charsPerLine;
		this.maxLines = ml;
		this.currentLine = cl;
		this.spaceInbetweenLines = sil;
		this.consoleLines = new ArrayList<String>();
	}
	
	//TODO could've used .split(" "); here
	public void seperateLines(String s) { //TODO BUG: lines can still start with spaces
		//TODO this method could be made way easier
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
			if(!(wordLength + currentLineLength > this.numberOfCharsPerLine)) {
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
				currentLineLength = wordLength;
				tempLine += word;
				if(words.size() == i + 1) { // checking to see if only one words needs to go on the last line
					lines.add(tempLine);
				}
			}
			
		}
		for(int i = 0; i < lines.size(); i++) {
			String str = lines.get(i);
			this.consoleLines.add(str);
		}
	}
	
	public void clearConsoleLines() {
		this.consoleLines.clear();
	}
	
	public void draw(Graphics g, Color c) {
		g.setColor(c);
		int iterator = 0;
		for(int j = this.consoleLines.size() - 1; j >= 0; j--) { // drawing the string
			iterator++;
			String str = this.consoleLines.get(j);
			g.drawString(str, 50, 275 - iterator*this.spaceInbetweenLines);
		}
	}
}
