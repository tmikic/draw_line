package com.tmikic.core.input;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;

import com.tmikic.core.application;

public class mouseinput implements MouseListener, MouseMotionListener {
	private static MouseEvent mouseClicked;
	private static MouseEvent mousePressed;
	private static MouseEvent mouseReleased;
	private static MouseEvent mouseMoved;
	private static MouseEvent mouseDragged;
	private static MouseEvent mouseEntered;
	private static MouseEvent mouseExited;
	private static MouseWheelEvent mouseWheelMoved;
	private static MouseEvent e1;
	boolean lpressed = false;
	int mx;
	int my;

	@Override
	public void mouseClicked(MouseEvent e) {

		mouseClicked = e;

	}

	@Override
	public void mousePressed(MouseEvent e) {

		mousePressed = e;
	}

	public void mouseReleased(MouseEvent e) {
		restart = 0;
		mouseReleased = e;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		mouseEntered = e;
	}

	@Override
	public void mouseExited(MouseEvent e) {
		mouseExited = e;
	}

	int restart = 0;

	public void mouseWheelMoved(MouseWheelEvent e) {
		mouseWheelMoved = e;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		mx = e.getX();
		my = e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

		mouseMoved = e;
	}

	public void update() {
		mouseClicked = null;
		mousePressed = null;
		mouseReleased = null;
		mouseMoved = null;
		mouseDragged = null;
		mouseEntered = null;
		mouseExited = null;
		mouseWheelMoved = null;
	}

	public MouseEvent getMouseClicked() {
		return mouseClicked;
	}

	public static MouseEvent getMousePressed() {
		return mousePressed;
	}

	public MouseEvent getMouseReleased() {
		return mouseReleased;
	}

	public static MouseEvent getMouseMoved() {
		return mouseMoved;
	}

	public MouseEvent getMouseDragged() {
		return mouseDragged;
	}

	public MouseEvent getMouseEntered() {
		return mouseEntered;
	}

	public MouseEvent getMouseExited() {
		return mouseExited;
	}

	public MouseWheelEvent getMouseWheelMoved() {
		return mouseWheelMoved;
	}

	int ssx = 0, ssy = 0;

	int ex[] = new int[1000];
	int ey[] = new int[1000];
	int sx[] = new int[1000];
	int sy[] = new int[1000];
	int vector = 0;
	int lvector = 1;
	int done = 0;
	int pressed = 0;
	long startTime = System.currentTimeMillis();
	int counter;
	int restarted = 0;
	int resetrestarted = 0;
    int counterx=0;
    int countery=0;
    double temp;
    long temp1;
	public void drawline(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(2));

		lvector = vector;
		if (mouseMoved != null) {
			mx = mouseMoved.getX();
			my = mouseMoved.getY();
		}
		if (mousePressed != null) {

			// System.out.println("test :D");
			if (mousePressed.getButton() == MouseEvent.BUTTON1) {
				// System.out.println("test2 :D");
				sx[vector] = mousePressed.getX();
				sy[vector] = mousePressed.getY();
				if(application.snap == 1){
               temp = sx[0]/28.0;
               System.out.println(sy[0]);
               temp1 = Math.round(temp);
               sx[0]=(int)temp1*28;
               
               System.out.println(temp1*28);
               temp =0;
               
               temp = sy[0]/28.0;
               temp1 = Math.round(temp);
               sy[0] = (int) temp1*28;		   
				}
				vector = 0;
				restarted = 1;
				keyboardinput.endline = 0;

			}
			if (mousePressed.getButton() == MouseEvent.BUTTON3) {
				ex[vector] = mousePressed.getX();
				ey[vector] = mousePressed.getY();
				if(application.snap == 1){
		               temp = ex[vector]/28.0;
		               temp1 = Math.round(temp);
		               System.out.println(ex[vector]);
		               ex[vector]=(int)temp1*28;
		               temp =0;
		              
		               temp = ey[vector]/28.0;
		               temp1 = Math.round(temp);
		                ey[vector] = (int) temp1*28;	
		               
		               	   
						}
				resetrestarted = 1;
				

			}
			restart = 1;

			counter++;
			pressed = 1;

		} else {
			if (counter != 0) {
				vector++;
				counter = 0;
			}
			}
    
		if (vector == 1) {
			g2.drawLine(sx[0], sy[0], (int) mx, (int) my);
		}
   
		if (vector > 1 && keyboardinput.endline == 0) {
			g2.drawLine(ex[vector - 1], ey[vector - 1], mx, my);
		}
		if (vector > 1 && restarted == 0) {
			for (int i = 0; i <= vector - 1; i++) {

				g2.drawLine(sx[0], sy[0], ex[1], ey[1]);

				if (i > 1) {
					g2.drawLine(ex[i - 1], ey[i - 1], ex[i], ey[i]);

				}
			}
		}
		if (restart == 1 && done == 0) {

			done = 1;
		}
		if(restarted == 1 && pressed == 1){
			restarted = 0;
			pressed =0;
		}
      }}

