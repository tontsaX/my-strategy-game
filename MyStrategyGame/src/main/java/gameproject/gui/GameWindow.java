package gameproject.gui;

import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class GameWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public static final int GAME_WIDTH = 1032;
	public static final int GAME_HEIGHT = (int)(GAME_WIDTH * 0.618); // golden ratio
	
	public GameWindow(JComponent gameBuild, String title) {
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
//		setLocationRelativeTo(null);
		setIgnoreRepaint(true);
		
		JScrollPane gameScreen = new JScrollPane(gameBuild);
		gameScreen.getViewport().setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
		getContentPane().add(gameScreen);
		
		pack();
		setVisible(true);
	}
}
