package main;

import java.util.ArrayList;
import java.util.HashMap;

public class Command {
	
	//TODO redo this whole class
	
	//this could be attached to cyrus if you don't want it to be static but just testing for right now
	public static HashMap<String, Command> commands = new HashMap<String, Command>();
	
	private String command;
	private ArrayList<String> args = new ArrayList<String>();
	private int amountOfArgs; // how many arguments the command needs. 0 being just the command -1 being unlimited
	//TODO might be able to remove this
	/* The boolean tells if the argument was satisfied
	 * 
	 */
	//TODO make the command have a help string that tells you how to use the command
	public Command(String cmd, int aoa) { // Creates a command
		this.command = cmd;
		this.amountOfArgs = aoa;
		commands.put(this.command, this);
	}
	public static Command getCommand(String str) { // not needed
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
			//TODO loop through all commands
			switch(this.command) { // output what args you need
			case "createkeybind":
				ai.outputMessage("Use the command like this: ");
				ai.outputMessage("    --/>createkeybind <any single key> <operation>");
				break;
			case "good":
				ai.outputMessage("Use the command like this: ");
				ai.outputMessage("    --/>createkeybind <time of day>");
				break;
			default:
				ai.outputMessage("This command is unknown are you sure your spelling is correct?");
			}
			
			
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
			break;
		case "goodnight": // this is allowed to be one word
			ai.outputMessage("Goodnight!");
			break;
		case "createkeybind":
			//TODO
		case "good":
			//TODO
		}
		for(int i = 0; i < argSize; i++) {
			String compare = this.args.get(i).toLowerCase();
					
				if(i > this.amountOfArgs) { // make sure the args are needed and not just random
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
					}
				}
		}
	}
	
	public static void setup() { 
		new Command("createkeybind", 2); //TODO warning do not use this command
		new Command("good", 1); // good morning/evening/night
		new Command("goodmorning", 0); // give them an error saying good morning is not one word
		new Command("goodafternoon", 0);
		new Command("goodevening", 0);
		new Command("goodnight", 0);
		new Command("hi", 0);
		new Command("hey", 0);
		new Command("hello", 0);
		new Command("hola", 0);
	}

}
