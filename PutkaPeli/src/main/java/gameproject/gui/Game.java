package gameproject.gui;

import java.awt.Toolkit;

import javax.swing.JPanel;

public class Game extends JPanel implements Runnable {

	private static final long serialVersionUID = 1L;
	private static final Toolkit TOOLKIT = Toolkit.getDefaultToolkit();

	private String title;
	private Boolean running;
	private Thread game;
	
	public Game(String title) {
		this.title = title;
	}
	
	public void start() {

	}
	
	public void stop() {
		running = false;
	}

	@Override
	public void run() {
		while(running) {
			// process user input
			update();
			// render
			TOOLKIT.sync(); // importan for unix-devices. Needs to be as a last step in the game loop
			// sleep
		}
		stop();
	}
	
	private void update() {
		
	}
	
	public String getTitle() {
		return this.title;
	}
}
