package gameproject.graphics;

public abstract class GameObject extends GameComponent {
	
	protected int velocityX, velocityY;

	public GameObject(int x, int y, int width, int height, int velX, int velY) {
		super(x, y, width, height);
		this.velocityX = velX;
		this.velocityY = velY;
	}

	/*
	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
	}
	*/
	
	public void updateVelocity(double velocity) {
		if(velocity >= 1) {
			velocityX *= (int) velocity;
			velocityY *= (int) velocity;
		}
	}
	
}
