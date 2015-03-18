package io.shparki.vholt;

import io.shparki.vholt.gfx.Window;
import io.shparki.vholt.io.Input;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;

public class Engine extends Canvas implements Runnable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int TARGET_UPS = 60;
	public static final long SECOND = 1_000_000_000L;
	public static final long PERIOD = SECOND / TARGET_UPS;
	private int currentUPS, currentFPS;
	private int UPS, FPS;

	private volatile boolean running;
	private volatile Thread animator;
	
	private BufferStrategy bs;
	private Graphics2D g2d;
	private Game game;
	
	
	public Engine(int width, int height, String title, String version, Game game){
		this.game = game;
	
		Window.create(width, height, title + " | " + version, this);
		start();
	}
	public Engine(Game game){ this (800, 600, "", "", game); }
	
	
	public synchronized void start(){
		if (!running || animator == null){
			animator = new Thread(this, "Animator");
			animator.start();
		}
	}
	public synchronized void stop(){
		running = false;
	}
	
	public void run(){
		long beforeTime, currentTime = System.nanoTime();
		long deltaTime = 0;
		long upsCounter = 0, secCounter = 0;
		
		init();
		
		running = true;
		while (running){
			beforeTime = currentTime;
			currentTime = System.nanoTime();
			deltaTime = currentTime - beforeTime;
			
			upsCounter += deltaTime;
			if (upsCounter >= PERIOD){
				upsCounter -= PERIOD;
				update();
				currentUPS ++;
			}
			
			render();
			currentFPS ++;
			
			secCounter += deltaTime;
			if (secCounter >= SECOND){
				secCounter -= SECOND;
				UPS = currentUPS; FPS = currentFPS;
				currentUPS = 0; currentFPS = 0;
				System.out.println("UPS: " + UPS + " | FPS: " + FPS);
			}
		}
		System.exit(0);
	}
	
	public void init(){
		game.init();
	}
	public void update(){
		
		game.update();
		
		Input.update();
	}
	public void render(){
		if (bs == null){
			bs = getBufferStrategy();
			if (bs == null){
				createBufferStrategy(3);
				return;
			}
		}
		
		g2d = (Graphics2D) bs.getDrawGraphics();
		
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, Window.getWidth(), Window.getHeight());
		
		game.render(g2d);
		
		bs.show();
		g2d.dispose();
	}
}
