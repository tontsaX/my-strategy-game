package gameproject;

import gameproject.gui.GamePanel;
import gameproject.gui.GameWindow;

public class Game {

	public Game(String title) {
		GamePanel game = new GamePanel();
		new GameWindow(game, title);
	}
}
