package gameproject;

public class Main {

	public static void main(String[] args) {
		newGame();
	}
	
	public static void newGame() {
		Game game = new Game("My Game");
		game.startGame();
	}
	
}
