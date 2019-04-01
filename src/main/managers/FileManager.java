package main.managers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileManager {
	
	public static FileManager fileManager = new FileManager();
	
	public static FileManager getInstance() {
		return fileManager;
	}
	
	private final String os = System.getProperty("os.name").toLowerCase();
	private final String user = System.getProperty("user.name").toLowerCase();
	
	public static final String COMMANDFILE = "commands.cy";
	public static final String JOKEFILE = "jokes.cy";
	public static final String ERRORMESSAGEFILE = "error messages.cy";
	public static final String COMMONQUESTIONFILE = "commonly asked questions.cy";
	
	private ArrayList<File> files = new ArrayList<File>(); // All the files the ai knows about
	/**
	 * Creates FileManager, this method is empty.
	 */
	public FileManager() {
		
	}
	
	/**
	 * Gets a file in the default Cyrus folder, this directory depends on the operating system
	 * and returns the file.
	 * @param name - The name of the file you want to retrieve 
	 * @return - Returns the file if it exists if not a new file ERROR.cy is created to stop the program from crashing
	 */
	public File getFile(String name) {
		try {
			File file;
			if(name.contains(".")) { // there is an extension
				if(os.equalsIgnoreCase("windows 10")) {
					file = new File("C:\\Users\\" + user + "\\AppData\\Local\\Cyrus\\" + name);
				} else {
					file = new File("/usr/cyrus/" + name);
				}
			} else {
				if(os.equalsIgnoreCase("windows 10")) {
					file = new File("C:\\Users\\" + user + "\\AppData\\Local\\Cyrus\\" + name + ".cy"); // the default extension
				} else {
					file = new File("/usr/cyrus/" + name + ".cy"); // the default extension
				}
			}
			if(file != null) {
				return file;
			}
		} catch(Exception e) {
			
		}
		// TODO check on startup if an error file exists and find a way to deal with it
		return new File("ERROR.cy"); // this way the program doesn't crash if a file isn't found should never happen
	}
	/**
	 * Returns each line of the file stored in an array of strings 0 being the first line of the file
	 * @param file - The file you want to read
	 * @return - A array of all the lines in the file 
	 */
	public ArrayList<String> readFullFile(File file) { // returns each line of the file stored in a array of strings
		try {
			ArrayList<String> temp = new ArrayList<String>();
			BufferedReader reader = new BufferedReader(new FileReader(file.toString()));
			try {
				String st; 
				while((st = reader.readLine()) != null) {
					temp.add(st);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			return temp;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * Reads a specified file line and returns it as a string.
	 * @param file - The file you want to read from
	 * @param lineNumb - The line number of the file
	 * @return - The line you specified
	 */
	public String readFileLine(File file, int lineNumb) {
		ArrayList<String> fileStr = readFullFile(file);
		try {
			return fileStr.get(lineNumb);
		} catch(Exception e) {
			return null; //TODO change this when finished
		}
	}
	
	//TODO this code seems to be unused
//	/**
//	 * Allows 
//	 * @param file
//	 * @param lineNumb
//	 * @param parent
//	 * @return
//	 */
//	public String getChildLine(File file, int lineNumb, String parent) { 
//		// reads a given line from a file and returns the line as a string
//		// or reads a line under a given parent string the first line under the parent string is 0
//		/* ex:
//		 * File
//		 * Family:
//		 * 	- Mark
//		 * 	- Jill
//		 * 	- Bob
//		 * typing readFileLine(File, 0, "family:");
//		 * will return "Mark"
//		 */
//			ArrayList<String> fileStr = readFullFile(file);
//			if(parent != "" && parent != null) { // they entered a parent string
//				if(fileStr.contains(parent) && fileStr.size() >= lineNumb) { // parent string is in the file
//					int index = fileStr.indexOf(parent); // get where the parent string is
//					lineNumb++; // this is because I want to use 0 for the first item under the parent but adding 0 will do nothing
//					return fileStr.get(index + lineNumb); // return the string below
//				} else { // the parent string is not in the file return nothing this shouldn't happen
//					return "Error: readParentLine, lineNumb was too big or the command does not exist!";
//				}
//			}
//		return "";
//	}
	
	/**
	 * Writes a line to a file at any lineNumb -1 appends the line to the end of the file.
	 * @param file - The name of the file in the default folder for Cyrus, this depends on the operating system in use
	 * @param line - What you want to add to the file
	 * @param lineNumb - The line number you want to add the line to, this will move the current line down and all lines below it down a line
	 * and add your line on
	 */
	public void writeToFile(File file, String line, int lineNumb) { // writes a line to a file
		//TODO fix this method
		// if lineNumb is -1 append to file
		/*if(this.readFullFile(file).contains(line)) { // stops from writing the same thing
			return;
		}*/
		if(this.readFileLine(file, lineNumb) != null) return;
		//if(this.readFileLine(file, lineNumb).equalsIgnoreCase("- hi")) return;
	    try {
	    	BufferedWriter writer = new BufferedWriter(new FileWriter(file.toString(), true));
	    	writer.append(line); // removed to lower case here
			writer.newLine();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Creates a file in the default path for Cyrus, this path depends on the operating system
	 * of the user.
	 * @param name - The name of the file you want to create
	 * @return - The created file
	 */
	public File createFile(String name) {
		File file;
		if(name.contains(".")) { // there is an extension
			file = new File(name);
		} else {
			file = new File(name + ".cy"); // the default extension
		}
		file.getParentFile().mkdirs();
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		this.files.add(file);
		return file;
	}
	
	/**
	 * Setups the default files and all there values.
	 * This makes it so users cannot mess with Cyrus's commands
	 */
	public void setup() {
		try {
			File command;
			File errorMessages;
			File commonQuestions;
			File jokes;
			if(os.equalsIgnoreCase("windows 10")) {
				command = createFile("C:\\Users\\" + user + "\\AppData\\Local\\Cyrus\\" + COMMANDFILE);
				errorMessages = createFile("C:\\Users\\" + user + "\\AppData\\Local\\Cyrus\\" + ERRORMESSAGEFILE);
				commonQuestions = createFile("C:\\Users\\" + user + "\\AppData\\Local\\Cyrus\\" + COMMONQUESTIONFILE);
				jokes = createFile("C:\\Users\\" + user + "\\AppData\\Local\\Cyrus\\" + JOKEFILE);
			} else {
				command = createFile("/usr/cyrus/" + COMMANDFILE);
				errorMessages = createFile("/usr/cyrus/" + ERRORMESSAGEFILE);
				commonQuestions = createFile("/usr/cyrus/" + COMMONQUESTIONFILE);
				jokes = createFile("/usr/cyrus/" + JOKEFILE);
			}
			int i = 0;
				writeToFile(command, "- hi", i++);
				writeToFile(command, "  - <hi/hey/hello/hola>", i++); // this would be the help string
				writeToFile(command, "    - 0", i++); // amount of args
				writeToFile(command, "      - hey, hello, hola", i++);
				
				writeToFile(command, "- createkeybind", i++);
				writeToFile(command, "  - <createkeybind> <key> <operation>", i++);
				writeToFile(command, "    - 2", i++);
				writeToFile(command, "      - ", i++);
				
				writeToFile(command, "- what", i++);
				writeToFile(command, "  - <what/what's> <question>", i++);
				writeToFile(command, "    - 1", i++);
				writeToFile(command, "      - what's", i++);
				
				writeToFile(command, "- math", i++);
				writeToFile(command, "  - <math>", i++);
				writeToFile(command, "    - 0", i++);
				writeToFile(command, "      - calc, calculator", i++);
				
				writeToFile(command, "- how", i++);
				writeToFile(command, "  - <how/how's/how'd> <question>", i++);
				writeToFile(command, "    - 1", i++);
				writeToFile(command, "      - how's, how'd", i++);
				
				writeToFile(command, "- clear", i++);
				writeToFile(command, "  - <clear>", i++);
				writeToFile(command, "    - 0", i++);
				writeToFile(command, "      - cl", i++);
				
				writeToFile(command, "- quit", i++);
				writeToFile(command, "  - <quit>", i++);
				writeToFile(command, "    - 0", i++);
				writeToFile(command, "      -", i++);
				
				writeToFile(command, "- joke", i++);
				writeToFile(command, "  - <joke>", i++);
				writeToFile(command, "    - 0", i++);
				writeToFile(command, "      -", i++);
				
				// TODO None of the commands below this line are implemented 
				////////////////////////////////////////////////////////////////////////////////////
				writeToFile(command, "- todo", i++);
				writeToFile(command, "  - <todo> <add/remove> <task> <reminder time> <reminder interval>", i++);
				writeToFile(command, "    - 0", i++);
				writeToFile(command, "      - td", i++);
				
				writeToFile(command, "- set", i++);
				writeToFile(command, "  - <set> <timer/countdown> <length>", i++);
				writeToFile(command, "    - 0", i++);
				writeToFile(command, "      - create", i++);
				
				writeToFile(command, "- ls", i++);
				writeToFile(command, "  - <ls> <directory>", i++);
				writeToFile(command, "    - 0", i++);
				writeToFile(command, "      - list", i++);
				
				writeToFile(command, "- cd", i++);
				writeToFile(command, "  - <cd> <directory>", i++);
				writeToFile(command, "    - 0", i++);
				writeToFile(command, "      - changedirectory", i++);
				/////////////////////////////////////////////////////////////////////////////////////
				
				int j = 0;
				
				writeToFile(errorMessages, "I'm sorry I don't understand what you are trying to say.", j++);
				writeToFile(errorMessages, "Are you sure your words make sense?", j++);
				writeToFile(errorMessages, "ERROR: please check your command and make sure it is correct!", j++);
				writeToFile(errorMessages, "This is an unknown word or phrase sorry I can't help you!", j++);
			
				int i1 = 0;
				
				writeToFile(commonQuestions, "", i1++);
				writeToFile(commonQuestions, "", i1++);
				writeToFile(commonQuestions, "", i1++);
				writeToFile(commonQuestions, "", i1++);
				
				int i2 = 0;
				
				writeToFile(jokes, "Yo' mama so stupid, she walked into an antique shop and asked, \"What's new?\"", i2++);
				writeToFile(jokes, "Your Mama's so fat that when she went to school she sat next to the whole class!", i2++);
				writeToFile(jokes, "Yo mamma's so fat, she tripped on 4th Avenue and landed on 12th.", i2++);
				writeToFile(jokes, "What do you call 500 lawyers at the bottom of the ocean?" + "A good start.", i2++);
				
				
			/*
			String test = readFileLine(file, 0, "greetings:");
			test = test.replace(" ", "");
			test = test.replace("-", "");
			System.out.println(test); //TODO this is a test it works!
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
