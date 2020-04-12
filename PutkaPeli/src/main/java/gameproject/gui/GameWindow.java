package gameproject.gui;

import java.awt.Dimension;

import javax.swing.JFrame;

public class GameWindow extends JFrame {

	/**
	 * default serial, something about using correct Seriazable objects
	 */
	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 640;
	public static final int HEIGHT = 480;
	
	public GameWindow(Game game) {
		setTitle(game.getTitle());
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setMaximumSize(new Dimension(WIDTH, HEIGHT));
		setMinimumSize(new Dimension(WIDTH, HEIGHT));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setIgnoreRepaint(true);
		
		add(game); // itse peli tarvitsee muistaa lisätä peli-ikkunaan
		
		setVisible(true);
	}
}
