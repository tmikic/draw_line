package com.tmikic.core.graphics;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
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
import com.tmikic.core.files.datastoreage;
import com.tmikic.core.input.mouseinput;

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
    static datastoreage ds = new datastoreage();
	static actionlistner ac = new actionlistner();
	static application app = new application();
	
	 static int count = 0;
	 static boolean first = true;
	static BufferedImage fimg = null;
		static BufferedImage img = null;
		private static mouseinput line = new mouseinput();
	public static void print(Graphics g , Container frame) {
		img = new BufferedImage(frame.getWidth(), frame.getHeight(), BufferedImage.TYPE_INT_RGB);
		File data = new File("C:\\drawline\\data\\data.txt");
		if(!data.exists()){
			
		
		
		}else{

			if(first == true){
				
				String s = ds.read();
				System.out.print(s);
				count = Integer.parseInt(s);
				System.out.println(s);
				
				first = false;
			}
		}
		File export = new File("C:\\drawline");
		
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
		
		line.drawline(g);
		 g = img.createGraphics();
		
		
		
		
		frame.paintAll(g);

		g.dispose();
		
		img = img.getSubimage(0, 45,frame.getWidth(), frame.getHeight()-45);
		try {
			count=count+1;
		    ImageIO.write(img, "png", new File(export+"\\Image_"+count+".png"));
		} catch (IOException ex) {
		    ex.printStackTrace();
		}
	    // Output the `BufferedImage` via `ImageIO`
		String a = count + "";
	    ds.writefile(a);
	    System.out.println("done");
	    ac.done = 1;
	}

}
