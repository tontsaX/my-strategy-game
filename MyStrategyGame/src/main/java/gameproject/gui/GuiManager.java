package gameproject.gui;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.LinkedList;

import javax.swing.JLayeredPane;
import javax.swing.SwingUtilities;

import gameproject.graphics.GameComponent;
import gameproject.graphics.layers.Background;
import gameproject.graphics.layers.Layer;
import gameproject.graphics.layers.SpriteLayer;
import gameproject.io.Mouse;

public class GuiManager {
	
	private static final Toolkit TOOLKIT = Toolkit.getDefaultToolkit();
	
	private Layer background, planetsLayer;
	private JLayeredPane gameLayers;
	private GameWindow gameWindow;
	
	private Mouse mouse;

	private GuiManager() {
		setLayerContainer();
		
		createGameLayers();
		
		assignActionListeners();
		
		assembleGameScreen();
		
		addToGameWindow();
	}
	
	private void setLayerContainer() {
		gameLayers = new JLayeredPane();
		gameLayers.setPreferredSize(new Dimension((int)(GameWindow.GAME_WIDTH * 1.618 * 2), 
												  (int)(GameWindow.GAME_HEIGHT * 1.618 * 2)));
	}
	
	private void createGameLayers() {
		// background layer, creates the background
		background = new Background(gameLayers.getPreferredSize());
		
		// draws planets and their connections
		planetsLayer = new SpriteLayer(gameLayers.getPreferredSize());
		
		// game gui layer, gameplayLayer, (minimap) and planet control
		// new guiLayer
	}
	
	private void assignActionListeners() {
		mouse = new Mouse();
		planetsLayer.addMouseListener(mouse);
		planetsLayer.addMouseMotionListener(mouse);
	}
	
	private void assembleGameScreen() {
		gameLayers.add(background, Integer.valueOf(1));
		gameLayers.add(planetsLayer, Integer.valueOf(2));
		// gameLayers.add(guiLayer, Integer.valueOf(3));
	}
	
	private void addToGameWindow() {
		gameWindow = new GameWindow(gameLayers, "My Strategy Game");
	}
	
	public static GuiManager buildGame() {
		return new GuiManager();
	}
	
	public boolean gameReadyToLaunch() {
		return planetsLayer.readyToLaunch();
	}
	
	public void repaintComponents(LinkedList<GameComponent> gameComponents) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				planetsLayer.setGameComponents(gameComponents);
				planetsLayer.repaint();
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
				mouse.setViewport(planetsLayer.getViewport());
				if(mouse.getDraggedView() != null) {
					planetsLayer.scrollRectToVisible(mouse.getDraggedView());
				}
			}				
		});
	}
}
