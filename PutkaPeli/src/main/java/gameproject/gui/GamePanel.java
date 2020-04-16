package gameproject.gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import gameproject.graphics.GameComponent;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private boolean addedToFrame = false;

	private Handler handler;
	
	public GamePanel(Handler controller) {
		this.handler = controller;
	}
	
	// Connects this component to a native screen resource
	public void addNotify() {
		super.addNotify();
		addedToFrame = true;
	}
	
	public boolean readyToLaunch() {
		return addedToFrame;
	}
	
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		graphics.setColor(Color.black);
		graphics.fillRect(0, 0, GameWindow.WIDTH, GameWindow.HEIGHT);
			
		for(GameComponent gameComponent: handler.gameComponents()) {
			gameComponent.render(graphics);
		}
	}
	
}
