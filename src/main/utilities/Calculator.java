package main.utilities;

import java.awt.Color;
import java.awt.Graphics;

import main.Frame;

public class Calculator implements Runnable {
	
	public static boolean running;
	
	public Calculator(Boolean r) {
		this.running = r;
	}

	public void drawCalc(Graphics g, Frame frame) { //TODO this is just drawing not VISUAL EFFECT ONLY
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, frame.getSize().width, frame.getSize().height);
		g.setColor(Color.WHITE);
		//TODO finish this
		g.fillRect(1, 100, 75, 50);
		g.fillRect(77, 100, 75, 50);
		g.fillRect(153, 100, 75, 50);
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
			Frame.calcFrame.draw(); // shows calculator
		}
		
	}
}
