package gameproject.gui;

import java.util.LinkedList;

import gameproject.graphics.GameComponent;
import gameproject.graphics.GameObject;
import gameproject.graphics.Planet;

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
		/*
		int index = components.indexOf(component);
		if(index != 0) { // if components have some sort of things that aren't supposed to be removed
			components.remove(component);
		}
		*/
		/*
		if(!(component instanceof GUIComponent)) {
			components.remove(component);
		}
		*/
		components.remove(component);
	}
	
	public LinkedList<GameComponent> gameComponents() {
		return components;
	}
	
	public void updateVelocity(double velocity) {
		for(GameComponent go: components) {
			if(go instanceof GameObject) {
				((GameObject) go).updateVelocity(velocity);
			}
		}
	}
}
