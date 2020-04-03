package main.utilities;

import java.util.Date;

public class Note {
	
	private String note;
	private Date created;
	
	public Note(String note) {
		this.note = note;
		this.created = new Date();
	}
	
	public String getNote() {
		return note;
	}
	
	public Date getDateCreated() {
		return this.created;
	}
	
	public void setNote(String note) {
		this.note = note;
	}
}
