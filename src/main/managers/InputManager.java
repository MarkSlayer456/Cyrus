package main.managers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import main.Frame;

public class InputManager implements KeyListener, MouseListener {

	public static ArrayList<Integer> keysDown = new ArrayList<Integer>();
	private String currentCommand = "";
	
	public static void doLogic() {
		if(keysDown.contains(8)) { //TODO This is for testing can be removed later
			System.out.println("8");
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	}

	
	@Override
	public void keyPressed(KeyEvent e) {
		keysDown.add(e.getKeyCode());
		int keyCode = e.getKeyCode();
		if(keyCode != 8 && keyCode != 10 && keyCode != 9 && keyCode != 16) { // checks for tab(9), enter(10), backspace(8), shift(16)
		currentCommand += (e.getKeyChar() + "");
		} else {
			//TODO get what key was pressed and do what needs to be done
			switch(keyCode) {
			case 9:
				currentCommand += ("    ");
				break;
			case 10:
				Frame.cyrus.interpret(currentCommand);
				System.out.println("You typed: " + currentCommand);
				currentCommand = "";
				break;
			case 8:
				//TODO
				break;
			case 16:
				// Do nothing
				break;
				default:
					System.out.println("Error, unknown key");
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//TODO There has to be a better way to do this
		for(int i = 0; i < keysDown.size(); i++) {
			if(keysDown.get(i) == e.getKeyCode()) {
				keysDown.remove(i);
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
