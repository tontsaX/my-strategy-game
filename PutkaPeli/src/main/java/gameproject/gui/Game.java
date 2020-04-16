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
	private Thread animator;
	private Handler handler;
	
	public Game(String title) {
		this.title = title;
		handler = new Handler();
		
		gameWindow = new GameWindow(this);
		gameWindow.createBufferStrategy(3);
	}
	
	/*
	public void addNotify() {
		super.addNotify(); // the JFrame should notify after the game is added to it
		startGame();
	}*/
	
	public void startGame() {
		if(animator == null || !running) {	
			animator = new Thread(this);
			animator.start();
		}
	}
	
	public void stopGame() {
		running = false;
		System.out.println("Game stopped.");
	}

	@Override
	public void run() {
		running = true;
		
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
			catchLag(lag / MS_PER_UPDATE);
			render(); 
			
			// important for unix-devices. Needs to be as the last step of the game loop
			TOOLKIT.sync(); 
		}
		
		System.exit(0);
	}
	
	private void update() {
		for(GameComponent gameComponent: handler.gameComponents()) {
			gameComponent.tick();
		}
	}
	
	private void render() {
		BufferStrategy bufferStrategy = gameWindow.getBufferStrategy();
		
		Graphics graphics = bufferStrategy.getDrawGraphics();
		
		graphics.setColor(Color.black);
		graphics.fillRect(0, 0, GameWindow.WIDTH, GameWindow.HEIGHT);
			
		for(GameComponent gameComponent: handler.gameComponents()) {
			gameComponent.render(graphics);
		}
		
		graphics.dispose();
		bufferStrategy.show();
	}
	
	private void catchLag(double scaledVelocity) {
		handler.updateVelocity(scaledVelocity);
	}
	
	public String getTitle() {
		return this.title;
	}
}
