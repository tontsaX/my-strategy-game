package gameproject.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Shape;

public abstract class GameComponent {

	protected int x, y, width, height;
	protected boolean selected;
	protected Color color;
	
	public GameComponent(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public abstract void update();
	public abstract void render(Graphics g);
	
	public void setHexColor(String hex) {
		this.color = Color.decode(hex);
	}
	
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	public boolean selected() {
		return this.selected;
	}
	
	public Shape getBounds() {
		return new Rectangle(x, y, width, height);
	}
	
}
