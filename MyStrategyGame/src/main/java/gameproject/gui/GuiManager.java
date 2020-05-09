package gameproject.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.LinkedList;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import gameproject.graphics.GameComponent;
import gameproject.graphics.layers.SpriteLayer;
import gameproject.io.Mouse;

public class GuiManager {
	
	private static final Toolkit TOOLKIT = Toolkit.getDefaultToolkit();
	
	
	private SpriteLayer spriteLayer;
	private GameWindow gameWindow;
	
	private Mouse mouse;

	private GuiManager() {
		JLayeredPane gameLayers = new JLayeredPane();
		gameLayers.setPreferredSize(new Dimension(1800, 1348));
		
		// background layer, sisältää planeetta- ja niiden reittianimaatiot
		JPanel backgroundLayer = new JPanel();
		backgroundLayer.setSize(new Dimension(1800, 1348));
		backgroundLayer.setBackground(Color.black);
		backgroundLayer.setPreferredSize(new Dimension(1800, 1348));
		
		// sprite layer, käsittelee pieniä piirroksia kuten mahdolliset liikenuolet
		spriteLayer = new SpriteLayer(new Dimension(1800, 1348)); 
		spriteLayer.setOpaque(false);
		
		mouse = new Mouse();
		spriteLayer.addMouseListener(mouse);
		spriteLayer.addMouseMotionListener(mouse);
		
		// game gui layer, minimap ja planeettojen tiedot ruudut 
		
		gameLayers.add(backgroundLayer, Integer.valueOf(1));
		gameLayers.add(spriteLayer, Integer.valueOf(2));

		JScrollPane gameScreen = new JScrollPane(gameLayers);
		// leveyttä ja korkeutta tarttee vähän muokkailla
		gameScreen.getViewport().setPreferredSize(new Dimension(1032, 650));
//		gameScreen.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		gameWindow = new GameWindow(gameScreen, "My Strategy Game");
	}
	
	private void initializeBackground() {
		
	}
	
	public static GuiManager buildGame() {
		return new GuiManager();
	}
	
	public boolean gameReadyToLaunch() {
		return spriteLayer.readyToLaunch();
	}
	
	public void repaintComponents(LinkedList<GameComponent> gameComponents) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				spriteLayer.setGameComponents(gameComponents);
				spriteLayer.repaint();
			}
		});
		TOOLKIT.sync(); // for different systems to be able to handle the swing events correctly
	}
	
	public int mouseX() {
		return mouse.getX();
	}
	
	public boolean mouseClicked() {
		return mouse.clicked();
	}
	
	public Point getClickedPoint() {
		return mouse.getClickedPoint();
	}
	
	public boolean mouseDragged() {
		return mouse.dragged();
	}
	
	public void scrollToView() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				mouse.setViewport(spriteLayer.getViewport());
				if(mouse.getDraggedView() != null) {
					spriteLayer.scrollRectToVisible(mouse.getDraggedView());
				}
			}				
		});
	}
}
