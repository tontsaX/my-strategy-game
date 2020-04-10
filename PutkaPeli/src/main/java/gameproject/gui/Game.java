package gameproject.gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

import gameproject.graphics.GameComponent;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	private static final Toolkit TOOLKIT = Toolkit.getDefaultToolkit();

	private String title;
	private Boolean running;
	private Thread game;
	private Handler handler;
	
	public Game(String title) {
		this.title = title;
		handler = new Handler();
		new GameWindow(this);
	}
	
	public synchronized void start() {
		game = new Thread(this);
		game.start();
	}
	
	public synchronized void stop() {
		running = false;
	}

	@Override
	public void run() {
		running = true;
		
		while(running) {
			System.out.println("Toimintaa");
			// process user input
			update();
			render();
			TOOLKIT.sync(); // importan for unix-devices. Needs to be as a last step in the game loop

			// sleep
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		stop();
	}
	
	private void update() {
		for(GameComponent gc: handler.gameComponents()) {
			gc.tick();
		}
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, GameWindow.WIDTH, GameWindow.HEIGHT);
		
		for(GameComponent gc: handler.gameComponents()) {
			gc.render(g);
		}
		
		g.dispose();
		bs.show();
	}
	
	public String getTitle() {
		return this.title;
	}
}
