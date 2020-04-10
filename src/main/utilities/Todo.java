package main.utilities;

import java.util.Date;

public class Todo {

	private String todo;
	private Date created;
	private Date completionTime;
	
	public Todo(String todo, Date complTime) {
		this.todo = todo;
		this.created = new Date();
		this.completionTime = complTime;
	}
	
	public void setTodo(String todo, Date complTime) {
		this.todo = todo;
		this.completionTime = complTime;
	}
	
	public String getTodo() {
		return this.todo;
	}
	
	public void setCompletionTime(Date date) {
		this.completionTime = date;
	}
	
	public Date getCompletionTime() {
		return this.completionTime;
	}
	
	public Date getCreated() {
		return this.created;
	}
	
}
