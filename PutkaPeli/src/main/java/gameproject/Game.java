package gameproject;

import java.awt.Toolkit;
import java.util.LinkedList;

import gameproject.graphics.GameComponent;
import gameproject.graphics.Planet;
import gameproject.gui.GamePanel;
import gameproject.gui.GameWindow;
import gameproject.gui.Handler;
import gameproject.io.IO;
import gameproject.io.Machine;
import gameproject.io.Mouse;

// This class has the game logic and is responsible of the game loop
public class Game implements Runnable {
	
	// game loop objects
	private static final Toolkit TOOLKIT = Toolkit.getDefaultToolkit();
	
	private static final int FPS = 60;
	private static final double MS_PER_UPDATE = 1000 / FPS; // 1000 ms / FPS = milliseconds per frame
	
	private Boolean running;
	private Thread animator;
	private IO machine;
	
	// logiikka tulee tänne
	Handler handler;
	GamePanel gamePanel;
	GameWindow gameWindow;
	Mouse mouse;

	public Game(String title) {
		machine = new Machine<>();
		mouse = new Mouse(machine);
		
		handler = new Handler(createGameComponents());
		gamePanel = new GamePanel(handler);
		gamePanel.addMouseListener(mouse);
		gameWindow = new GameWindow(gamePanel, title);
		startGame();
	}
	
	private LinkedList<GameComponent> createGameComponents() {
		LinkedList<GameComponent> components = new LinkedList<>();
		
		Planet earth = new Planet(288, 208, 0, 0);
		Planet mars = new Planet(133, 150, 0, 0);
		
		earth.setHexColor("#0000FF");
		mars.setHexColor("#FF0000");
		
		components.add(earth);
		components.add(mars);
		
		return components;
	}
	
	public void startGame() {
		if(gamePanel.readyToLaunch()) {
			if(animator == null || !running) {	
				animator = new Thread(this);
				animator.start();
			}
		}
	}
	
	public void stopGame() {
		running = false;
		System.out.println("Game stopped.");
	}
	
	// GAME LOOP ////////
	@Override
	public void run() {
		gameLoop();
	}
	
	private void gameLoop() {
		running = true;
		
		double previousTime = machine.time();
		double lag = 0.0;
		
		while(running) {
			double currentTime = machine.time();
			double elapsedTime = currentTime - previousTime;
			previousTime = currentTime;
			lag += elapsedTime;

			// process user input
			userInput();
			
			while(lag >= MS_PER_UPDATE) {
				update();
				lag -= MS_PER_UPDATE;
			}
			
			// the lag velocity is under 1, so right now with Graphics its makes no difference
			// because Graphics takes integer values as arguments
			// if it'd be Graphics2D, then it'd be useful
			catchLag(lag / MS_PER_UPDATE);
			gamePanel.repaint();

			// important for unix-devices. Needs to be as the last step of the game loop
			TOOLKIT.sync(); 
		}
		
		machine.exitSystem();
	}
	
	private void userInput() {
		int[] cursorCoordinates = (int[])machine.getUserInput();
		if(cursorCoordinates != null) {
			machine.print("Clicked (" 
					+ cursorCoordinates[0] + ", "
					+ cursorCoordinates[1] + ")");
		}
	}
	
	private void update() {
		for(GameComponent gameComponent: handler.gameComponents()) {
			gameComponent.tick();
		}
	}
	
	private void catchLag(double scaledVelocity) {
		handler.updateVelocity(scaledVelocity);
	}
	
	///////////////////////////
	
	public void setConsole(IO io) {
		machine = io;
	}
	
}
