package main;

public class AI {
	
	private String name;
	
	public AI(String n) {
		this.name = n;
	}
	
	public String getName() { // Only create one AI
		return this.name;
	}

}
