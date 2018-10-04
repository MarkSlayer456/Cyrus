package main;

import java.awt.Color;
import java.util.ArrayList;

import main.managers.ChatManager;
import main.managers.InputManager;
import main.managers.UIManager;

public class AI implements Runnable {
	private boolean thinking; // is Cyrus doing any computations at all
	private String name;
	private boolean hasGreeted; // has Cyrus introduced himself
	private Color color; // will implement later
	private int argsRemaining; // how many args is Cyrus waiting for if 0 then no args needed
	private String currentCommand; // used when dealing with arguments
	private ArrayList<String> args;
	private InputManager inputManager;
	private UIManager uiManager;
	private ChatManager chatManager;
	
	public AI(String n, InputManager input, UIManager ui, ChatManager chat) { // Only create one AI
		this.name = n;
		this.hasGreeted = false;
		this.color = Color.CYAN;
		this.argsRemaining = 0;
		this.currentCommand = "";
		this.args = new ArrayList<String>();
		this.thinking = true;
		this.inputManager = input;
		this.uiManager = ui;
		this.chatManager = chat;
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
	
	public void exitArgInterpreter() {
		this.currentCommand = "";
		this.args.clear();
		this.argsRemaining = 0;
	}
	
	// the a in this method would just be this.args so it's kinda pointless
	public void interpretArgs(ArrayList<String> a, String cmd) { //TODO edit this
		for(int i = 0; i < a.size(); i++) {
			String arg = a.get(i);
			switch(this.currentCommand) { 
			// TODO this is just a test and it works, createkeybind is not finished
			case "createkeybind":
				System.out.println("createkeybind is the command what are you args?");
				char z; // this will be used later
				if(i == 0) { // first arg
					if(arg.length() - 1 <= 0) {
						z = arg.charAt(i); // gets the value of the first key you type
					} else {
						outputMessage("Your first argument was invalid, exiting command");
						exitArgInterpreter();
						break;
					}
				} else if(i == 1) { // second arg
					switch(arg) {
					case "close":
						Frame.mainFrame.close();
						outputMessage("closing the frame...");
						break;
					default:
						outputMessage("Your second argument was invalid, exiting command");
						exitArgInterpreter(); // last arg don't need to exit
					}
				}
			}
		}
	}
	ArrayList<String> knownWords = new ArrayList<String>();
	boolean added = false;
	public boolean checkIfGibberish(String str) {
		//TODO also loop through args
		if(!added) {
			knownWords.add("Hello".toLowerCase());
			knownWords.add("Cyrus".toLowerCase());
			knownWords.add("Hi".toLowerCase());
			knownWords.add("Hey".toLowerCase());
			knownWords.add("good".toLowerCase());
			knownWords.add("morning".toLowerCase());
			added = true;
		}
		if(knownWords.contains(str.toLowerCase())) {
			return false;
		}
		return true;
	}
	
	public void interpret(String cmd) {
		// testing //
		String output = "";
		ArrayList<String> cmdArray = new ArrayList<String>();
		for(String s : cmd.split(" ")) { // gets each word typed 
			/*if(checkIfGibberish(s.toLowerCase())) {
				outputMessage("Some of the words you typed are unknown to me... sorry!");
				return;
			}*/
			cmdArray.add(s.toLowerCase());
		}
		//TODO update this for unlimited amount of arguments
		if(cmdArray.size() == 1) {
			switch(cmdArray.get(0).toLowerCase()) { // looking at the command name
			case "hello":
			case "hi":
			case "hey":
			case "hola":
				output += "Hello!";
				 //TODO just to void the stuff below for a minute
				
			}
		} else if(cmdArray.size() == 2) {
			switch(cmdArray.get(0)) {
			case "hello":
			case "hi":
			case "hey":
			case "hola":
				output += "Hello!";
				System.out.println(output);
				break;
			default:
				output += "This is gibberish!";
				return;
			}
			switch(cmdArray.get(1)) { // get second arg
				case "hello":
				case "hi":
				case "hey":
				case "hola":
					output += " Hello!";
					break;
				default:
					output += " This is gibberish!";
					return;
			}
		}
		outputMessage(output);
		
		if(output != null) {
			return;
		}
		if(cmd.contains("is cyrus")) {
			outputMessage("Wait.. I'm Cyrus why are you refering to me as Cyrus when you could say you?");
			return;
		}
		
		///////////////////////
		//TODO make it split the command into different strings if there are spaces and then run the interpret args method
		//TODO finish this
		if(this.argsRemaining >= 1) {
			switch(this.currentCommand) { // each new command will have to be added here if it applies
			case "createkeybind":
				this.args.add(cmd);
				interpretArgs(this.args, cmd);
				break;
			case "good": //TODO not done
				this.args.add(cmd);
				interpretArgs(this.args, cmd);
			}
			this.argsRemaining--;
			return;
		} else {
			exitArgInterpreter();
		}
		System.out.println("interpreting " + cmd);
		
		
		switch(cmd.toLowerCase()) { // all commands go under this switch
		case "createkeybind":
			this.currentCommand = cmd; //TODO move to prompt if it makes sense later
			prompt(cmd, 2);
			break;
			// greetings //
			// could add multiple responses
			// could add cases where if you misspell a word cyrus will correct you or try
		case "hi":
		case "hey":
		case "hello":
		case "hola":
			outputMessage("Hello!");
			break;
		case "good morning": //TODO change
			outputMessage("Good morning!");
			//prompt(cmd, 1);
			break;
		case "die":
			outputMessage("That's kinda rude don't you think?");
			break;
			///////////////////////////////////
		default:
			//TODO make a file of responses for failed messages/commands
			outputMessage("I'm sorry I do not know what you want, I am terribly sorry about that!");
					
			break;
		}
	}
	
	public void prompt(String cmd, Integer arguments) { // Prompts user to type in an argument(s) for a certain command
		//TODO
		this.argsRemaining = arguments;
		outputMessage(cmd + " needs " +  arguments + " arguments please type them in now!");
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
