package main;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import main.managers.FileManager;

public class Command {
	
	//this could be attached to cyrus if you don't want it to be static but just testing for right now
	public static HashMap<String, Command> commands = new HashMap<String, Command>();
	public static HashMap<String, Command> aliasesS = new HashMap<String, Command>();
	//private HashMap<ArrayList<String>, ArrayList<String>> argResponse = new HashMap<ArrayList<String>, ArrayList<String>>();
	private ArrayList<String> aliases = new ArrayList<String>();
	
	/*for each argument there can be aliases and each argument has 
	* a string answer but I will want multiple answers
	* for each arg later so you can have a array of possible answers
	*/
	
	// Not sure how to deal with needing the AI information on all these methods yet will find a work around
		// maybe make this a commandManager and make AI have a commandManager
	private String command;
	private ArrayList<String> args = new ArrayList<String>(); // might be able to remove this but could be used to loop through all valid arguments later
	private ArrayList<String> helpString = new ArrayList<String>();
	private int amountOfArgs; // how many arguments the command needs. 0 being just the command -1 being unlimited
	
	public String prefixHelpString = "Use the command like this"; // this can stay static
	
	public Command(String cmd, int aoa, ArrayList<String> help, ArrayList<String> a) { // Creates a command
		this.command = cmd;
		this.amountOfArgs = aoa;
		this.helpString = help;
		this.aliases = a;
		commands.put(this.command, this);
		if(this.aliases != null) {
			for(String s : aliases) {
				aliasesS.put(s, this);
			}
		}
	}
	
	public ArrayList<String> getHelpString() {
		return this.helpString;
	}
	
	
	public static Command getCommand(String str) {
		Command cmd = null;
		
		// if it contains any non char value ignore it
		if(str.contains("?")) str = str.replace("?", " ");
		if(str.contains(".")) str = str.replace(".", " ");
		if(str.contains("!")) str = str.replace("!", " ");
		if(str.contains(",")) str = str.replace(",", " ");
			
		for(String s : str.split(" ")) { 
			if(cmd == null) {
				if(commands.get(s) != null) {
				cmd = commands.get(s);
				cmd.args.clear();
				} else if(aliasesS.get(s) != null) {
					cmd = aliasesS.get(s);
					cmd.args.clear();
				}
			} else {
				cmd.args.add(s);
			}
		}
		return cmd;
	}
	
	public ArrayList<String> getArgs() {
		return this.args;
	}
	
	public void executeCommand(AI ai) {
		int argSize = this.args.size(); // 0 being the first arg
		switch(this.command) {
		default:
			//TODO do something
			break;
		case "hi":
			ai.outputMessage("Hello!");
			break;
			
		case "quit":
			Frame.quit();
			break;
		case "createkeybind": // needs to args
			if(argSize == 2) {
				
			} else {
				ai.outputHelpMessage(this);
			}
			break;
			
		case "what":
			if(argSize >= 1) {
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
				} else {
					ai.outputHelpMessage(this);
				}
			} else {
				ai.outputHelpMessage(this);
			}
			break;
			
		case "how":
			if(argSize >= 1) {
				if(this.args.contains("weather")) {
					ai.outputMessage("<insert the weather here>");
				} else if(this.args.contains("are") && this.args.contains("you")) {
					ai.outputMessage("I am great!");
				} else {
					ai.outputHelpMessage(this);
				}
			} else {
				ai.outputHelpMessage(this);
			}
			break;
			
		case "math":
			if(!Frame.calc.isRunning()) {
				Frame.cyrusCalc.start();
			} else {
				if(Frame.calcFrame.getFrame().isVisible()) {
					ai.outputMessage("There is already a calculator open!");
				} else {
					Frame.calcFrame.getFrame().setVisible(true);
				}
			}
			break;
			
		case "open":
			//TODO this command causes issues use at own risk
			if(argSize > 1) {
				for(int i = 0; i < argSize; i++) {
					this.args.set(1, this.args.get(1) + this.args.get(i));
				}
			}
			try {
				String fileLoc = this.args.get(1);
				new ProcessBuilder(fileLoc).start();
			} catch (Exception e) {
				ai.outputMessage("This program can not be found; are you sure you're looking in the right place!");
			}
			break;
		case "clear":
			ai.getChatManager().clearConsoleLines();
			ai.outputMessage("I have cleared the console for you!");
			break;
		}
	}
	public static void setup(AI ai) { 
		FileManager fileMan = ai.getFileManager();
		// need to get a file here
		File file = new File("commands.cy");
		for(int k = 0; k < fileMan.readFullFile(file).size(); k++) {
			String command = fileMan.readFileLine(file, k);
			if(command.startsWith("- ")) { // make sure it's the command
				String helpStr = fileMan.readFileLine(file, k + 1); // reads the next line which is the help string
				helpStr = helpStr.replaceFirst("  - ", "");
				String numberOfArgs = fileMan.readFileLine(file, k + 2); // reads the next line which is the help string
				ArrayList<String> helpStrArray = new ArrayList<String>();
				helpStrArray.add(helpStr);
				numberOfArgs = numberOfArgs.replaceAll(" ", "");
				numberOfArgs = numberOfArgs.replaceAll("-", "");
				String copyCommands = fileMan.readFileLine(file, k + 3); // aliases
				copyCommands = copyCommands.replaceAll(" ", "");
				copyCommands = copyCommands.replaceAll("-", "");
				ArrayList<String> copyCommandArray = new ArrayList<String>();
				String[] copyCommandStrings = copyCommands.split(",");
				for(String str : copyCommandStrings ) {
					copyCommandArray.add(str);
				}
				command = command.replaceAll("-", "");
				command = command.replaceAll(" ", "");
				new Command(command, Integer.parseInt(numberOfArgs), helpStrArray, copyCommandArray);
			}
		}
	}
}
