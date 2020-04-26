package gameproject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;

import gameproject.graphics.GameComponent;
import gameproject.graphics.Handler;
import gameproject.graphics.Planet;
import gameproject.gui.GamePanel;
import gameproject.gui.GameWindow;
import gameproject.io.IO;
import gameproject.io.Machine;
import gameproject.io.Mouse;

// This class is responsible of running the game
public class Game implements Runnable {

	private static final Toolkit TOOLKIT = Toolkit.getDefaultToolkit();
	
	private static final int MILLISECONDS = 1000;
	private static final int FPS = 60;
	//private static final double MS_PER_UPDATE = MILLISECONDS / FPS;
	private static final long MS_PER_UPDATE = MILLISECONDS / FPS;
	
	private IO console;
	private Mouse mouse;
	
	private Handler handler;
	
	private GamePanel gamePanel;
	private GameWindow gameWindow;
	
	private Boolean running;
	private Thread animator;

	public Game(String title) {
		console = new Machine();
		mouse = new Mouse();
		
		handler = new Handler(createGameComponents());
		
		initializeGameScreen();
		
		initializeGameWindow(title);
	}
	
	private LinkedList<GameComponent> createGameComponents() {
		LinkedList<GameComponent> components = new LinkedList<>();
		
		// Planet(coordinates x y, width, height, velocity/speed x y)
		Planet earth = new Planet(1100, 800, 50, 50, 0, 0);
		Planet mars = new Planet(133, 150, 40, 40, 0, 0);
		
		earth.setColorHex("#1E90FF");
		earth.setBorderColorHex("#00BFFF");
		mars.setColorHex("#CD5C5C");
		mars.setBorderColorHex("#F08080");
		
		components.add(earth);
		components.add(mars);
		
		return components;
	}
	
	public void startGame() {
		if(gamePanel.readyToLaunch()) {
			if(animator == null || !running) {	
				animator = new Thread(this);
				animator.start();
				System.out.println("Animator created and the thread name is " + Thread.currentThread().getName());
			}
		}
	}
	
	public void stopGame() {
		running = false;
		System.out.println("Game stopped.");
	}
	
	// ------GAME LOOP--------------------------------
	@Override
	public void run() {
		gameLoop();
	}
	
	private void gameLoop() {
		running = true;
		System.out.println("Animator set to run and the thread name is " + Thread.currentThread().getName());
		
//		double previousTime = console.time();
//		double lag = 0.0;
		
		while(running) {
//			double currentTime = console.time();
//			double elapsedTime = currentTime - previousTime;
//			previousTime = currentTime;
//			lag += elapsedTime;

			userInput();
			
//			while(lag >= MS_PER_UPDATE) {
				update();
//				lag -= MS_PER_UPDATE;
//			}
			
			// the lag velocity is under 1, so right now with Graphics its makes no difference
			// because Graphics takes integer values as arguments
			// if it'd be Graphics2D, then it'd be useful
//			catchLag(lag / MS_PER_UPDATE);
			//render();

			// important for unix-devices. Needs to be as the last step of the game loop
			TOOLKIT.sync(); 
			
			try {
				Thread.sleep(MS_PER_UPDATE);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		console.exitSystem();
	}
	
	private void userInput() {
		if(mouse.clicked()) {
			System.out.println("Mouse clicked, we are in Game and the thread name is " + Thread.currentThread().getName());
			console.print("Clicked (" 
					+ mouse.getX() + ", "
					+ mouse.getY() + ")");
			handler.makeGameComponentSelected(mouse.getX(), mouse.getY());
		}
		//if(mouse.) // for dragged functionality
	}
	
	private void update() {
		handler.updateGameComponents();
	}
	
	private void catchLag(double scaledVelocity) {
		handler.updateVelocity(scaledVelocity);
	}
	
	private void render() {
		gamePanel.setGameComponents(handler.getGameComponents());
		gamePanel.repaint();
	}
	//----------------------------------------------------
	
	private void initializeGameScreen() {
		gamePanel = new GamePanel();
		//gamePanel.setLayout(new FlowLayout());
		gamePanel.setPreferredSize(new Dimension(1800, 1348));
		gamePanel.setBackground(Color.black);
		gamePanel.addMouseListener(mouse);
	}
	
	private void initializeGameWindow(String title) {
		gameWindow = new GameWindow(gamePanel, title);
		/*
		gameWindow.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent windowEvent) {
				console.exitSystem();
			}
		});
		*/
		
	}
}
