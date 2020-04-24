package gameproject.graphics;

import java.util.LinkedList;

// This class controls and updates game components
public class Handler {
	
	// LinkedList is better for data manipulating than ArrayList
	private LinkedList<GameComponent> components;
	
	public Handler(LinkedList<GameComponent> gameComponents) {
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
	
	public void updateVelocity(double velocity) {
		for(GameComponent go: components) {
			if(go instanceof GameObject) {
				((GameObject) go).updateVelocity(velocity);
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
