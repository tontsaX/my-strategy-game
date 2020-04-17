package gameproject.graphics;

import java.awt.Color;
import java.awt.Graphics;

public class Planet extends GameObject {

	public Planet(int x, int y, int velX, int velY) {
		super(x, y, velX, velY);
	}

	@Override
	public void tick() {
		spin();
	}
	
	@Override
	public void render(Graphics g) {
		//g.setColor(Color.getColor("RED"));
		g.setColor(color);
		g.fillOval(x, y, 32, 32);
	}
	
	private void spin() {
		
	}

}
