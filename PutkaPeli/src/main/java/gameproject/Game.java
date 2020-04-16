package gameproject;

import java.util.LinkedList;

import gameproject.graphics.GameComponent;
import gameproject.graphics.Planet;
import gameproject.gui.Controller;
import gameproject.gui.GamePanel;
import gameproject.gui.GameWindow;

public class Game {
	
	// logiikka tulee tänne
	Controller controller;
	GamePanel gamePanel;
	GameWindow gameWindow;

	public Game(String title) {
		controller = new Controller(createGUIComponents());
		gamePanel = new GamePanel(controller);
		gameWindow = new GameWindow(gamePanel, title);
	}
	
	private LinkedList<GameComponent> createGUIComponents() {
		LinkedList<GameComponent> components = new LinkedList<>();
		
		components.add(new Planet(288, 208, 1, 0));
		
		return components;
	}
	
	
}
