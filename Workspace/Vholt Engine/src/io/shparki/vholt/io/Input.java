package io.shparki.vholt.io;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Input implements KeyListener, MouseListener {
	
	private static ArrayList<Integer> downKeys = new ArrayList<Integer>();
	private static ArrayList<Integer> pressedKeys = new ArrayList<Integer>();
	private static ArrayList<Integer> releasedKeys = new ArrayList<Integer>();
	
	private static ArrayList<Integer> downMouse = new ArrayList<Integer>();
	private static ArrayList<Integer> pressedMouse = new ArrayList<Integer>();
	private static ArrayList<Integer> releasedMouse = new ArrayList<Integer>();
	
	public static boolean isKeyDown(int keyCode){ return downKeys.contains(Integer.valueOf(keyCode)); }
	public static boolean isKeyPressed(int keyCode) { return pressedKeys.contains(Integer.valueOf(keyCode)); }
	public static boolean isKeyReleased(int keyCode) { return releasedKeys.contains(Integer.valueOf(keyCode)); }
	
	public static boolean isMouseDown(int button) { return downMouse.contains(Integer.valueOf(button)); }
	public static boolean isMousePressed(int button) { return pressedMouse.contains(Integer.valueOf(button)); }
	public static boolean isMouseReleased(int button) { return releasedMouse.contains(Integer.valueOf(button)); }
	
	
	
	public static void update(){
		pressedKeys.clear();
		releasedKeys.clear();
		
		pressedMouse.clear();
		releasedMouse.clear();
	}


	public void keyPressed(KeyEvent e) {
		if (!isKeyDown(e.getKeyCode())){
			if (!isKeyPressed(e.getKeyCode())){
				pressedKeys.add(Integer.valueOf(e.getKeyCode()));
			}
			downKeys.add(Integer.valueOf(e.getKeyCode()));
		}
	}
	public void keyReleased(KeyEvent e) {
		if (isKeyDown(e.getKeyCode())){
			if (!isKeyReleased(e.getKeyCode())){
				releasedKeys.add(Integer.valueOf(e.getKeyCode()));
			}
			downKeys.remove(Integer.valueOf(e.getKeyCode()));
		}
	}
	public void mousePressed(MouseEvent e) { 
		if (!isMouseDown(e.getButton())){
			if (!isMousePressed(e.getButton())){
				pressedMouse.add(Integer.valueOf(e.getButton()));
			}
			downMouse.add(Integer.valueOf(e.getButton()));
		}
	}
	public void mouseReleased(MouseEvent e) { 
		if (isMouseDown(e.getButton())){
			if (!isMouseReleased(e.getButton())){
				releasedMouse.add(Integer.valueOf(e.getButton()));
			}
			downMouse.remove(Integer.valueOf(e.getButton()));
		}
	}
	
	public void mouseClicked(MouseEvent e) {  }
	public void mouseEntered(MouseEvent e) {  }
	public void mouseExited(MouseEvent e) {  }
	public void keyTyped(KeyEvent e) {  }
	
}
