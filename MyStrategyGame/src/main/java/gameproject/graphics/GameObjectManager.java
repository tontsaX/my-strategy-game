package gameproject.graphics;

import java.util.LinkedList;

public class GameObjectManager {
	
	// LinkedList is better for data manipulating than ArrayList
	private LinkedList<GameComponent> components;
	
	public GameObjectManager() {
		components = createTestGameComponents();
	}
	
	private LinkedList<GameComponent> createTestGameComponents() {
		LinkedList<GameComponent> components = new LinkedList<>();
		
		// Planet(coordinates x y, width, height, velocity/speed x y)
		Planet earth = new Planet(1100, 800, 50, 50, 0, 0);
		Planet mars = new Planet(133, 150, 40, 40, 0, 0);
		
		earth.setColorHex("#1E90FF");
		earth.setBorderColorHex("#00BFFF");
		mars.setColorHex("#CD5C5C");
		mars.setBorderColorHex("#F08080");
		
		components.add(earth);
		components.add(mars);
		
		return components;
	}
	
	public void addGameComponent(GameComponent component) {
		components.add(component);
	}
	
	public void removeGameComponent(GameComponent component) {
		components.remove(component);
	}
	
	public LinkedList<GameComponent> getGameComponents() {
		return components;
	}
	
	public void updateGameComponents() {
		for(GameComponent gameComponent: components) {
			gameComponent.update();
		}
	}
	
	public void updateVelocity(double lagFactor) {
		for(GameComponent go: components) {
			if(go instanceof GameObject) {
				((GameObject) go).updateVelocity(lagFactor);
			}
		}
	}

	public void makeGameComponentSelected(int x, int y) {
		for(GameComponent go: components) {
			if(go.getBounds().contains(x, y)) {
				go.isSelected(true);
			}
			else {
				go.isSelected(false);
			}
		}
	}
}
