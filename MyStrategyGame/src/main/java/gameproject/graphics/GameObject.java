package gameproject.graphics;

public abstract class GameObject extends GameComponent {
	
	private final int orgVelX, orgVelY;
	protected int velocityX, velocityY;

	public GameObject(int x, int y, int width, int height, int velX, int velY) {
		super(x, y, width, height);
		this.velocityX = velX;
		this.velocityY = velY;
		orgVelX = velocityX;
		orgVelY = velocityY;
	}
	
	// sets new velocity which is updated on the next cycle
	/* vika, jos velocity on 1 ja lag update on 2 velocity päivitetään
	 * 1*2, jolloin velocityksi jää 2. Jos seuraavalla kierroksella jäädään
	 * taas jälkeen 2, niin velocityksi tulee 4 jne. Velocity ei palaudu alkuperäiseen
	 * tällä hetkellä. */
	public void updateVelocity(double velocity) {
		setVelocityX(orgVelX);
		setVelocityY(orgVelY);
		
		if(velocity >= 1) {
			velocityX *= (int) velocity;
			velocityY *= (int) velocity;
		}
	}
	
	public void setVelocityX(double velX) {
		velocityX = (int)velX;
	}
	
	public void setVelocityY(double velY) {
		velocityY = (int) velY;
	}
	
}
