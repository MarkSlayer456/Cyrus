package main;

import java.util.ArrayList;
import java.util.HashMap;

public class Command {
	
	//TODO redo this whole class
	
	//this could be attached to cyrus if you don't want it to be static but just testing for right now
	public static ArrayList<Command> allKnownCommands = new ArrayList<Command>(); // Info should be obtained from a file anyway just testing
	public static HashMap<String, ArrayList<String>> commandArgs = new HashMap<String, ArrayList<String>>(); // the string command and the array of args
	
	private String command;
	private ArrayList<String> args = new ArrayList<String>();
	/* If a command has overrideAcceptedArg as true it is added to all acceptedArgs 
	 * 
	 */
	
	public Command(String cmd, ArrayList<String> a) { // Creates a command
		this.command = cmd.toLowerCase();
		this.args = a;
	}
	
	public String getCommand() {
		return this.command.toLowerCase();
	}
	
	public ArrayList<String> args() {
		return this.args;
	}
	
	public void executeCommand() {
		switch(this.command.toLowerCase()) {
		case "hey":
		case "hi":
		case "hello":
			Frame.cyrus.outputMessage("Hello!");
			break;
		}
	}
	
	public static void setup() { // creates all commands
		allKnownCommands.add(new Command("hi", null));
		allKnownCommands.add(new Command("hey", null));
		allKnownCommands.add(new Command("hello", null));
		ArrayList<String> goodMorningA = new ArrayList<String>();
		goodMorningA.add("morning");
		allKnownCommands.add(new Command("good", goodMorningA));
		
	}

}
