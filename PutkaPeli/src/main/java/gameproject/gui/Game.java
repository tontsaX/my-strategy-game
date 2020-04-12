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
		double previousTime = System.currentTimeMillis();
		double lag = 0.0;
		
		while(running) {
			double currentTime = System.currentTimeMillis();
			double elapsedTime = currentTime - previousTime;
			previousTime = currentTime;
			lag += elapsedTime;

			// process user input
			
			while(lag >= MS_PER_UPDATE) {
				update();
				lag -= MS_PER_UPDATE;
			}
			
			// the lag velocity is under 1, so right now with Graphics its makes no difference
			// because Graphics takes integer values as arguments
			// if it'd be Graphics2D, then it'd be useful
			double scaledVelocity = lag / MS_PER_UPDATE;
			render(scaledVelocity); 
			
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
		BufferStrategy bufferStrategy = gameWindow.getBufferStrategy();
		
		if(bufferStrategy == null) {
			gameWindow.createBufferStrategy(3);
			return;
		}
		
		Graphics graphics = bufferStrategy.getDrawGraphics();
		
		graphics.setColor(Color.black);
		graphics.fillRect(0, 0, GameWindow.WIDTH, GameWindow.HEIGHT);
		
		handler.updateVelocity(velocity);
		
		for(GameComponent gameComponent: handler.gameComponents()) {
			gameComponent.render(graphics);
		}
		
		graphics.dispose();
		bufferStrategy.show();
	}
	
	public String getTitle() {
		return this.title;
	}
}
