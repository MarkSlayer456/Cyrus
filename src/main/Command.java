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
	private boolean entered; // was the command entered
	//TODO might be able to remove this
	/* The boolean tells if the argument was satisfied
	 * 
	 */
	
	public Command(String cmd, int aoa) { // Creates a command
		this.command = cmd;
		this.amountOfArgs = aoa;
		this.entered = false;
		commands.put(this.command, this);
	}
	
	public static Command getCommand(String str) { // not needed
		System.out.println(str + " is being converted to a command");
		Command cmd = null;
		for(String s : str.split(" ")) { //TODO update this method
			if(cmd == null) {
				cmd = commands.get(s);
				cmd.args.clear();
			} else {
				cmd.args.add(s);
			}
			if(cmd == null) {
				Frame.cyrus.outputMessage("error unknown command");
				return null; //TODO output message
			}
		}
		return cmd; //TODO make a output message saying unknown command
	}
	
	public ArrayList<String> getArgs() {
		return this.args;
	}
	
	public void executeCommand(AI ai) {
		int argSize = this.args.size(); // 0 being the first arg
		if(this.command == "hey" || this.command == "hi" || this.command == "hello" || this.command == "hola") {
			ai.outputMessage("Hello!");
		} else if(this.command == "createkeybind") {
			this.entered = true;
		}
		for(int i = 0; i < argSize; i++) {
			String compare = this.args.get(i).toLowerCase();
					
				if(i > this.amountOfArgs || !this.entered) { // make sure the args are needed and not just random
					ai.outputMessage("This command doesn't support that many args, however the command was still executed!");
					return;
				} else { // the args
					// createkeybind args //
					System.out.println("i = " + i + "  |  compare = " + compare + "  |  command = " + this.command);		
					if(this.command == "createkeybind") {
						if(i == 0) {
							// TODO add code
						} else if(i == 1) {
							if(compare == "close") {
								ai.outputMessage("Closing the frame...");
								//Frame.mainFrame.close(); // this is for testing don't use this command from the desktop
							}
						}
					}
				}
				//TODO you have to use if else because break; breaks the for loop
				/*switch(this.args.get(i).toLowerCase()) { // the command
				case "hey":
				case "hi":
				case "hello":
				case "hola":
					System.out.println("Hello!");
					//TODO add output
					return; // these commands have no arguments
				case "createkeybind":
					System.out.println("createkeybind!");
					break;
				default:
					return; // stop the method as the command is unknown
				}
				this.entered = true;
			} else { // looking at arguments
				System.out.println("yep");
				if(i > this.amountOfArgs) {
					System.out.println("I do not understand!");
				} else {
					if(this.entered) { // if the command was entered
						switch(this.args.get(i).toLowerCase()) {
						case "1":
							System.out.println("your pressed 1");
							break;
						case "close":
							if(i == 2) {
							System.out.println("1 set to the close operation");
							} else {
								System.out.println("error");
							}
							break;
						default:
							System.out.println("error");
						}
					} else {
						System.out.println("what?");
					}
				}
			}*/
			
			
			
		}
	}
	
	public static void setup() { 
		new Command("createkeybind", 2);
		new Command("hi", 0);
		new Command("hey", 0);
		new Command("hello", 0);
		new Command("hola", 0);
	}

}
