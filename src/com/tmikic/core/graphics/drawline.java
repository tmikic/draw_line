package com.tmikic.core.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import com.tmikic.core.input.mouseinput;

public class drawline {
	private mouseinput input = new mouseinput();
	MouseEvent e = null;

	public void render(Graphics g) {
		input.mousePressed(e);
		g.setColor(Color.black);
		int sx, sy;
		sx = e.getX();
		sy = e.getY();
		boolean start = false, last = false, pressedl = false, pressedr = false;
		if (mouseinput.getMousePressed().getButton() == MouseEvent.BUTTON1) {
			if (start == false) {
				sx = input.getMouseMoved().getX();
				sy = input.getMouseMoved().getY();
			}
			pressedl = true;
		}
		if (pressedl == true && pressedr == false) {
			int ex = 0, ey = 0;
			ex = input.getMouseMoved().getX();
			ey = input.getMouseMoved().getX();
			g.drawLine(sx, sy, ex, ey);
		}
		if (mouseinput.getMousePressed().getButton() == MouseEvent.BUTTON3) {
			int ex = 0, ey = 0;
			ex = mouseinput.getMousePressed().getX();
			ey = mouseinput.getMouseMoved().getX();
			g.drawLine(sx, sy, mouseinput.getMousePressed().getX(), mouseinput.getMousePressed().getY());
			pressedr = true;
		}
	}
}
