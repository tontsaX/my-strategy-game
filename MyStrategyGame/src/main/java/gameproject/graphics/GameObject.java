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
	
	// sets new velocity which is updated on the next cycle
	/* vika, jos velocity on 1 ja lag update on 2 velocity päivitetään
	 * 1*2, jolloin velocityksi jää 2. Jos seuraavalla kierroksella jäädään
	 * taas jälkeen 2, niin velocityksi tulee 4 jne. Velocity ei palaudu alkuperäiseen
	 * tällä hetkellä. */
	public void updateVelocity(double velocity) {
		if(velocity >= 1) {
			velocityX *= (int) velocity;
			velocityY *= (int) velocity;
		}
	}
	
}
