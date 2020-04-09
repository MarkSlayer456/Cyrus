package main;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import main.managers.ChatManager;
import main.managers.FileManager;
import main.managers.NoteManager;

public class AI {
	
	FileManager fileManager = FileManager.getInstance();
	
	private String name;
	private boolean hasGreeted; // has Cyrus introduced himself
	private Color color; // will implement later
	private ArrayList<String> args;
	private final ChatManager chatManager;
	private NoteManager noteManager;
	private List<String> commands; // a list of all commands entered the first item being the last command
	int currCommand;
	
	/**
	 * 
	 * @param name - Name of the AI
	 * @param chat - ChatManager for the AI to display the console
	 */
	public AI(String name, ChatManager chat, NoteManager noteManager) {
		this.name = name;
		this.hasGreeted = false;
		this.color = Color.CYAN;
		this.args = new ArrayList<>();
		this.chatManager = chat;
		this.noteManager = noteManager;
		this.noteManager.loadNotes();
		this.commands = new ArrayList<>();
		this.currCommand = -1;
	}
	
	
	/**
	 * Sets up the AI.
	 * This method currently just greets the user.
	 */
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
	 * @param message - What you want to output to the console
	 */
	public void outputMessage(String message) {
		ArrayList<String> line = this.getChatManager().separateLines(message);
		for(String l : line) 
			this.getChatManager().addConsoleLine(l);
	}
	/**
         * Outpus help message
         * @param cmd - The command's help message you want to use
         */
	public void outputHelpMessage(Command cmd) {
		this.outputMessage(cmd.getPrefixHelpString());
		this.outputMessage(cmd.getHelpString().get(0));
	}
	
	public void outputErrorMessage() {
		File file = fileManager.getFile(FileManager.ERRORMESSAGEFILE);
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
	/**
	 * Gets name
	 * @return - The name of the AI
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * Gets ChatManager
	 * @return - The ChatManager for the AI
	 */
	public ChatManager getChatManager() {
		return this.chatManager;
	}
	
	/**
	 * Gets Note manager
	 * @return - The Note manager for the AI
	 */
	public NoteManager getNoteManager() {
		return this.noteManager;
	}
	
	public int getCurrCommand() {
		return this.currCommand;
	}
	
	public void increaseCurrCommand() {
		this.currCommand++;
	}
	
	public void decreaseCurrCommand() {
		this.currCommand--;
	}
	
	public void setCurrCommand(int numb) {
		this.currCommand = numb;
	}
	
	public void addToCommands(String cmd) {
		this.commands.add(0, cmd);
	}
	
	public List<String> getCommands() {
		return this.commands;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
