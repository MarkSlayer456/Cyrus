package main.managers;

import java.awt.Graphics;
import java.util.ArrayList;

public class ChatManager {
	
	public static int numberOfCharsPerLine = 30;
	
	/*
	 * TODO is it wise to draw strings in here?
	 * if so make this method void
	 **/
	
	public static ArrayList<String> consoleLine = new ArrayList<String>();
	
	
	public static void seperateLines(String s, Graphics g) { //TODO lines can still start with spaces
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
		///////////////////////////////////////////////////
		///// draws the string to the screen /////
		//TODO this method will be removed later
		//TODO add lines to consoleLines
		for(int j = 0; j < lines.size(); j++) { // drawing the string
			String str = lines.get(j);
			g.drawString(str, 100, 200 + j*25);
		}
		//////////////////////////////////////////////////
	}
}
