package gameproject.graphics;

import java.awt.Color;

public abstract class GameObject extends GameComponent {
	
	protected int velX, velY;
	protected Color color;

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
	
	public void setHexColor(String hex) {
		this.color = Color.decode(hex);
	}
}
