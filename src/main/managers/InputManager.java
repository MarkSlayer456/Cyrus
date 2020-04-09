package main.managers;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.List;

import main.AI;
import main.CyrusMain;
import main.Frame;
import main.utilities.Button;
import main.utilities.Calculator;

public class InputManager implements KeyListener, MouseListener, MouseWheelListener {

	public static InputManager inputManager;
	private CyrusMain cyrusMain;

	public static InputManager getInstance() {
		return inputManager;
	}
	
	private ArrayList<Integer> keysDown;
	private ArrayList<Character> currentCharacters;
	private String currentCommand;
	
	public InputManager() { // creates inputmanager
		this.keysDown = new ArrayList<Integer>();
		this.currentCharacters = new ArrayList<Character>();
		this.currentCommand = "";
		cyrusMain = CyrusMain.getInstance();
	}
	
	/**
	 * Clears all the currently stored characters that the user has typed
	 */
	public void clearCurrentChars() {
		this.currentCharacters.clear();
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	}

	
	@Override
	public void keyPressed(KeyEvent e) {
		Frame activeFrame = cyrusMain.getActiveFrame();
		if(activeFrame == cyrusMain.getMainFrame()) {
			this.keysDown.add(e.getKeyCode());
			int keyCode = e.getKeyCode();
			for(int i = 0; i < this.keysDown.size(); i++) {
				if(this.keysDown.get(i) == 17 || keyCode == 17) { // probably a better way to do this but still works
					return;
				}
			}
			if(keyCode != 8 && keyCode != 10 && keyCode != 9 && keyCode != 16
					&& keyCode != KeyEvent.VK_UP && keyCode != KeyEvent.VK_DOWN) { // checks for tab(9), enter(10), backspace(8), shift(16)
				this.currentCommand += (e.getKeyChar() + "");
				this.currentCharacters.add(e.getKeyChar());
			} else {
				switch(keyCode) {
				case 9:
					this.currentCommand += ("    ");
					break;
				case 10:
					cyrusMain.getCyrus().interpret(this.currentCommand);
					this.clearCurrentChars();
					System.out.println("You typed: " + this.currentCommand); //TODO remove later
					CyrusMain main2 = CyrusMain.getInstance();
					AI cyrus2 = main2.getCyrus();
					cyrus2.addToCommands(this.currentCommand);
					cyrus2.setCurrCommand(-1);
					this.currentCommand = "";
					main2.getMainFrame().getUIManager().setOffset(0);
					break;
				case 8:
					if(!(this.currentCharacters.isEmpty())) {
						this.currentCharacters.remove(this.currentCharacters.size() - 1);
						this.currentCommand = "";
						for(int i = 0; i < this.currentCharacters.size(); i++) {
							char a = this.currentCharacters.get(i);
							this.currentCommand += a;
						}
					}
					break;
				case KeyEvent.VK_UP:
					CyrusMain main = CyrusMain.getInstance();
					AI cyrus = main.getCyrus();
					List<String> list = cyrus.getCommands();
					if(!(cyrus.getCurrCommand() >= list.size()-1)) {
						cyrus.increaseCurrCommand();
						String command = list.get(cyrus.getCurrCommand());
						this.currentCommand = command;
						this.clearCurrentChars();
						for(int i = 0; i < command.length(); i++) {
							this.currentCharacters.add(command.charAt(i));
						}
					}
					break;
				case KeyEvent.VK_DOWN:
					CyrusMain main1 = CyrusMain.getInstance();
					AI cyrus1 = main1.getCyrus();
					if(cyrus1.getCurrCommand() > 0) {
						cyrus1.decreaseCurrCommand();
						List<String> list1 = cyrus1.getCommands();
						String command1 = list1.get(cyrus1.getCurrCommand());
						this.currentCommand = command1;
						this.clearCurrentChars();
						for(int i = 0; i < command1.length(); i++) {
							this.currentCharacters.add(command1.charAt(i));
						}
					} else {
						this.currentCommand = "";
						this.clearCurrentChars();
					}
					break;
				case 16:
					// Do nothing
					break;
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
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
		int mx = e.getX(); // mouse x
		int my = e.getY(); // mouse y
		Calculator calc = cyrusMain.getCalc();
		Frame activeFrame = cyrusMain.getActiveFrame();
		if(activeFrame == calc.getFrame()) {
			for(Button b : calc.getButtons()) {
				if(b.getRect().contains(new Point(mx, my))) {
					calc.clickButton(b);
				}
			}
		} else if(activeFrame == cyrusMain.getMainFrame()) {
			
		} else {
			System.out.println("ERROR unknown window dectected!");
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		int notches = e.getWheelRotation();
		CyrusMain cyrus = CyrusMain.getInstance();
		AI ai = cyrus.getCyrus();
		if(notches > 0) {
			cyrus.getMainFrame().getUIManager().scrolldown(ai);
		} else {
			cyrus.getMainFrame().getUIManager().scrollup(ai);
		}
	}	
	
	///// Setters /////

	///// Getters /////
	public ArrayList<Character> getCurrentCharacters() {
		return this.currentCharacters;
	}
	
	public String getCurrentCommand() {
		return this.currentCommand;
	}

	
}
