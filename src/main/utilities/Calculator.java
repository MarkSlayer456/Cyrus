package main.utilities;

import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;

import main.Frame;
import main.managers.ImageLoader;

public class Calculator implements Runnable {
	
	public static ImageLoader imageManager = ImageLoader.getInstance();
	
	//TODO add paths could be moved to imageLoader
	private final Image PLUS = imageManager.getImage("");
	private final Image SUB = imageManager.getImage("");
	private final Image MULTI = imageManager.getImage("");
	private final Image DIVIDE = imageManager.getImage("");
	private final Image EQUALS = imageManager.getImage("");
	private final Image ONE = imageManager.getImage("");
	private final Image TWO = imageManager.getImage("");
	private final Image THREE = imageManager.getImage("");
	private final Image FOUR = imageManager.getImage("");
	private final Image FIVE = imageManager.getImage("");
	private final Image SIX = imageManager.getImage("");
	private final Image SEVEN = imageManager.getImage("");
	private final Image EIGHT = imageManager.getImage("");
	private final Image NINE = imageManager.getImage("");
	private final Image ZERO = imageManager.getImage("");
	
	private boolean running;
	private Frame frame;
	private ArrayList<Button> buttons;
	private int currentNumb;
	
	public Calculator(Boolean r, Frame f) { // only one of these is ever created
		this.running = r;
		this.frame = f;
	    this.buttons = new ArrayList<>();
		createButtons();
	}
	
	//TODO find out how to solve this
	private void createButtons() {
		buttons.add(new Button(new Point(this.frame.getWidth() - 100, 150), 75, 50, true, PLUS));
    	buttons.add(new Button(new Point(this.frame.getWidth() - 100, 205), 75, 50, true, SUB));
    	buttons.add(new Button(new Point(this.frame.getWidth() - 100, 260), 75, 50, true, MULTI));
    	buttons.add(new Button(new Point(this.frame.getWidth() - 100, 315), 75, 50, true, DIVIDE));
    	buttons.add(new Button(new Point(this.frame.getWidth() - 100, 370), 75, 50, true, EQUALS));
		
		buttons.add(new Button(new Point(this.frame.getWidth() - 200, 400), 75, 50, true, ONE));
    	buttons.add(new Button(new Point(this.frame.getWidth() - 280, 400), 75, 50, true, TWO));
		buttons.add(new Button(new Point(this.frame.getWidth() - 360, 400), 75, 50, true, THREE));
		buttons.add(new Button(new Point(this.frame.getWidth() - 440, 400), 75, 50, true, FOUR));
		buttons.add(new Button(new Point(this.frame.getWidth() - 520, 400), 75, 50, true, FIVE));
		buttons.add(new Button(new Point(this.frame.getWidth() - 200, 315), 75, 50, true, SIX));
		buttons.add(new Button(new Point(this.frame.getWidth() - 280, 315), 75, 50, true, SEVEN));
		buttons.add(new Button(new Point(this.frame.getWidth() - 360, 315), 75, 50, true, EIGHT));
		buttons.add(new Button(new Point(this.frame.getWidth() - 440, 315), 75, 50, true, NINE));
		buttons.add(new Button(new Point(this.frame.getWidth() - 520, 315), 75, 50, true, ZERO));
	}
	
	private void updateButtonsLocation() {
		
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
			this.updateButtonsLocation();
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
