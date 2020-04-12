package gameproject.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

import javax.swing.JPanel;

import gameproject.graphics.GameComponent;

public class Game extends JPanel implements Runnable {

	private static final long serialVersionUID = 1L;
	
	private static final Toolkit TOOLKIT = Toolkit.getDefaultToolkit();
	private static final int FPS = 60;
	private static final double MS_PER_UPDATE = 1000 / FPS; // 1000 ms / FPS = milliseconds per frame
	
	private static GameWindow gameWindow;

	private String title;
	private Boolean running;
	private Thread game;
	private Handler handler;
	
	public Game(String title) {
		this.title = title;
		handler = new Handler();
		
		gameWindow = new GameWindow(this);
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
		
		// game loop source: https://gameprogrammingpatterns.com/game-loop.html
		double previous = System.currentTimeMillis();
		double lag = 0.0;
		
		while(running) {
			double current = System.currentTimeMillis();
			double elapsed = current - previous;
			previous = current;
			lag += elapsed;

			// process user input
			
			while(lag >= MS_PER_UPDATE) {
				update();
				lag -= MS_PER_UPDATE;
			}
			
			// the lag velocity is under 1, so right now with Graphics its makes no difference
			// because Graphics takes integer values as arguments
			// if it'd be Graphics2D, then it'd be useful
			render(lag / MS_PER_UPDATE); 
			
			// important for unix-devices. Needs to be as the last step of the game loop
			TOOLKIT.sync(); 
		}
		
		stop();
	}
	
	private void update() {
		for(GameComponent gc: handler.gameComponents()) {
			gc.tick();
		}
	}
	
	private void render(double velocity) {
		BufferStrategy bs = gameWindow.getBufferStrategy();
		
		if(bs == null) {
			gameWindow.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, GameWindow.WIDTH, GameWindow.HEIGHT);
		
		handler.updateVelocity(velocity);
		
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
