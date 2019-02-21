package main.utilities;

import java.awt.Point;
import java.util.ArrayList;

import main.Frame;
import main.managers.ImageLoader;

public class Calculator implements Runnable {
	
	public static ImageLoader imageManager = ImageLoader.getInstance();
	
	private boolean running;
	private Frame frame;
	private ArrayList<Button> buttons;
	private int currentNumb;
	/**
	 * Creates Calculator
	 * @param r - Is the calculator running, typically false
	 * @param f - The frame for the calculator
	 */
	public Calculator(Boolean r, Frame f) { // only one of these is ever created
		this.running = r;
		this.frame = f;
	    this.buttons = new ArrayList<>();
		createButtons();
	}
	
	/**
	 * Updates all the buttons locations
	 */
	private void updateButtonLocation() {
		 for(Button b : this.buttons) {
		  b.getRect().setRect(this.frame.getJFrame().getWidth() - b.getPos().getX(), this.frame.getJFrame().getHeight() - b.getPos().getY(), b.getWidth(), b.getHeight());
		 }
	}
	
	private void createButtons() {
		buttons.add(new Button(new Point(100, 400), 75, 50, true, Button.PLUS, this.frame.getJFrame()));
    	buttons.add(new Button(new Point(100, 345), 75, 50, true, Button.SUB, this.frame.getJFrame()));
    	buttons.add(new Button(new Point(100, 290), 75, 50, true, Button.MULTI, this.frame.getJFrame()));
    	buttons.add(new Button(new Point(100, 235), 75, 50, true, Button.DIVIDE, this.frame.getJFrame()));
    	buttons.add(new Button(new Point(100, 180), 75, 50, true, Button.EQUALS, this.frame.getJFrame()));
		
		buttons.add(new Button(new Point(200, 400), 75, 50, true, Button.ONE, this.frame.getJFrame()));
    	buttons.add(new Button(new Point(280, 400), 75, 50, true, Button.TWO, this.frame.getJFrame()));
		buttons.add(new Button(new Point(360, 400), 75, 50, true, Button.THREE, this.frame.getJFrame()));
		buttons.add(new Button(new Point(200, 345), 75, 50, true, Button.FOUR, this.frame.getJFrame()));
		buttons.add(new Button(new Point(280, 345), 75, 50, true, Button.FIVE, this.frame.getJFrame()));
		buttons.add(new Button(new Point(360, 345), 75, 50, true, Button.SIX, this.frame.getJFrame()));
		buttons.add(new Button(new Point(200, 290), 75, 50, true, Button.SEVEN, this.frame.getJFrame()));
		buttons.add(new Button(new Point(280, 290), 75, 50, true, Button.EIGHT, this.frame.getJFrame()));
		buttons.add(new Button(new Point(360, 290), 75, 50, true, Button.NINE, this.frame.getJFrame()));
		buttons.add(new Button(new Point(280, 235), 75, 50, true, Button.ZERO, this.frame.getJFrame()));
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
			this.updateButtonLocation();
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
