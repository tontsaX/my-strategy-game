package gameproject.gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

public class GameWindow extends JFrame {
	/**
	 * default serial, something about using correct Seriazable objects
	 */
	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 1080; //  720
	public static final int HEIGHT = 680; //  454
	
	public GameWindow(GamePanel game, String title) {
		setTitle(title);
		Dimension frameSize = new Dimension(WIDTH, HEIGHT);
		setPreferredSize(frameSize);
		setMaximumSize(frameSize);
		setMinimumSize(frameSize);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setIgnoreRepaint(true);
		
		JScrollPane scrollPane = new JScrollPane(game);
		scrollPane.getViewport().setPreferredSize(new Dimension(WIDTH, HEIGHT));
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		add(scrollPane);
		setVisible(true);
		System.out.println("GameWindow created and the thread name is " + Thread.currentThread().getName());
	}
}
