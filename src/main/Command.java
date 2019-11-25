package main;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Random;

import main.managers.FileManager;

public class Command {
    
	private static FileManager fileManager = FileManager.getInstance();
	private CyrusMain cyrusMain;
	
	private final String prefixHelpString = "Use the command like this:";
	
	//this could be attached to cyrus if you don't want it to be static but just testing for right now
	private static HashMap<String, Command> commands = new HashMap<>();
	private static HashMap<String, Command> aliasesS = new HashMap<>();
	private ArrayList<String> aliases = new ArrayList<>(); //TODO maybe remove this
	private static ArrayList<String> commandStrings = new ArrayList<>();
	// Not sure how to deal with needing the AI information on all these methods yet will find a work around
	private String command;
	private ArrayList<String> args = new ArrayList<>(); // might be able to remove this but could be used to loop through all valid arguments later
	private ArrayList<String> helpString = new ArrayList<>();
	private int amountOfArgs; // how many arguments the command needs. 0 being just the command -1 being unlimited
	private String manual;
	
	
	/**
	 * Creates a command
	 * @param cmd - The name of the command
	 * @param aoa - The amount of arguments
	 * @param help - The help string for the command
	 * @param a - All the aliases for the command
	 */
	public Command(String cmd, int aoa, ArrayList<String> help, ArrayList<String> a, String manual) {
		this.command = cmd;
		this.amountOfArgs = aoa;
		this.helpString = help;
		this.aliases = a;
		this.manual = manual;
		commands.put(this.command, this);
		commandStrings.add(cmd);
		System.out.println(this.command + " was created");
		if(this.aliases != null) {
			for(String s : aliases) {
				aliasesS.put(s, this);
			}
		}
		cyrusMain = CyrusMain.getInstance();
	}
	
	/**
	 * Attempts to execute a command for the given AI
	 * @param ai - The AI you want to try to execute the command
	 */
	public void executeCommand(AI ai) {
		int argSize = this.args.size(); // 0 being the first arg
		switch(this.command) {
		case "": // pretty much the same thing
		default:
			ai.outputErrorMessage();
			break;
		case "hi":
			ai.outputMessage("Hello!");
			break;
			
		case "quit":
			CyrusMain.quit();
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
					ai.outputMessage(new SimpleDateFormat("HH:mm:ss dd/MM/yyyy").format(Calendar.getInstance().getTime()));
				} else if(this.args.contains("you") && (this.args.contains("doing")) || (this.args.contains("up") && this.args.contains("to"))) {
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
				cyrusMain.getCalc().getFrame().setDrawing(true);
				cyrusMain.getCalc().makeVisible();
			break;
		case "clear":
			ai.getChatManager().clearConsoleLines();
			ai.outputMessage("I have cleared the console for you!");
			break;
		case "joke":
			File file = fileManager.getFile(FileManager.JOKEFILE);
			Random r = new Random();
			int ran = r.nextInt(fileManager.readFullFile(file).size());
			ai.outputMessage(fileManager.readFileLine(file, ran));
			break;
		case "help":
			for(String command : commandStrings) {
				ai.outputMessage(command + " - " + Command.getCommand(command).getManual());
			}
			break;
		}
	}
	
	/**
	 * Detects all the commands in the file
	 */
	public static void discoverCommands() {
		File file = fileManager.getFile(FileManager.COMMANDFILE);
		for(int k = 0; k < fileManager.readFullFile(file).size(); k += 5) {
			String command = fileManager.readFileLine(file, k);
			if(command.startsWith("- ")) { // make sure it's the command
				String helpStr = fileManager.readFileLine(file, k + 1); // reads the next line which is the help string
				helpStr = helpStr.replaceFirst("  - ", "");
				String numberOfArgs = fileManager.readFileLine(file, k + 2); // reads the next line which is the number of args
				ArrayList<String> helpStrArray = new ArrayList<>();
				helpStrArray.add(helpStr);
				numberOfArgs = numberOfArgs.replaceAll(" ", "");
				numberOfArgs = numberOfArgs.replaceAll("-", "");
				String copyCommands = fileManager.readFileLine(file, k + 3); // aliases
				copyCommands = copyCommands.replaceAll(" ", "");
				copyCommands = copyCommands.replaceAll("-", "");
				ArrayList<String> copyCommandArray = new ArrayList<>();
				String[] copyCommandStrings = copyCommands.split(",");
				String manual = fileManager.readFileLine(file, k + 4);
				manual = manual.replaceFirst("        ", "");
				manual = manual.replaceAll("-", "");
				System.out.println(manual);
				System.out.println(command);
				
                copyCommandArray.addAll(Arrays.asList(copyCommandStrings));
				command = command.replaceAll("-", "");
				command = command.replaceAll(" ", "");
				new Command(command, Integer.parseInt(numberOfArgs), helpStrArray, copyCommandArray, manual);
			}
		}
	}
	
	///// Setters /////
	
	///// Getters /////
	
	/**
	 * gets the manual page for each command
	 * @return the manual page for the given command
	 */
	public String getManual() {
		return this.manual;
	}
	
	/**
	 * Gets command
	 * @param str - The name of the command
	 * @return - The command if the name given is a command
	 */
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
	/**
	 * Gets arguments for command
	 * @return - An array of the arguments for the command
	 */
	public ArrayList<String> getArgs() {
		return this.args;
	}
	/**
	 * Gets the help string
	 * @return - An array of the help string of the command
	 */
	public ArrayList<String> getHelpString() {
		return this.helpString;
	}
	/**
	 * Gets prefix help string
	 * @return - The help string to prefix most help messages
	 */
	public String getPrefixHelpString() {
		return this.prefixHelpString;
	}
	
}
