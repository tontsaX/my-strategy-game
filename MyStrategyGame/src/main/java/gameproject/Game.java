package gameproject;

import java.awt.Point;
import java.util.LinkedList;

import gameproject.graphics.GameComponent;
import gameproject.graphics.GameObjectManager;
import gameproject.graphics.Planet;
import gameproject.gui.GuiManager;
import gameproject.io.IO;
import gameproject.io.Console;

// This class is responsible of running the game
public class Game implements Runnable {
	
	private static final int MILLISECONDS = 1000;
	private static final int FPS = 60;
	private static final double MS_PER_UPDATE = MILLISECONDS / FPS;
	
	private IO console;
	
	private GameObjectManager gameObjectManager;
	private GuiManager guiManager;
	
	private Boolean running;
	private Thread game;

	public Game(String title) {
		console = new Console();
		
		gameObjectManager = new GameObjectManager();
		
		guiManager = GuiManager.buildGame();
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
			gameObjectManager.makeGameComponentSelected(point.x, point.y);
		}
		
	}
	
	private void updateGameObjects() {
		gameObjectManager.updateGameComponents();
	}
	
	private void catchLag(double lagFactor) {
		gameObjectManager.updateVelocity(lagFactor);
		
	}
	
	private void render() {
		guiManager.repaintComponents(gameObjectManager.getGameComponents());
	}
	//---------------------------- end of game loop methods
}
