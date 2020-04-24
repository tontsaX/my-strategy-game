package gameproject.gui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class GameWindow extends JFrame {

	/**
	 * default serial, something about using correct Seriazable objects
	 */
	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 1080;
	public static final int HEIGHT = 680;
	
	public GameWindow(GamePanel game, String title) {
		setTitle(title);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setMaximumSize(new Dimension(WIDTH, HEIGHT));
		setMinimumSize(new Dimension(WIDTH, HEIGHT));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setIgnoreRepaint(true);
		
		JScrollPane scrollPane = new JScrollPane(game);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		//add(game);
		getContentPane().add(scrollPane);
		
		setVisible(true);
	}
}
