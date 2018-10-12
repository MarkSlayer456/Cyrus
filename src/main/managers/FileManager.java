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
	
	public boolean checkIfFileIsDone() { //TODO
		return false;
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
	
	
	public String readFileLine(File file, int lineNumb, String parent) { 
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
			ArrayList<String> str = readFullFile(file);
			if(parent != "" && parent != null) { // they entered a parent string
				if(str.contains(parent) && str.size() >= lineNumb) { // parent string is in the file
					int index = str.indexOf(parent); // get where the parent string is
					lineNumb++; // this is because I want to use 0 for the first item under the parent but adding 0 will do nothing
					return str.get(index + lineNumb); // return the string below
				} else { // the parent string is not in the file return nothing this shouldn't happen
					return "Error: readFileLine lineNumb was too big!";
				}
			}
		return "";
	}
	
	public void writeToFile(File file, String line, int lineNumb) { // writes a line to a file
		// if lineNumb is -1 write to the first line that is empty
		if(this.readFullFile(file).contains(line)) { // stops from writing the same thing
			return;
		}
	    try {
	    	BufferedWriter writer = new BufferedWriter(new FileWriter(file.toString(), true));
			writer.append(line.toLowerCase()); // make sure everything is lower case
			writer.newLine();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	     
	    
		
		
		
	}
	
	
	public File createFile(String name) {
		System.out.println("creating file " + name + "...");
		File file;
		if(name.contains(".")) { // there is an extension
			file = new File(name);
		} else {
			file = new File(name + ".cy"); // the default extension
		}
		if(!file.exists()) {
			try {
				file.createNewFile();
				System.out.println("File created!");
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
			writeToFile(file, "greetings:", -1);
			writeToFile(file, "  - hi", -1);
			writeToFile(file, "  - hey", -1);
			writeToFile(file, "  - hello", -1);
			writeToFile(file, "  - hola", -1);
			String test = readFileLine(file, 1, "greetings:");
			test = test.replace(" ", "");
			test = test.replace("-", "");
			System.out.println(test); //TODO this is a test it works!
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
