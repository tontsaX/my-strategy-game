package gameproject;

public class Main {

	public static void main(String[] args) {
		//newGame();
		new Game("My Game");
	}
	
	public static void newGame() {
		Game game = new Game("My Game");
		game.startGame();
	}
	
}
