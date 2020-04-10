package gameproject;

import gameproject.gui.Game;
import gameproject.gui.GameWindow;

public class Main {

	public static void main(String[] args) {
		play();
	}

	private static void play() {
		Game game = new Game("My Strategy Game");
		game.start();
	}
	
}
