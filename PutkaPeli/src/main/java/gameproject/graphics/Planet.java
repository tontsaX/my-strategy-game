package gameproject.graphics;

import java.awt.Graphics;
import java.awt.Shape;

public class Planet extends GameObject {

	public Planet(int x, int y, int width, int height, int velX, int velY) {
		super(x, y, width, height, velX, velY);
	}

	@Override
	public void update() {
		spin();
	}
	
	@Override
	public Shape getBounds() {
		return null; // palauta ellipsi
	}
	
	@Override
	public void render(Graphics g) {
		//g.setColor(Color.getColor("RED"));
		g.setColor(color);
		g.fillOval(x, y, width, height);
	}
	
	private void spin() {
		
	}

}
