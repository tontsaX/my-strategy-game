package gameproject.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JPanel;

import gameproject.graphics.GameComponent;

public class GamePanel extends JPanel implements Runnable {

	private static final long serialVersionUID = 1L;
	private static final Toolkit TOOLKIT = Toolkit.getDefaultToolkit();
	
	private static final int FPS = 60;
	private static final double MS_PER_UPDATE = 1000 / FPS; // 1000 ms / FPS = milliseconds per frame
	
	private Boolean running;
	private Controller controller;
	private Thread animator;
	
	public GamePanel(Controller controller) {
		this.controller = controller;
	}
	
	public void addNotify() {
		super.addNotify(); // GameWindow notifies GamePanel to start the game after it's added to GameWindow
		startGame();
	}
	
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
			repaint();

			// important for unix-devices. Needs to be as the last step of the game loop
			TOOLKIT.sync(); 
		}
		
		System.exit(0);
	}
	
	private void update() {
		for(GameComponent gameComponent: controller.gameComponents()) {
			gameComponent.tick();
		}
	}
	
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		graphics.setColor(Color.black);
		graphics.fillRect(0, 0, GameWindow.WIDTH, GameWindow.HEIGHT);
			
		for(GameComponent gameComponent: controller.gameComponents()) {
			gameComponent.render(graphics);
		}
	}
	
	private void catchLag(double scaledVelocity) {
		controller.updateVelocity(scaledVelocity);
	}
	
}
