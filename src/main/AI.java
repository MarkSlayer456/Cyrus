package main;

import java.awt.Color;
import java.util.ArrayList;

import main.managers.ChatManager;
import main.managers.FileManager;
import main.managers.InputManager;
import main.managers.UIManager;
import main.utilities.Calculator;

public class AI implements Runnable {
	private boolean thinking; // is Cyrus doing any computations at all
	private String name;
	private boolean hasGreeted; // has Cyrus introduced himself
	private Color color; // will implement later
	private ArrayList<String> args;
	private InputManager inputManager;
	private UIManager uiManager;
	private ChatManager chatManager;
	private FileManager fileManager;
	private Calculator calc;
	
	public AI(String n, InputManager input, UIManager ui, ChatManager chat, FileManager file, Calculator c) { // Only create one AI
		this.name = n;
		this.hasGreeted = false;
		this.color = Color.CYAN;
		this.args = new ArrayList<String>();
		this.thinking = true;
		this.inputManager = input;
		this.uiManager = ui;
		this.chatManager = chat;
		this.fileManager = file;
		this.calc = c;
	}
	
	public FileManager getFileManager() {
		return this.fileManager;
	}
	
	public ChatManager getChatManager() {
		return this.chatManager;
	}
	
	public UIManager getUIManager() {
		return this.uiManager;
	}
	
	public InputManager getInputManager() {
		return this.inputManager;
	}
	
	
	public void setup() {
		//TODO ask for name
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
		if(!this.hasGreeted) {
		this.greet();
		}
	}
	
	public void outputMessage(String str) { // output a message to Cyrus/console
		//TODO finish this
		this.getChatManager().seperateLines(str);
	}
	
	public void interpret(String cmd) {
		if(Command.getCommand(cmd) != null) {
		Command.getCommand(cmd).executeCommand(this);
		} else {
			outputMessage("I'm sorry I don't know what you are trying to say right now.");
			return;
		}
	}
	
	@Override
	public void run() { // Cyrus thought process
		while(this.thinking) {
			this.logic();
		}
	}
	
	
	
	///// Methods that Cyrus responds to /////
	// Just test methods at the moment to test if cyrus is working
	public String speak() { // Method is mostly for testing will be changed later as the starting message
		return null;
	}	
}
