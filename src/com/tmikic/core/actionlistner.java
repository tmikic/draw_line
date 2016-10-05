package com.tmikic.core;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.tmikic.core.graphics.Screen;


public class actionlistner implements ActionListener {
	public static int done = 1;
	 public void actionPerformed(ActionEvent e) {
		if(e.getSource() == application.grid1){
			application.grid = 1;
		}
        if(e.getSource() == application.grid2){
        	application.grid = 0;
		}
        if(e.getSource() == application.snap1){
			application.snap = 1;
		}
        if(e.getSource() == application.snap2){
        	application.snap = 0;
		}
        if(e.getSource() == application.export){
        	done = 0;
		}
		
	}

}
