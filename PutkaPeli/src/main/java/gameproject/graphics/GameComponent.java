package gameproject.graphics;

import java.awt.Graphics;

public abstract class GameComponent {

	protected int x, y;
	protected boolean selected;
	
	public GameComponent(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public abstract void update();
	public abstract void render(Graphics g);
}
