package main.managers;

import java.util.ArrayList;

import main.utilities.Note;

public class NoteManager {
	
	private ArrayList<Note> notes;
	private FileManager fileManager;
	
	public NoteManager(FileManager fileManager) {
		this.notes = new ArrayList<Note>();
		this.fileManager = fileManager;
	}
	
	public ArrayList<Note> getNotes() {
		return notes;
	}
	
	public void addNote(Note n) {
		notes.add(n);
	}
	
	
	public void modifyNote(int i, String newNote) {
		Note n = notes.get(i);
		n.setNote(newNote);
	}
	
	public void modifyNote(Note n, String newNote) {
		n.setNote(newNote);
	}
	
	public void removeNote(int i) {
		this.notes.remove(i);
	}
	
	public void saveNotes() {
		fileManager.saveNotes(this);
	}
	
	public void loadNotes() {
		ArrayList<String> list = fileManager.readFullFile(fileManager.getFile(FileManager.NOTEFILE));
		if(list == null) {
			return;
		}
		for(int i = 0; i < list.size(); i++) {
			this.notes.add(new Note(list.get(i)));
		}
	}
	
}
