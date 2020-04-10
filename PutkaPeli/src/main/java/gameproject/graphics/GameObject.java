package gameproject.graphics;

import java.awt.Graphics;

public abstract class GameObject extends GameComponent {
	
	int velX, velY;

	public GameObject(int x, int y, int velX, int velY) {
		super(x,y);
		this.velX = velX;
		this.velY = velY;
	}

	/*
	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
	}
	*/
	
	public void updateVelocity(double velocity) {
		if(velocity >= 1) {
			velX *= (int) velocity;
			velY *= (int) velocity;
		}
	}
}
