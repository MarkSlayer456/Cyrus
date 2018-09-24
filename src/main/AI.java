package main;

public class AI implements Runnable {
	public static boolean thinking = true; // is Cyrus doing any computations at all
	private String name;
	private boolean hasGreeted; // has Cyrus introduced himself
	
	public AI(String n) { // Only create one AI
		this.name = n;
		this.hasGreeted = false;
	}
	
	public void greet() {
		//TODO this is just for testing
		System.out.println("Hello, my name is " + this.name + ", this message is brought to you from the AI.java file under the greet method!");
		this.hasGreeted = true;
	}
	
	public String getName() {
		return this.name;
	}

	public void logic() { // The thinking method
		if(!this.hasGreeted) {
		this.greet();
		}
	}
	
	
	@Override
	public void run() { // Cyrus thought process
		while(thinking) {
			this.logic();
		}
	}
	
	
	
	///// Methods that Cyrus responds to /////
	// Just test methods at the moment to test if cyrus is working
	public String speak() { // Method is mostly for testing will be changed later as the starting message
		return null;
	}	
}
