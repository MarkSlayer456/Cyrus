package main.utilities;

import java.awt.Point;
import java.util.ArrayList;

import main.CyrusMain;
import main.Frame;
import main.managers.ImageLoader;

public class Calculator {
	
	public static ImageLoader imageManager = ImageLoader.getInstance();
	private CyrusMain cyrusMain;
	
	
	private boolean running;
	private Frame frame;
	private ArrayList<Button> buttons;
	private int currentNumb;
	private String displayText;
	/**
	 * Creates Calculator
	 * @param r - Is the calculator running, typically false
	 * @param f - The frame for the calculator
	 */
	public Calculator(Boolean r, Frame f) { // only one of these is ever created
		this.running = r;
		this.frame = f;
	    this.buttons = new ArrayList<>();
	    this.displayText = "";
		createButtons();
		cyrusMain = CyrusMain.getInstance();
	}
	
	/**
	 * Updates all the buttons locations
	 */
	private void updateButtonLocation() {
		 for(Button b : this.buttons) {
			 b.getRect().setRect(this.frame.getJFrame().getWidth() - b.getPos().getX(), this.frame.getJFrame().getHeight() - b.getPos().getY(), b.getWidth(), b.getHeight());
		 }
	}
	
	/**
	 * Activates button given and does action
	 * @param b - button to click
	 */
	public void clickButton(Button b) {
		switch(b.getText().toLowerCase()) {
		case "0":
			this.setCurrentNumb(0);
			this.updateDisplayText(this.getDisplayText() + this.getCurrentNumb() + "");
			break;
		case "1":
			this.setCurrentNumb(1);
			this.updateDisplayText(this.getDisplayText() + this.getCurrentNumb() + "");
			break;
		case "2":
			this.setCurrentNumb(2);
			this.updateDisplayText(this.getDisplayText() + this.getCurrentNumb() + "");
			break;
		case "3":
			this.setCurrentNumb(3);
			this.updateDisplayText(this.getDisplayText() + this.getCurrentNumb() + "");
			break;
		case "4":
			this.setCurrentNumb(4);
			this.updateDisplayText(this.getDisplayText() + this.getCurrentNumb() + "");
			break;
		case "5":
			this.setCurrentNumb(5);
			this.updateDisplayText(this.getDisplayText() + this.getCurrentNumb() + "");
			break;
		case "6":
			this.setCurrentNumb(6);
			this.updateDisplayText(this.getDisplayText() + this.getCurrentNumb() + "");
			break;
		case "7":
			this.setCurrentNumb(7);
			this.updateDisplayText(this.getDisplayText() + this.getCurrentNumb() + "");
			break;
		case "8":
			this.setCurrentNumb(8);
			this.updateDisplayText(this.getDisplayText() + this.getCurrentNumb() + "");
			break;
		case "9":
			this.setCurrentNumb(9);
			this.updateDisplayText(this.getDisplayText() + this.getCurrentNumb() + "");
			break;
		case "+":
			this.updateDisplayText(this.getDisplayText() + " + ");
			break;
		case "-":
			this.updateDisplayText(this.getDisplayText() + " - ");
			break;
		case "*":
			this.updateDisplayText(this.getDisplayText() + " * ");
			break;
		case "/":
			this.updateDisplayText(this.getDisplayText() + " / ");
			break;
		case "c":
			this.updateDisplayText("");
			break;
		default:
			break;
		}
	}
	
	/**
	 * Creates all the buttons for the calculator
	 */
	private void createButtons() {
		
		buttons.add(new Button(new Point(440, 400), 75, 50, true, Button.PLUS, this.frame.getJFrame(), "C"));
		
		buttons.add(new Button(new Point(100, 400), 75, 50, true, Button.PLUS, this.frame.getJFrame(), "+"));
    	buttons.add(new Button(new Point(100, 345), 75, 50, true, Button.SUB, this.frame.getJFrame(), "-"));
    	buttons.add(new Button(new Point(100, 290), 75, 50, true, Button.MULTI, this.frame.getJFrame(), "*"));
    	buttons.add(new Button(new Point(100, 235), 75, 50, true, Button.DIVIDE, this.frame.getJFrame(), "/"));
    	buttons.add(new Button(new Point(100, 180), 75, 50, true, Button.EQUALS, this.frame.getJFrame(), "="));
		
		buttons.add(new Button(new Point(200, 400), 75, 50, true, Button.NINE, this.frame.getJFrame(), "9"));
    	buttons.add(new Button(new Point(280, 400), 75, 50, true, Button.EIGHT, this.frame.getJFrame(), "8"));
		buttons.add(new Button(new Point(360, 400), 75, 50, true, Button.SEVEN, this.frame.getJFrame(), "7"));
		buttons.add(new Button(new Point(200, 345), 75, 50, true, Button.SIX, this.frame.getJFrame(), "6"));
		buttons.add(new Button(new Point(280, 345), 75, 50, true, Button.FIVE, this.frame.getJFrame(), "5"));
		buttons.add(new Button(new Point(360, 345), 75, 50, true, Button.FOUR, this.frame.getJFrame(), "4"));
		buttons.add(new Button(new Point(200, 290), 75, 50, true, Button.THREE, this.frame.getJFrame(), "3"));
		buttons.add(new Button(new Point(280, 290), 75, 50, true, Button.TWO, this.frame.getJFrame(), "2"));
		buttons.add(new Button(new Point(360, 290), 75, 50, true, Button.ONE, this.frame.getJFrame(), "1"));
		buttons.add(new Button(new Point(280, 235), 75, 50, true, Button.ZERO, this.frame.getJFrame(), "0"));
		
		
	}
	
	/**
	 * Adds two numbers together
	 * @param num1- The first number to add
	 * @param num2 - The second number to add
	 * @return the numbers added together
	 */
	public double add(int num1, int num2) {
		return num1 + num2;
	}
	/**
	 * Subtracts two numbers.
	 * @param num1 - The base number
	 * @param num2 - The number you want to subtract
	 * @return - The answer
	 */
	public double sub(int num1, int num2) {
		return num1 - num2;
	}
	/**
	 * Multiplies two numbers.
	 * @param num1 - The base number
	 * @param num2 - The number you want to multiply by
	 * @return - The answer
	 */
	public double mult(int num1, int num2) {
		return num1 * num2;
	}
	/**
	 * Divides two numbers.
	 * @param num1 - The base number
	 * @param num2 - The number you want to divide by
	 * @return - The answer
	 */
	public double div(int num1, int num2) {
		return num1 / num2;
	}
	/**
	 * Is the calculator currently running.
	 * @return - True if the calculator is running; false otherwise
	 */
	public boolean isRunning() {
		return this.running;
	}
	/**
	 * Sets the frame to visible
	 */
	public void makeVisible() {
			cyrusMain.getCalc().getFrame().getJFrame().setVisible(true);
	}
	
	public void update () {
		this.getFrame().setupBufferStrategy();
		////////////////////////////////////////////
		this.updateButtonLocation();
		this.getFrame().getUIManager().draw(this.buttons);
		////////////////////////////////////////////
		this.getFrame().disposeAndShow();
	}
	
	///// Setter /////
	/**
	 * Adds a button to the calculator. 
	 * @param button - The button you wish to add to the calculator
	 */
	public void addButton(Button button) {
		this.buttons.add(button);
	}
	/**
	 * Set's the current number displayed on the calculator.
	 * @param numb - The number you want to set it to
	 */
	public void setCurrentNumb(int numb) {
		this.currentNumb = numb;
	}
	
	
	/**
	 * Adds to current number
	 * @param numb - The number you want to add
	 * @return - Returns the new current number
	 */
	private int addCurrentNumb(int numb) {
		this.currentNumb += numb;
		return this.currentNumb;
	}
	
	/**
	 * Subtracts from current number
	 * @param numb - The number you want to subtract
	 * @return - Return the new current number
	 */
	private int subCurrentNumb(int numb) {
		this.currentNumb -= numb;
		return this.currentNumb;
	}
	
	
	///// Getter /////
	/**
	 * Gets all the buttons the calculator has.
	 * @return - A array of buttons for the given calculator
	 */
	public ArrayList<Button> getButtons() {
		return this.buttons;
	}
	
	/**
	 * Gets the frame for the given calculator.
	 * @return - The frame for the given calculator
	 */
	public Frame getFrame() {
		return this.frame;
	}
	
	/**
	 * Gets the current number displayed on calculator.
	 * @return - The current display number
	 */
	public int getCurrentNumb() {
		return this.currentNumb;
	}
	
	/**
	 * Update display text.
	 * @param str - The display text
	 */
	public void updateDisplayText(String str) {
		this.displayText = str;
	}
	
	/**
	 * Gets the current display text.
	 * @return - The current display text
	 */
	public String getDisplayText() {
		return this.displayText;
	}
}
