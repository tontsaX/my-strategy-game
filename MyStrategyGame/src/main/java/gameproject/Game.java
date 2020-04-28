package gameproject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.LinkedList;

import javax.swing.JViewport;
import javax.swing.SwingUtilities;

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
	private static final double MS_PER_UPDATE = MILLISECONDS / FPS;
	
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
				Machine.printCurrentThreadName("Animator created and the thread name is ");
			}
		}
	}
	
	public void stopGame() {
		running = false;
		console.print("Game stopped.");
	}
	
	// ------GAME LOOP--------------------------------
	@Override
	public void run() {
		gameLoop();
	}
	
	private void gameLoop() {
		running = true;
		Machine.printCurrentThreadName("Animator set to run and the thread name is ");
		
		double previousTime = console.time();
		double lag = 0.0;
		
		while(running) {
			double currentTime = console.time();
			double elapsedTime = currentTime - previousTime;
			previousTime = currentTime;
			lag += elapsedTime;

			userInput();
			
			while(lag >= MS_PER_UPDATE) {
				updateGameObjects();
				lag -= MS_PER_UPDATE;
			}
			
			// the lag velocity is under 1, so right now with Graphics its makes no difference
			// because Graphics takes integer values as arguments
			// if it'd be Graphics2D, then it'd be useful
			catchLag(lag / MS_PER_UPDATE);
			render();
	
		}
		
		console.exitSystem();
	}
	
	private void userInput() {
		if(mouse.dragged()) {
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					mouse.setViewport(gamePanel.getViewport());
					if(mouse.getDraggedView() != null) {
						gamePanel.scrollRectToVisible(mouse.getDraggedView());
					}
				}				
			});
		}
		if(mouse.clicked()) {
			Machine.printCurrentThreadName("Mouse clicked, we are in Game and the thread name is ");
			console.print("Clicked (" 
					+ mouse.getX() + ", "
					+ mouse.getY() + ")");
			handler.makeGameComponentSelected(mouse.getX(), mouse.getY());
		}
		
	}
	
	private void updateGameObjects() {
		handler.updateGameComponents();
	}
	
	private void catchLag(double scaledVelocity) {
		handler.updateVelocity(scaledVelocity);
	}
	
	private void render() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				gamePanel.setGameComponents(handler.getGameComponents());
				gamePanel.repaint();
			}
		});
		TOOLKIT.sync(); // for different systems to be able to handle the swing events correctly
	}
	//----------------------------------------------------
	
	private void initializeGameScreen() {
		gamePanel = new GamePanel();
		//gamePanel.setLayout(new FlowLayout());
		gamePanel.setPreferredSize(new Dimension(1800, 1348));
		gamePanel.setBackground(Color.black);
		gamePanel.setAutoscrolls(true);
		gamePanel.addMouseListener(mouse);
		gamePanel.addMouseMotionListener(mouse);
	}
	
	private void initializeGameWindow(String title) {
		gameWindow = new GameWindow(gamePanel, title);
	}
}
