package main.utilities;

import java.awt.Point;
import java.util.ArrayList;

import main.Frame;

public class Calculator implements Runnable {
	
	
	private boolean running;
	private Frame frame;
	private ArrayList<Button> buttons;
	private int currentNumb;
	
	public Calculator(Boolean r, Frame f, ArrayList<Button> buttons) { // only one of these is ever created
		this.running = r;
		this.frame = f;
		this.buttons = buttons;
		
		//TODO add images
		Button plus = new Button(new Point(475, 150), 75, 50, true, null);
    	Button sub = new Button(new Point(475, 205), 75, 50, true, null);
    	Button mult = new Button(new Point(475, 260), 75, 50, true, null);
    	Button div = new Button(new Point(475, 315), 75, 50, true, null);
    	Button equal = new Button(new Point(475, 370), 75, 50, true, null);
    	
    	buttons.add(plus);
    	buttons.add(sub);
    	buttons.add(mult);
    	buttons.add(div);
    	buttons.add(equal);
	}
	
	/**
	 * Adds two numbers together
	 * @param num1 the first number to add
	 * @param num2 the second number to add
	 * @return the numbers added together
	 */
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

	public boolean isRunning() {
		return this.running;
	}
	
	public void setup() {
		Frame.calcFrame.getJFrame().setVisible(true);
	}
	
	
	@Override
	public void run() {
		this.setup();
		this.running = true;
		while(this.running) {
			this.getFrame().setupBufferStrategy();
			////////////////////////////////////////////
			this.getFrame().getUIManager().drawCalc(this.buttons);
			////////////////////////////////////////////
			this.getFrame().disposeAndShow();
		}
		
	}
	
	///// Setter /////
	public void addButton(Button button) {
		this.buttons.add(button);
	}
	
	public void setCurrentNumb(int numb) {
		this.currentNumb = numb;
	}
	
	///// Getter /////
	public ArrayList<Button> getButtons() {
		return this.buttons;
	}
	
	public Frame getFrame() {
		return this.frame;
	}
	
	public int getCurrentNumb() {
		return this.currentNumb;
	}
}
