package gameproject.graphics;

public abstract class GameObject extends GameComponent {
	
	protected int velX, velY;

	public GameObject(int x, int y, int width, int height, int velX, int velY) {
		super(x, y, width, height);
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
