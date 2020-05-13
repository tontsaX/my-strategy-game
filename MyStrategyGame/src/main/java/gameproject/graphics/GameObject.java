package gameproject.graphics;

public abstract class GameObject extends GameComponent {
	
	protected int orgVelX, orgVelY;
	protected int velocityX, velocityY;

	public GameObject(int x, int y, int width, int height, int velX, int velY) {
		super(x, y, width, height);
		this.velocityX = velX;
		this.velocityY = velY;
		orgVelX = velocityX;
		orgVelY = velocityY;
	}
	
	// if lag, update to the original values
	// then calculate velocity values by lag factor
	public void updateVelocity(double lagFactor) {
		setVelocitiesToOriginals();
		
		if(lagFactor >= 1) {
			velocityX *= (int) lagFactor;
			velocityY *= (int) lagFactor;
		}
	}
	
	public void setVelocityX(double velX) {
		velocityX = (int)velX;
	}
	
	public void setVelocityY(double velY) {
		velocityY = (int) velY;
	}
	
	public void setVelocitiesToOriginals() {
		setVelocityX(orgVelX);
		setVelocityY(orgVelY);
	}
	
}
