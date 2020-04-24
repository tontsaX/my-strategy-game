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
	
	public static final int WIDTH = 720; // 1080
	public static final int HEIGHT = 454; // 680
	
	public GameWindow(GamePanel game, String title) {
		setTitle(title);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setMaximumSize(new Dimension(WIDTH, HEIGHT));
		setMinimumSize(new Dimension(WIDTH, HEIGHT));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setIgnoreRepaint(true);
		
		// outo lag ja jos lisää layoutin, niin koko homma menee rikki
		JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		//scrollPane.getViewport().setPreferredSize(new Dimension(WIDTH, HEIGHT));
		scrollPane.getViewport().add(game);
		scrollPane.getViewport().setDoubleBuffered(true);
		scrollPane.setDoubleBuffered(true);
		
        //scrollPane.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
		
		//add(game);
		add(scrollPane);
		
		//setVisible(true);
	}
}
