package gameproject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.LinkedList;

import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import gameproject.graphics.GameComponent;
import gameproject.graphics.Handler;
import gameproject.graphics.Planet;
import gameproject.graphics.layers.SpriteLayer;
import gameproject.gui.GameWindow;
import gameproject.gui.GuiManager;
import gameproject.io.IO;
import gameproject.io.Console;
import gameproject.io.Mouse;

// This class is responsible of running the game
public class Game implements Runnable {
	
	private static final int MILLISECONDS = 1000;
	private static final int FPS = 60;
	private static final double MS_PER_UPDATE = MILLISECONDS / FPS;
	
	private IO console;
	
	private Handler handler;
	private GuiManager guiManager;
	
	private Boolean running;
	private Thread game;

	public Game(String title) {
		console = new Console();
		
		handler = new Handler(createGameComponents());
		
		guiManager = GuiManager.buildGame();
	}
	
	// This will go to GameObjectManager
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
		if(guiManager.gameReadyToLaunch()) {
			if(game == null || !running) {	
				game = new Thread(this);
				game.start();
			}
		}
	}
	
	public void stopGame() {
		running = false;
	}
	
	// ------GAME LOOP--------------------------------
	@Override
	public void run() {
		gameLoop();
	}
	
	private void gameLoop() {
		running = true;
		
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
		
		console.print("Game stopped.");
		console.exitSystem();
	}
	
	private void userInput() {
		if(guiManager.mouseDragged()) {
			guiManager.scrollToView();
		}
		if(guiManager.mouseClicked()) {
			Point point = guiManager.getClickedPoint();
			handler.makeGameComponentSelected(point.x, point.y);
		}
		
	}
	
	private void updateGameObjects() {
		handler.updateGameComponents();
	}
	
	private void catchLag(double lagFactor) {
		handler.updateVelocity(lagFactor);
		
	}
	
	private void render() {
		guiManager.repaintComponents(handler.getGameComponents());
	}
	//---------------------------- end of game loop methods
}
