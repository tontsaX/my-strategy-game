package gameproject.graphics.layers;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import javax.swing.JPanel;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;

import gameproject.graphics.GameComponent;

public class SpriteLayer extends JPanel {

	private static final long serialVersionUID = 1L;
	private boolean addedToFrame = false;

	private LinkedList<GameComponent> gameComponents;
	
	public SpriteLayer() {
		gameComponents = new LinkedList<>();
	}
	
	public SpriteLayer(Dimension size) {
		setSize(size);
		gameComponents = new LinkedList<>();
	}
	
	public boolean readyToLaunch() {
		return addedToFrame;
	}
	
	public void setGameComponents(LinkedList<GameComponent> gameComponents) {
		this.gameComponents = gameComponents;
	}
	
	@Override
	public void addNotify() {
		super.addNotify();
		addedToFrame = true;
	}
	
	@Override
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		
		for(GameComponent gameComponent: gameComponents) {
			gameComponent.render(graphics);
		}
		
		graphics.dispose();
	}
	
	// from scrollable demo
//	public Dimension getPreferredScrollableViewportSize() {
//        return new Dimension(GameWindow.WIDTH, GameWindow.WIDTH);
//    }
	
	@Override
	public Dimension getPreferredSize() {
		return getSize();
	}
	
	public JViewport getViewport() {
    	return (JViewport) SwingUtilities.getAncestorOfClass(JViewport.class, this);
    }
	
}