package main.managers;

public class FrameRateManager {
	
	private final double oneFrameInterval = 33.3333334; 
	private double startingTime, endingTime;
	public FrameRateManager() {
		this.startingTime = 0;
		this.endingTime = 0;
	}
	private void firstTime() {
		if(this.startingTime == 0) {
			this.startingTime = System.currentTimeMillis();
			this.endingTime = System.currentTimeMillis();
		}
	}
	
	
	public boolean canExecute() {
		firstTime();
		if(this.endingTime - this.startingTime >= this.oneFrameInterval) {
			return true;
		} else {
			try {
				Thread.sleep((long) (this.oneFrameInterval - (this.endingTime - this.startingTime)));
				return true;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("ERROR frame failed to load");
		return false;
	}
	
	///// Setters /////
	public void setStartingTime(double amount) {
		if(amount > 0) {
			this.startingTime = amount;
		}
	}
	
	public void setEndingTime(double amount) {
		if(amount > 0) {
			this.endingTime = amount;
		}
	}
}
