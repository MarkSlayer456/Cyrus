package main.managers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class InputManager implements KeyListener, MouseListener {

	public static ArrayList<Integer> keysDown = new ArrayList<Integer>();
	
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
