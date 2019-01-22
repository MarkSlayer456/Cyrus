package main.utilities;

import java.awt.Color;

import main.Frame;

public class Calculator implements Runnable {
	
	private boolean running;
	private Frame frame;
	
	public Calculator(Boolean r, Frame f) { // only one of these is ever created
		this.running = r;
		this.frame = f;
	}
	
	public Frame getFrame() {
		return this.frame;
	}
	
	public double add(int num1, int num2) {
		return num1 + num2;
	}
	
	public double sub(int num1, int num2) {
		return num1 - num2;
	}
	
	public double mult(int num1, int num2) {
		return num1 * num2;
	}
	
	public double div(int num1, int num2) {
		return num1 / num2;
	}

	public void drawCalc() { //TODO this is just drawing not VISUAL EFFECT ONLY
		if(this.getFrame().getJFrame().getBufferStrategy() == null) { this.getFrame().getJFrame().createBufferStrategy(3); }
		this.getFrame().setBS(this.getFrame().getJFrame().getBufferStrategy());
		this.getFrame().setGraphics(this.getFrame().getBS().getDrawGraphics());
		this.getFrame().getGraphics().clearRect(0, 0, this.getFrame().getWidth(), this.getFrame().getHeight());
		///////////////////////////////////////////////////////////////
		this.getFrame().getGraphics().setColor(Color.BLACK);
		this.getFrame().getGraphics().fillRect(0, 0, frame.getSize().width, frame.getSize().height);
		this.getFrame().getGraphics().setColor(Color.WHITE);
		//TODO finish this
		this.getFrame().getGraphics().fillRect(1, 100, 75, 50);
		this.getFrame().getGraphics().fillRect(77, 100, 75, 50);
		this.getFrame().getGraphics().fillRect(153, 100, 75, 50);
		///////////////////////////////////////////////////////////////
		this.getFrame().getGraphics().dispose();
		this.getFrame().getBS().show();
	}
	
	public boolean isRunning() {
		return this.running;
	}
	
	public void setup() {
		Frame.calcFrame.getFrame().setVisible(true);
	}
	
	
	@Override
	public void run() {
		this.setup();
		this.running = true;
		while(this.running) {
			this.drawCalc();
			//this.getFrame().draw();
			//Frame.calcFrame.draw(); // shows calculator
		}
		
	}
}
