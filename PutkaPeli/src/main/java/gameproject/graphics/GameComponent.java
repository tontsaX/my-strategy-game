package gameproject.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Shape;

public abstract class GameComponent {

	protected int x, y, width, height; 
	protected int borderX, borderY, borderW, borderH;
	protected int borderSize;
	
	protected boolean selected;
	protected Color color, borderColor;
	
	public GameComponent(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		borderSize = 6;
		borderX = x - borderSize/2;
		borderY = y - borderSize/2;
		borderW = this.width + borderSize;
		borderH = this.height + borderSize;
	}
	
	public abstract void update();
	public abstract void render(Graphics g);
	
	public void setColorHex(String hex) {
		this.color = Color.decode(hex);
	}
	
	public void setBorderColorHex(String hex) {
		this.borderColor = Color.decode(hex);
	}
	
	public void isSelected(boolean selected) {
		this.selected = selected;
	}
	
	public boolean selected(boolean selected) {
		return this.selected;
	}
	
	public Shape getBounds() {
		return new Rectangle(x, y, width, height);
	}
	
}
