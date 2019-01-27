package main;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import main.managers.ChatManager;
import main.managers.FileManager;
import main.managers.InputManager;
import main.managers.UIManager;
import main.utilities.Calculator;

public class AI implements Runnable {
	public static boolean thinking; // is Cyrus doing any computations at all
	private String name;
	private boolean hasGreeted; // has Cyrus introduced himself
	private Color color; // will implement later
	private ArrayList<String> args;
	private final InputManager inputManager;
	private final ChatManager chatManager;
	private final FileManager fileManager;
	private final Calculator calc;
	
	public AI(String n, InputManager input, ChatManager chat, FileManager file, Calculator c) { // Only create one AI
		this.name = n;
		this.hasGreeted = false;
		this.color = Color.CYAN;
		this.args = new ArrayList<String>();
		this.inputManager = input;
		this.chatManager = chat;
		this.fileManager = file;
		this.calc = c;
                
                // static vars //
                thinking = true;
	}
	
	public FileManager getFileManager() {
		return this.fileManager;
	}
	
	public ChatManager getChatManager() {
		return this.chatManager;
	}
	
	public InputManager getInputManager() {
		return this.inputManager;
	}
	
	public void setup() {
		//TODO ask for name
		// check if setup file exists and make sure nothing is invalid
	}
	
	public void greet() {
		//TODO this is just for testing
		if(!this.hasGreeted) {
			this.hasGreeted = true;
			outputMessage("Hello, my name is " + this.name + ", this message is brought to you from the AI.java file under the greet method!");
		}
	}
	
	public String getName() {
		return this.name;
	}

	public void logic() { // The thinking method
                    this.greet();
	}
	
	public void outputMessage(String str) { // output a message to Cyrus/console
		//TODO finish this
		this.getChatManager().seperateLines(str);
	}
	
	public void outputHelpMessage(Command cmd) {
		this.outputMessage(cmd.prefixHelpString);
		this.outputMessage(cmd.getHelpString().get(0));
	}
	
	public void outputErrorMessage() {
		String user = System.getProperty("user.name").toString().toLowerCase();
		File file = this.getFileManager().getFile("C:\\Users\\" + user + "\\AppData\\Local\\Cyrus\\error messages.cy");
		Random r = new Random();
		int ran = r.nextInt(this.getFileManager().readFullFile(file).size());
		this.outputMessage(this.getFileManager().readFileLine(file, ran));
	}
	
	public void interpret(String cmd) {
		if(Command.getCommand(cmd) != null && !cmd.equalsIgnoreCase("")) {
		Command.getCommand(cmd).executeCommand(this);
		} else {
			this.outputErrorMessage();
		}
	}
	
	@Override
	public void run() { // Cyrus thought process
		while(thinking) {
			this.logic(); //TODO Cyrus logic doesn't do anything
		}
	}
	
	
	
	///// Methods that Cyrus responds to /////
	// Just test methods at the moment to test if cyrus is working
	public String speak() { // Method is mostly for testing will be changed later as the starting message
		return null;
	}	
}
