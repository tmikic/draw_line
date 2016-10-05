package com.tmikic.core;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.sound.sampled.Line;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.tmikic.core.graphics.Screen;
import com.tmikic.core.graphics.drawline;
import com.tmikic.core.input.keyboardinput;
import com.tmikic.core.input.mouseinput;

public class application extends Canvas implements Runnable {

	private static final long serialVersionUID = -4087822441542237397L;
	public static int width = 300;
	public static int height = width / 16 * 9;
	public static int scale = 3;

	private Thread thread;
	private JFrame frame;
	private boolean running = false;

	private Screen screen;
	private mouseinput line;
	private MouseEvent e;

	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	public application() {

		Dimension size = new Dimension(width * scale-4, height * scale-10);
		setPreferredSize(size);
         
		screen = new Screen(width, height);
		line = new mouseinput();
		frame = new JFrame();

	}

	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}

	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}
    int run = 0;
    public static int[]gridx = new int[width*scale^2];
    public static int[]gridy = new int[height*scale^2];
    public static int snap = 0;
    
    
	public void run() {
		long lastTime = System.nanoTime();
		long lasttimems = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		double delta1 =0;
		while (running) {
			long now = System.nanoTime();

			delta += (now - lastTime) / ns;
			delta1 += (now - lastTime) / ns;
			
          
            
            lastTime = System.nanoTime();
			while (delta >= 1) {
				this.addMouseMotionListener(new mouseinput());
				this.addMouseListener(new mouseinput());
				this.addKeyListener(new keyboardinput());
                
				update();
				delta--;
				

			}
			render();
			
			
               
				
				delta1--;

				 
			
		}
		 
	}

	public void update() {
		line.update();
	}
	static int grid = 0;

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();

		screen.clear();
		g.setColor(Color.white);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		if(grid == 1){
			g.setColor(Color.cyan);
		for (int i = 0; i <= height * scale; i = i + 28) {
			g.drawLine(0, i, width * scale, i);
		}
		for (int i = 0; i <= width * scale; i = i + 28) {
			g.drawLine(i, 0, i, height * scale);
		}
		}
		g.setColor(Color.black);
		line.drawline(g);
		if(actionlistner.done == 0){
			screen.print(g);
		}
		g.dispose();
		
		bs.show();

	}
    
	public static int x, y;
    public static JMenuItem grid1 = new JMenuItem("grid mode on");
	public static JMenuItem grid2 = new JMenuItem("grid mode off");
	public static JMenuItem snap1 = new JMenuItem("snap mode on");
	public static JMenuItem snap2 = new JMenuItem("snap mode off");
	public static JMenuItem export = new JMenuItem("export as png");
	public static void main(String[] args) {
		grid1.addActionListener(new actionlistner());
		grid2.addActionListener(new actionlistner());
		snap1.addActionListener(new actionlistner());
        snap2.addActionListener(new actionlistner());
        export.addActionListener(new actionlistner());
		   JMenuBar mb = new JMenuBar();
	        JMenu menu1 = new JMenu("modes");
	        JMenu menu2 = new JMenu("export");
	        menu1.add(grid1);
	        menu1.add(grid2);
	        menu1.add(snap1);
	        menu1.add(snap2);
	        menu2.add(export);
	        mb.add(menu1);
	        mb.add(menu2);
	        
	       
		application app = new application();
		app.frame.setJMenuBar(mb);
		app.frame.validate();
		app.frame.repaint();
		app.frame.setResizable(false);
		app.frame.setTitle("drawline");
		app.frame.add(app);
		app.frame.pack();
		app.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.frame.setLocationRelativeTo(null);
		app.frame.setVisible(true);
		x = app.frame.getLocationOnScreen().x;
		y = app.frame.getLocationOnScreen().y;
		app.start();
		
	}

	

}
