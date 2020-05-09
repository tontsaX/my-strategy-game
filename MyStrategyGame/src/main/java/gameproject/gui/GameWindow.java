package gameproject.gui;

import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import gameproject.io.Machine;

public class GameWindow extends JFrame {
	/**
	 * default serial, something about using correct Seriazable objects
	 */
	private static final long serialVersionUID = 1L;
	
//	public static final int WIDTH = 1080; //  720
//	public static final int HEIGHT = 680; //  454
	
	public GameWindow(JComponent game, String title) {
		setTitle(title);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
//		setLocationRelativeTo(null);
		setIgnoreRepaint(true);
		
		getContentPane().add(game);
		pack();
		setVisible(true);
	}
}
