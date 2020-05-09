package gameproject.graphics.layers;

import java.awt.Dimension;
import java.awt.Graphics;

import gameproject.graphics.GameComponent;

public class SpriteLayer extends Layer {

	private static final long serialVersionUID = 1L;
	
	public SpriteLayer(Dimension size) {
		super(size);
		setOpaque(false);
	}
	
	@Override
	public void paintComponent(Graphics graphics) {
		if(gameComponents != null) {
			super.paintComponent(graphics);
			
			for(GameComponent gameComponent: gameComponents) {
				gameComponent.render(graphics);
			}
			
			graphics.dispose();
		}
	}
}