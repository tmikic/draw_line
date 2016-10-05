package com.tmikic.core.graphics;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.tmikic.core.actionlistner;
import com.tmikic.core.application;

public class Screen {

	private static int width;
	private static int height;
	public int[] pixels;
	public BufferedImage bi;
	
	Robot robot = null;
	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		
		
	    try {
	        robot = new Robot();
	    } catch (AWTException e1) {
	        // TODO Auto-generated catch block
	        e1.printStackTrace();
	    }
	}

	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0xffffff;
		}

	}

	public void render() {

	}

	static actionlistner ac = new actionlistner();
	static application app = new application();
	public static void print(Graphics g) {
		File export = new File("C:\\drawline");
		;
		if (!export.exists()) {
			
    	    boolean result = false;

    	    try{
    	    	export.mkdirs();
    	        result = true;
    	    } 
    	    catch(SecurityException se){
    	        //handle it
    	    }        
    	    if(result) {    
    	        System.out.println("DIR created");  
    	    }
		}
    	    
		  BufferedImage image = new BufferedImage(app.width*3,app.height*3,BufferedImage.TYPE_INT_ARGB);
	    // Create a `BufferedImage` and create the its `Graphics`
	   image = GraphicsEnvironment.getLocalGraphicsEnvironment()
	            .getDefaultScreenDevice().getDefaultConfiguration()
	            .createCompatibleImage(app.width*3, app.height*3);

       Graphics2D graphics = (Graphics2D) g;
	    // Print to BufferedImage
	    BufferedImage bimage = 
	            ((Graphics2D) graphics).getDeviceConfiguration().createCompatibleImage(
	              width*3, height*3, Transparency.TRANSLUCENT);
	    app.paint(graphics);
	    graphics.dispose();
	    // Output the `BufferedImage` via `ImageIO`
	    try {
	        ImageIO.write(bimage, "png", new File(export+"\\Image.png"));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    System.out.println("done");
	    ac.done = 1;
	}

}
