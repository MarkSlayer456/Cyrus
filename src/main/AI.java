package main;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import main.managers.ChatManager;
import main.managers.FileManager;

public class AI {
	
	FileManager fileManager = FileManager.getInstance();
	
	
	private String name;
	private boolean hasGreeted; // has Cyrus introduced himself
	private Color color; // will implement later
	private ArrayList<String> args;
	private final ChatManager chatManager;
	
	
	/**
	 * 
	 * @param name - Name of the AI
	 * @param chat - ChatManager for the AI to display the console
	 */
	public AI(String n, ChatManager chat) {
		this.name = n;
		this.hasGreeted = false;
		this.color = Color.CYAN;
		this.args = new ArrayList<String>();
		this.chatManager = chat;
	}
	
	
	
	public void setup() {
		this.greet("Hello, my name is " + this.name + ", this message is brought to you from the AI.java file under the greet method!");
		//TODO ask for name
		// check if setup file exists and make sure nothing is invalid
		/*if(!(fileManager.getFile("settings.cy").exists())) { // TODO finish this
			File settings = fileManager.createFile("settings");
			int i = 0;
			fileManager.writeToFile(settings, "color: red", i++);
			// no other settings at the moment
		}*/
	}
	
	
	/**
	 * @param message - The greet message for the AI
	 */
	public void greet(String message) {
		//TODO this is just for testing
		if(!this.hasGreeted) {
			this.hasGreeted = true;
			outputMessage(message);
		}
	}
	
	
	
	/**
	 * 
	 * @param str - What you want to output to the console
	 */
	public void outputMessage(String str) {
		this.getChatManager().seperateLines(str);
	}
	
	public void outputHelpMessage(Command cmd) {
		this.outputMessage(cmd.getPrefixHelpString());
		this.outputMessage(cmd.getHelpString().get(0));
	}
	
	public void outputErrorMessage() {
		String user = System.getProperty("user.name").toString().toLowerCase();
		File file = fileManager.getFile("C:\\Users\\" + user + "\\AppData\\Local\\Cyrus\\error messages.cy");
		Random r = new Random();
		int ran = r.nextInt(fileManager.readFullFile(file).size());
		this.outputMessage(fileManager.readFileLine(file, ran));
	}
	
	public void interpret(String cmd) {
		if(Command.getCommand(cmd) != null && !cmd.equalsIgnoreCase("")) {
		Command.getCommand(cmd).executeCommand(this);
		} else {
			this.outputErrorMessage();
		}
	}
	
	///// Setters /////
	
	
	
	///// Getters /////
	
	public String getName() {
		return this.name;
	}
	
	public ChatManager getChatManager() {
		return this.chatManager;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
