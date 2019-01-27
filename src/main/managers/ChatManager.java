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
	
	public void seperateLines(String s) {
                int maxChars = this.numberOfCharsPerLine; // TODO change the name of number of chars to this
                String line = "";
                String currentWord = "";
                String[] words = s.split(" ");
            for (int i = 0; i < words.length; i++) {
                if (line.length() + words[i].length() <= maxChars) {
                    currentWord = words[i];
                    if(line.length() + currentWord.length() <= maxChars) {
                        line += currentWord + " ";
                    } else {
                        this.consoleLines.add(line);
                        line = "";
                        words[i] = "";
                    }
                    if(words.length - 1 == i) {
                        this.consoleLines.add(line);
                    }
                } else {
                    this.consoleLines.add(line);
                    line = "";
                    words[i] = "";
                }
            }
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
