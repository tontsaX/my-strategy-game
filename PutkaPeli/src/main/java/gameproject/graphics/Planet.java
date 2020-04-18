package gameproject.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

public class Planet extends GameObject {

	public Planet(int x, int y, int width, int height, int velocityX, int velocityY) {
		super(x, y, width, height, velocityX, velocityY);
	}

	@Override
	public void update() {
		spin();
	}
	
	@Override
	public Shape getBounds() {
		return new Ellipse2D.Float(x, y, width, height);
	}
	
	@Override
	public void render(Graphics g) {
		if(selected) {
			g.setColor(Color.cyan);
			g.fillOval(borderX, borderY, borderW, borderH);
		}
		g.setColor(color);
		g.fillOval(x, y, width, height);
	}
	
	private void spin() {

	}

}
