package main.managers;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import main.Frame;

public class InputManager implements KeyListener, MouseListener {

	private ArrayList<Integer> keysDown;
	private ArrayList<Character> currentCharacters;
	private String currentCommand;
	
	public InputManager() { // creates inputmanager
		this.keysDown = new ArrayList<Integer>();
		this.currentCharacters = new ArrayList<Character>();
		this.currentCommand = "";
	}
	
	public void drawWhatUserIsCurrentlyTyping(Graphics g) { // Rename this
		String temp = "";
		for(int i = 0; i < this.currentCharacters.size(); i++) {
			char z = this.currentCharacters.get(i);
			temp += z;
			g.drawString(temp, 40, 275);
		}
	}
	
	public ArrayList<Character> getCurrentChars() {
		return this.currentCharacters;
	}
	
	
	public void clearCurrentChars() {
		this.currentCharacters.clear();
	}
	
	public void doLogic() {
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	}

	
	@Override
	public void keyPressed(KeyEvent e) {
		this.keysDown.add(e.getKeyCode());
		int keyCode = e.getKeyCode();
		if(keyCode != 8 && keyCode != 10 && keyCode != 9 && keyCode != 16) { // checks for tab(9), enter(10), backspace(8), shift(16)
			this.currentCommand += (e.getKeyChar() + "");
			this.currentCharacters.add(e.getKeyChar());
		} else {
			//TODO get what key was pressed and do what needs to be done
			switch(keyCode) {
			case 9:
				this.currentCommand += ("    ");
				break;
			case 10:
				Frame.cyrus.interpret(this.currentCommand);
				this.clearCurrentChars();
				System.out.println("You typed: " + this.currentCommand);
				this.currentCommand = "";
				break;
			case 8:
				//TODO
				if(!(this.currentCharacters.isEmpty())) {
					this.currentCharacters.remove(this.currentCharacters.size() - 1);
					this.currentCommand = "";
					for(int i = 0; i < this.currentCharacters.size(); i++) {
						char a = this.currentCharacters.get(i);
						this.currentCommand += a;
					}
				}
				break;
			case 16:
				// Do nothing
				break;
				default:
					System.out.println("Error, unknown key");
					break;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//TODO There has to be a better way to do this
		for(int i = 0; i < this.keysDown.size(); i++) {
			if(this.keysDown.get(i) == e.getKeyCode()) {
				this.keysDown.remove(i);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

}