package gameproject.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import javax.swing.JPanel;
import javax.swing.Scrollable;

import gameproject.graphics.GameComponent;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private boolean addedToFrame = false;

	private LinkedList<GameComponent> gameComponents;
	
	// muista grid layout
	public GamePanel() {
		gameComponents = new LinkedList<>();
	}
	
	@Override
	public void addNotify() {
		super.addNotify();
		addedToFrame = true;
	}
	
	public boolean readyToLaunch() {
		return addedToFrame;
	}
	
	public void setGameComponents(LinkedList<GameComponent> gameComponents) {
		this.gameComponents = gameComponents;
	}
	
	@Override
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		//graphics.setColor(Color.black);
		//graphics.fillRect(0, 0, GameWindow.WIDTH, GameWindow.HEIGHT);
		//graphics.fillRect(0, 0, 1200, 900);

		for(GameComponent gameComponent: gameComponents) {
			gameComponent.render(graphics);
		}
		
		graphics.dispose();
	}
	
}