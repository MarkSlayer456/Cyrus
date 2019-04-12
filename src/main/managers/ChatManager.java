package main.managers;

import java.util.ArrayList;

public class ChatManager {
	
	private final int numberOfCharsPerLine;
	private final int maxLines; // the maximum number of lines shown at a time
	private final int currentLine; // which line the user is currently on (will be displayed at the bottom of the console)
	
	private ArrayList<String> consoleLines;
	
	/**
	 * Create ChatManager.
	 * @param charsPerLine - Maximum number of characters per line
	 * @param maxLines - Maximum amount of lines
	 * @param currentLine - The current line (unused)
	 * @param spaceInbetweenLines - Space inbetween each line
	 */
	public ChatManager(Integer charsPerLine, Integer maxLines, Integer currentLine) {
	    this.numberOfCharsPerLine = charsPerLine;
	    this.maxLines = maxLines;
	    this.currentLine = currentLine;
	    this.consoleLines = new ArrayList<String>();
	}
	
	/**
	 * Separates the string given, using the numberOfCharsPerLine as the maximum characters for
	 * each line.
	 * @param s - The string you want to seperate into lines
	 * @return - A array of all the lines
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
	 * Adds a line to the console to be displayed.
	 * @param line
	 */
	public void addConsoleLine(String line) {
		this.consoleLines.add(line);
	}
	
	/**
	 * Clears all the current lines on the console; removing them from memory.
	 */
	public void clearConsoleLines() {
		this.consoleLines.clear();
	}
	
	///// Getters /////
	/**
	 * Gets all the console lines
	 * @return - An array of the console lines
	 */
	public ArrayList<String> getConsoleLines() {
		return this.consoleLines;
	}
}
