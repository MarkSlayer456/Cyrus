package main.managers;

import java.util.ArrayList;

public class ChatManager {
	
	private final int numberOfCharsPerLine;
	private final int maxLines; // the maximum number of lines shown at a time
	private final int currentLine; // which line the user is currently on (will be displayed at the bottom of the console)
	private final int spaceInbetweenLines;
	
	private ArrayList<String> consoleLines;
	
	public ChatManager(Integer charsPerLine, Integer ml, Integer cl, Integer sil) {
		this.numberOfCharsPerLine = charsPerLine;
		this.maxLines = ml;
		this.currentLine = cl;
		this.spaceInbetweenLines = sil;
		this.consoleLines = new ArrayList<String>();
	}
	
	/**
	 * separates the string given, using the numberOfCharsPerLine
	 * @param s - The string you want to seperate into lines
	 * @return - a Array of all the lines
	 */
	public ArrayList<String> separateLines(String s) {
				ArrayList<String> lines = new ArrayList<>();
                int maxChars = this.numberOfCharsPerLine; // TODO change the name of number of chars to this
                String line = "";
                String currentWord = "";
                String[] words = s.split(" ");
            for (int i = 0; i < words.length;) {
                    currentWord = words[i];
                    if(line.length() + currentWord.length() <= maxChars) {
                        line += currentWord + " ";
                        i++;
                    } else {
                        //this.consoleLines.add(line);
                    	lines.add(line);
                        line = "";
                    }
                    if(words.length == i) {
                        lines.add(line);
                    }
            }
            return lines;
	}
	
	/**
	 * Adds a line to the console to be displayed
	 * @param line
	 */
	public void addConsoleLine(String line) {
		this.consoleLines.add(line);
	}
	
	public void clearConsoleLines() {
		this.consoleLines.clear();
	}
	
	///// Getters /////
	public ArrayList<String> getConsoleLines() {
		return this.consoleLines;
	}
	
	public int getSpaceInbetweenLines() {
		return this.spaceInbetweenLines;
	}
}
