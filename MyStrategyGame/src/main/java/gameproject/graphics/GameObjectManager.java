package gameproject.graphics;

import java.util.LinkedList;

public class GameObjectManager {
	
	// LinkedList is better for data manipulating than ArrayList
	private LinkedList<GameComponent> components;
	
	public GameObjectManager(LinkedList<GameComponent> gameComponents) {
		components = gameComponents;
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
