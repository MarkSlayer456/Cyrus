package main.managers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
	public static final String NOTEFILE = "notes.cy";
	public static final String TODOFILE = "todo.cy";
	
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
	
	/**
	 * Writes a line to a file at any lineNumb -1 appends the line to the end of the file.
	 * @param file - The name of the file in the default folder for Cyrus, this depends on the operating system in use
	 * @param line - What you want to add to the file
	 * @param lineNumb - The line number you want to add the line to, this will move the current line down and all lines below it down a line
	 * and add your line on
	 */
	public void writeToFile(File file, String line, int lineNumb, boolean append) { // writes a line to a file
		//TODO fix this method
		// if lineNumb is -1 append to file
		/*if(this.readFullFile(file).contains(line)) { // stops from writing the same thing
			return;
		}*/
		if(file.exists()) {
			//if(this.readFileLine(file, lineNumb) != null) return;
			if(append) {
				  try {
				    	BufferedWriter writer = new BufferedWriter(new FileWriter(file.toString(), append));
				    	writer.append(line); // removed to lower case here
						writer.newLine();
						writer.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
			} else {
		        try {
		        	FileOutputStream fileOut = new FileOutputStream(file);
					fileOut.write(line.getBytes());
					fileOut.write(System.lineSeparator().getBytes());
					fileOut.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		//if(this.readFileLine(file, lineNumb).equalsIgnoreCase("- hi")) return;
	  
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
			File notes;
			if(os.equalsIgnoreCase("windows 10")) {
				command = createFile("C:\\Users\\" + user + "\\AppData\\Local\\Cyrus\\" + COMMANDFILE);
				errorMessages = createFile("C:\\Users\\" + user + "\\AppData\\Local\\Cyrus\\" + ERRORMESSAGEFILE);
				commonQuestions = createFile("C:\\Users\\" + user + "\\AppData\\Local\\Cyrus\\" + COMMONQUESTIONFILE);
				jokes = createFile("C:\\Users\\" + user + "\\AppData\\Local\\Cyrus\\" + JOKEFILE);
				notes = createFile("C:\\Users\\" + user + "\\AppData\\Local\\Cyrus\\" + NOTEFILE);
			} else {
				command = createFile("/usr/cyrus/" + COMMANDFILE);
				errorMessages = createFile("/usr/cyrus/" + ERRORMESSAGEFILE);
				commonQuestions = createFile("/usr/cyrus/" + COMMONQUESTIONFILE);
				jokes = createFile("/usr/cyrus/" + JOKEFILE);
				notes = createFile("/usr/cyrus/" + NOTEFILE);
			}
			int i = 0;
				boolean append = true;
				writeToFile(command, "- hi", i++, append);
				writeToFile(command, "  - <hi/hey/hello/hola>", i++, append); // this would be the help string
				writeToFile(command, "    - 0", i++, append); // amount of args
				writeToFile(command, "      - hey, hello, hola", i++, append);
				writeToFile(command, "        - This command is used to say hello to cyrus", i++, append);
				
				writeToFile(command, "- createkeybind", i++, append);
				writeToFile(command, "  - <createkeybind> <key> <operation>", i++, append);
				writeToFile(command, "    - 2", i++, append);
				writeToFile(command, "      - ", i++, append);
				writeToFile(command, "        - This command is used to create a key bind", i++, append);
				
				writeToFile(command, "- what", i++, append);
				writeToFile(command, "  - <what/what's> <question>", i++, append);
				writeToFile(command, "    - 1", i++, append);
				writeToFile(command, "      - what's", i++, append);
				writeToFile(command, "        - This command is used to ask cyrus a question", i++, append);
				
				writeToFile(command, "- math", i++, append);
				writeToFile(command, "  - <math>", i++, append);
				writeToFile(command, "    - 0", i++, append);
				writeToFile(command, "      - calc, calculator", i++, append);
				writeToFile(command, "        - This command is used to open the calculator", i++, append);
				
				writeToFile(command, "- how", i++, append);
				writeToFile(command, "  - <how/how's/how'd> <question>", i++, append);
				writeToFile(command, "    - 1", i++, append);
				writeToFile(command, "      - how's, how'd", i++, append);
				writeToFile(command, "        - This command is used to ask cyrus a question", i++, append);
				
				writeToFile(command, "- clear", i++, append);
				writeToFile(command, "  - <clear>", i++, append);
				writeToFile(command, "    - 0", i++, append);
				writeToFile(command, "      - cl", i++, append);
				writeToFile(command, "        - This command is used to clear the console", i++, append);
				
				writeToFile(command, "- quit", i++, append);
				writeToFile(command, "  - <quit>", i++, append);
				writeToFile(command, "    - 0", i++, append);
				writeToFile(command, "      -", i++, append);
				writeToFile(command, "        - This command is used to end the cyrus process", i++, append);
				
				writeToFile(command, "- joke", i++, append);
				writeToFile(command, "  - <joke>", i++, append);
				writeToFile(command, "    - 0", i++, append);
				writeToFile(command, "      -", i++, append);
				writeToFile(command, "        - This command is used to have cyrus tell you a joke", i++, append);
				
				// TODO None of the commands below this line are implemented 
				////////////////////////////////////////////////////////////////////////////////////
				writeToFile(command, "- todo", i++, append);
				writeToFile(command, "  - <todo> <add/remove> <task> <reminder time> <reminder interval>", i++, append);
				writeToFile(command, "    - 0", i++, append);
				writeToFile(command, "      - td", i++, append);
				writeToFile(command, "        - This command is used to setup a todo with a timer", i++, append);
				
				writeToFile(command, "- set", i++, append);
				writeToFile(command, "  - <set> <timer/countdown> <length>", i++, append);
				writeToFile(command, "    - 0", i++, append);
				writeToFile(command, "      - create", i++, append);
				writeToFile(command, "        - This command is used to set a timer", i++, append);
				
				writeToFile(command, "- ls", i++, append);
				writeToFile(command, "  - <ls> <directory>", i++, append);
				writeToFile(command, "    - 0", i++, append);
				writeToFile(command, "      - list", i++, append);
				writeToFile(command, "        - This command is used to list the contents "
						+ "of your current working direcotry", i++, append);
				
				writeToFile(command, "- cd", i++, append);
				writeToFile(command, "  - <cd> <directory>", i++, append);
				writeToFile(command, "    - 0", i++, append);
				writeToFile(command, "      - changedirectory", i++, append);
				writeToFile(command, "        - This command is used to change your current working directory", i++, append);
				
				writeToFile(command, "- help", i++, append);
				writeToFile(command, "  - <help>", i++, append);
				writeToFile(command, "    - 0", i++, append);
				writeToFile(command, "      - man", i++, append);
				writeToFile(command, "        - This command is used to find a "
						+ "manual page about any given comand", i++, append);
				
				writeToFile(command, "- note", i++, append);
				writeToFile(command, "  - <note> <remove> <the note>", i++, append);
				writeToFile(command, "    - 1", i++, append);
				writeToFile(command, "      - n", i++, append);
				writeToFile(command, "        - This command is used to take notes", i++, append);
				
				writeToFile(command, "- todo", i++, append);
				writeToFile(command, "  - <todo> <remove> <todo number>", i++, append);
				writeToFile(command, "    - 1", i++, append);
				writeToFile(command, "      - t", i++, append);
				writeToFile(command, "        - This command is used to make todos", i++, append);
				/////////////////////////////////////////////////////////////////////////////////////
				
				int j = 0;
				
				writeToFile(errorMessages, "I'm sorry I don't understand what you are trying to say.", j++, append);
				writeToFile(errorMessages, "Are you sure your words make sense?", j++, append);
				writeToFile(errorMessages, "ERROR: please check your command and make sure it is correct!", j++, append);
				writeToFile(errorMessages, "This is an unknown word or phrase sorry I can't help you!", j++, append);
			
				int i1 = 0;
				
				writeToFile(commonQuestions, "", i1++, append);
				writeToFile(commonQuestions, "", i1++, append);
				writeToFile(commonQuestions, "", i1++, append);
				writeToFile(commonQuestions, "", i1++, append);
				
				int i2 = 0;
				
				writeToFile(jokes, "Yo' mama so stupid, she walked into an antique shop and asked, \"What's new?\"", i2++, append);
				writeToFile(jokes, "Your Mama's so fat that when she went to school she sat next to the whole class!", i2++, append);
				writeToFile(jokes, "Yo mamma's so fat, she tripped on 4th Avenue and landed on 12th.", i2++, append);
				writeToFile(jokes, "What do you call 500 lawyers at the bottom of the ocean?" + "A good start.", i2++, append);
				
				
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
	
	public void saveNotes(NoteManager noteManager) {
		File notes;
		if(os.equalsIgnoreCase("windows 10")) {
			notes = createFile("C:\\Users\\" + user + "\\AppData\\Local\\Cyrus\\" + NOTEFILE);
		} else {
			notes = createFile("/usr/cyrus/" + NOTEFILE);
		}
		if(noteManager.getNotes().size() == 0) {
			try { // this is here because we don't want a newline
	        	FileOutputStream fileOut = new FileOutputStream(notes);
				fileOut.write("".getBytes());
				fileOut.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
		for(int i = 0; i < noteManager.getNotes().size(); i++) {
			if(i == 0) {
				writeToFile(notes, noteManager.getNotes().get(i).getNote(), i, false);
			} else {
				writeToFile(notes, noteManager.getNotes().get(i).getNote(), i, true);
			}
			
		}
	}
	
	public void saveTodos(TodoManager todoManager) {
		File todos;
		if(os.equalsIgnoreCase("windows 10")) {
			todos = createFile("C:\\Users\\" + user + "\\AppData\\Local\\Cyrus\\" + TODOFILE);
		} else {
			todos = createFile("/usr/cyrus/" + TODOFILE);
		}
		if(todoManager.getTodos().size() == 0) {
			try { // this is here because we don't want a newline
	        	FileOutputStream fileOut = new FileOutputStream(todos);
				fileOut.write("".getBytes());
				fileOut.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
		for(int i = 0; i < todoManager.getTodos().size(); i++) {
			if(i == 0) {
				writeToFile(todos, todoManager.getTodos().get(i).getTodo(), i, false);
			} else {
				writeToFile(todos, todoManager.getTodos().get(i).getTodo(), i, true);
			}
			
		}
	}

}
