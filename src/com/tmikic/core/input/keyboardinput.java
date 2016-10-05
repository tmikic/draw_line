package com.tmikic.core.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.tmikic.core.application;
import com.tmikic.core.graphics.Screen;

public class keyboardinput implements KeyListener{

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
   public static int endline= 0;
	
	int run = 0;
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == 68){
			System.out.println("d was pressed");
			endline =1;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
