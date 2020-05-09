package gameproject.graphics.layers;

import java.awt.Dimension;
import java.util.LinkedList;

import javax.swing.JPanel;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;

import gameproject.graphics.GameComponent;

public abstract class Layer extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	protected boolean addedToFrame = false;
	protected LinkedList<GameComponent> gameComponents;
	
	public Layer(Dimension size) {
		setSize(size);
	}
	
	public void setGameComponents(LinkedList<GameComponent> gameComponents) {
		this.gameComponents = gameComponents;
	}
	
	public boolean readyToLaunch() {
		return addedToFrame;
	}
	
	public JViewport getViewport() {
    	return (JViewport) SwingUtilities.getAncestorOfClass(JViewport.class, this);
    }
	
	@Override
	public void addNotify() {
		super.addNotify();
		addedToFrame = true;
	}
	
	@Override
	public Dimension getPreferredSize() {
		return getSize();
	}
	
}
