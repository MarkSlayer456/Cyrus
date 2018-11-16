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
	    	writer.append(line.toLowerCase());
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
			File file = createFile("commands");
			int i = 0;
				writeToFile(file, "- hi", i++);
				writeToFile(file, "  - <hi/hey/hello/hola>", i++); // this would be the help string
				writeToFile(file, "    - 0", i++); // amount of args
				writeToFile(file, "      - hey, hello, hola", i++);
				
				writeToFile(file, "- createkeybind", i++);
				writeToFile(file, "  - <createkeybind> <key> <operation>", i++);
				writeToFile(file, "    - 2", i++);
				writeToFile(file, "      - ", i++);
				
				writeToFile(file, "- what", i++);
				writeToFile(file, "  - <what/what's> <question>", i++);
				writeToFile(file, "    - 1", i++);
				writeToFile(file, "      - what's", i++);
				
				writeToFile(file, "- math", i++);
				writeToFile(file, "  - <math>", i++);
				writeToFile(file, "    - 0", i++);
				writeToFile(file, "      - ", i++);
				
				writeToFile(file, "- how", i++);
				writeToFile(file, "  - <how/how's/how'd> <question>", i++);
				writeToFile(file, "    - 1", i++);
				writeToFile(file, "      - how's, how'd", i++);
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
