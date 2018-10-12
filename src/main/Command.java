package main;

import java.util.ArrayList;
import java.util.HashMap;

public class Command {
	
	//this could be attached to cyrus if you don't want it to be static but just testing for right now
	public static HashMap<String, Command> commands = new HashMap<String, Command>();
	//TODO make a array list of strings so you can make alias 
	//TODO make the command have a help string that tells you how to use the command
	//private HashMap<ArrayList<String>, ArrayList<String>> argResponse = new HashMap<ArrayList<String>, ArrayList<String>>();
	//TODO finish this later
	private ArrayList<String> alias = new ArrayList<String>();
	
	/*for each argument there can be aliases and each argument has 
	* a string answer but I will want multiple answers
	* for each arg later so you can have a array of possible answers
	*/
	
	private String command;
	private ArrayList<String> args = new ArrayList<String>();
	private ArrayList<String> helpString = new ArrayList<String>();
	private int amountOfArgs; // how many arguments the command needs. 0 being just the command -1 being unlimited
	//TODO might be able to remove this
	
	public Command(String cmd, int aoa, ArrayList<String> help) { // Creates a command
		this.command = cmd;
		this.amountOfArgs = aoa;
		this.helpString = help;
		commands.put(this.command, this);
	}
	public static Command getCommand(String str) {
		System.out.println(str + " is being converted to a command");
		Command cmd = null;
		for(String s : str.split(" ")) { //TODO update this method
			if(cmd == null) {
				if(commands.get(s) != null) {
				cmd = commands.get(s);
				cmd.args.clear();
				}
			} else {
				cmd.args.add(s);
			}
		}
		return cmd; //TODO make a output message saying unknown command
	}
	
	public ArrayList<String> getArgs() {
		return this.args;
	}
	
	public void executeCommand(AI ai) {
		int argSize = this.args.size(); // 0 being the first arg
		if(!(argSize >= this.amountOfArgs)) { // not enough args were entered
				ai.outputMessage(this.command);
				ai.outputMessage("Use the command like this: ");
				ai.outputMessage(this.helpString.get(0));
			return;
		}
		
		switch(this.command) {
		case "hi":
		case "hey":
		case "hello":
		case "hola":
			ai.outputMessage("Hello!");
			break;
		case "goodmorning":
		case "goodevening":
		case "goodafternoon":
			ai.outputMessage("There is a space after good silly.");
			ai.outputMessage(this.helpString.get(0));
			break;
		case "goodnight": // this is allowed to be one word
			ai.outputMessage("Goodnight!");
			break;
		case "createkeybind":
			//TODO
		case "good":
			//TODO
		case "what's":
		case "what":
			if(this.args.contains(" ") || this.args.isEmpty()) {
				ai.outputMessage("What would you like to know?");
			}
			break;
			//TODO 
		}
		for(int i = 0; i < argSize; i++) {
			String compare = this.args.get(i).toLowerCase();
				if(i > this.amountOfArgs && this.amountOfArgs != -1) { // make sure the args are needed and not just random
					ai.outputMessage("This command doesn't support that many args, however the command was still executed!");
					return;
				} else { // the args
					// createkeybind args //
					if(this.command.equalsIgnoreCase("createkeybind")) { // this can't be a switch because using a break will break the for loop
						if(i == 0) {
							ai.outputMessage("This is a test message you typed 1");
							// TODO add code
						} else if(i == 1) {
							if(compare.equalsIgnoreCase("close")) {
								ai.outputMessage("Closing the frame...");
								Frame.mainFrame.close(); // this is for testing don't use this command from the desktop
							}
						}
						// good args //
					} else if(this.command.equalsIgnoreCase("good")) { //TODO maybe later make it so Cyrus can tell time and tell them based off time
						if(i == 0) {
							if(compare.equalsIgnoreCase("morning")) {
								ai.outputMessage("Good morning!");
							} else if(compare.equalsIgnoreCase("evening")) {
								ai.outputMessage("Good evening!");
							} else if(compare.equalsIgnoreCase("night")) {
								ai.outputMessage("Good night!");
							} else if(compare.equalsIgnoreCase("afternoon")) {
								ai.outputMessage("Good afternoon!");
							}
						}
					} else if(this.command.equalsIgnoreCase("what") || this.command.equalsIgnoreCase("what's")) { //TODO finish this 
						if(this.args.contains("weather")) {
							ai.outputMessage("<insert the weather here>");
						} else if(this.args.contains("favorite") && this.args.contains("color")) {
							ai.outputMessage("My favorite color is blue!");
						} else if(this.args.contains("favorite") && this.args.contains("game")) {
							ai.outputMessage("I hate all games they just seem to be too easy to me!");
						} else if(this.args.contains("time")) {
							ai.outputMessage("<insert system time here>");
						} else if(this.args.contains("you") && this.args.contains("doing")) {
							ai.outputMessage("I'm helping you of course!");
						} else if(this.args.contains("happening") || this.args.contains("up")) {
							ai.outputMessage("<insert the news>");
						}
						return; // otherwise the loop will print the message multiple times
						
						
					}
				}
		}
	}
	public static void setup() { 
		ArrayList<String> a = new ArrayList<String>(); // This will be changed later
		a.add("createkeybind <key> <operation>");
		new Command("createkeybind", 2, a); //TODO warning do not use this command
		ArrayList<String> a1 = new ArrayList<String>();
		a1.add("good <time of day>");
		new Command("good", 1, a1); // good morning/evening/night
		// Error Commands // TODO maybe make a class called ErrorCommands
		// since these are error commands there isn't a way you're suppose to enter them
		ArrayList<String> a2 = new ArrayList<String>();
		ArrayList<String> a3 = new ArrayList<String>();
		ArrayList<String> a4 = new ArrayList<String>();
		new Command("goodmorning", 0, a2); // give them an error saying good morning is not one word
		new Command("goodafternoon", 0, a3);
		new Command("goodevening", 0, a4);
		// Error Commands //
		ArrayList<String> a5 = new ArrayList<String>();
		a5.add("goodnight");
		new Command("goodnight", 0, a5);
		ArrayList<String> a6 = new ArrayList<String>();
		a6.add("hey");
		a6.add("hi");
		a6.add("hello");
		a6.add("hola");
		new Command("hi", 0, a6);
		new Command("hey", 0, a6);
		new Command("hello", 0, a6);
		new Command("hola", 0, a6);
		ArrayList<String> a7 = new ArrayList<String>();
		a7.add("what <question>");
		new Command("what", -1, a7);
		a7.add("what's <question>");
		new Command("what's", -1, a7); // this won't exist later
	}

}
