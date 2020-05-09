package gameproject.graphics.layers;

import java.awt.Color;
import java.awt.Dimension;

public class Background extends Layer {
	
	private static final long serialVersionUID = 1L;
	
	public Background(Dimension size) {
		super(size);
		setBackground(Color.black);
	}
	
}
