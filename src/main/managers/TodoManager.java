package main.managers;

import java.util.ArrayList;
import java.util.Date;

import main.utilities.Todo;

public class TodoManager {

	private ArrayList<Todo> todo;
	private FileManager fileManager;
	
	public TodoManager(FileManager fileManager) {
		this.todo = new ArrayList<Todo>();
		this.fileManager = fileManager;
	}
	
	public ArrayList<Todo> getTodos() {
		return this.todo;
	}
	
	public void addTodo(Todo t) {
		this.todo.add(t);
	}
	
	public void modifyTodo(int i, String newTodo, Date date) {
		Todo t = this.todo.get(i);
		t.setTodo(newTodo, date);
	}
	
	public void modifyTodo(Todo t, String newTodo, Date date) {
		t.setTodo(newTodo, date);
	}
	
	public void removeTodo(int i) {
		this.todo.remove(i);
	}
	
	public void saveTodos() {
		fileManager.saveTodos(this);
	}
	
	public void loadTodo() {
		ArrayList<String> list = fileManager.readFullFile(fileManager.getFile(FileManager.TODOFILE));
		if(list == null) {
			return;
		}
		for(int i = 0; i < list.size(); i++) {
			this.todo.add(new Todo(list.get(i), null));
		}
	}
	
}
