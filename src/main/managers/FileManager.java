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
	
	private ArrayList<File> files = new ArrayList<File>(); // All the files the ai knows about
	
	public FileManager() {
		
	}
	
	public ArrayList<File> getFiles() {
		return this.files;
	}
	
	public File getFile(String name) { //TODO this causes a crash if a file isn't found
		try {
			File file;
			if(name.contains(".")) { // there is an extension
				file = new File(name);
			} else {
				file = new File(name + ".cy"); // the default extension
			}
			if(file != null) {
				return file;
			}
		} catch(Exception e) {
			
		}
		return null;
	}
	
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
	
	public String readFileLine(File file, int lineNumb) {
		ArrayList<String> fileStr = readFullFile(file);
		try {
			return fileStr.get(lineNumb);
		} catch(Exception e) {
			return null; //TODO change this when finished
		}
	}
	
	public String readParentLine(File file, int lineNumb, String parent) { 
		// reads a given line from a file and returns the line as a string
		// or reads a line under a given parent string the first line under the parent string is 0
		/* ex:
		 * File
		 * Family:
		 * 	- Mark
		 * 	- Jill
		 * 	- Bob
		 * typing readFileLine(File, 0, "family:");
		 * will return "Mark"
		 */
			ArrayList<String> fileStr = readFullFile(file);
			if(parent != "" && parent != null) { // they entered a parent string
				if(fileStr.contains(parent) && fileStr.size() >= lineNumb) { // parent string is in the file
					int index = fileStr.indexOf(parent); // get where the parent string is
					lineNumb++; // this is because I want to use 0 for the first item under the parent but adding 0 will do nothing
					return fileStr.get(index + lineNumb); // return the string below
				} else { // the parent string is not in the file return nothing this shouldn't happen
					return "Error: readParentLine, lineNumb was too big or the command does not exist!";
				}
			}
		return "";
	}
	
	//I think it's to much work to make this writer be able to write to any line
	public void writeToFile(File file, String line, int lineNumb) { // writes a line to a file
		// if lineNumb is -1 write to the first line that is empty
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
	
	
	public File createFile(String name) {
		File file;
		if(name.contains(".")) { // there is an extension
			file = new File(name);
		} else {
			file = new File(name + ".cy"); // the default extension
		}
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
	
	
	public void setup() {
		try {
			//TODO need a better file location 
			File command = createFile("commands");
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
				writeToFile(command, "      - ", i++);
				
				writeToFile(command, "- how", i++);
				writeToFile(command, "  - <how/how's/how'd> <question>", i++);
				writeToFile(command, "    - 1", i++);
				writeToFile(command, "      - how's, how'd", i++);
				
				writeToFile(command, "- clear", i++);
				writeToFile(command, "  - <clear>", i++);
				writeToFile(command, "    - 0", i++);
				writeToFile(command, "      - cl", i++);
				
				File errorMessages = createFile("error messages");
				int j = 0;
				writeToFile(errorMessages, "I'm sorry I don't understand what you are trying to say.", j++);
				writeToFile(errorMessages, "Are you sure your words make sense?", j++);
				writeToFile(errorMessages, "ERROR: please check your command and make sure it is correct!", j++);
				
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
