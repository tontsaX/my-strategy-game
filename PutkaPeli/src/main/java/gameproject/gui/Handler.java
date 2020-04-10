package gameproject.gui;

import java.util.LinkedList;

import gameproject.graphics.GameComponent;
import gameproject.graphics.Planet;

// this class is responsible for assembling all game components and elements together
// provides game components to the Game class
public class Handler {
	
	// LinkedList is better for data manipulating than ArrayList
	private LinkedList<GameComponent> components;
	
	public Handler() {
		// game components are created here
		components = new LinkedList<>();
		addGameComponent(new Planet(288, 208, 5, 0));
	}
	
	public void addGameComponent(GameComponent component) {
		components.add(component);
	}
	
	public void removeGameComponent(GameComponent component) {
		int index = components.indexOf(component);
		if(index != 0) { // if components have some sort of things that aren't supposed to be removed
			components.remove(component);
		}
	}
	
	public LinkedList<GameComponent> gameComponents() {
		return components;
	}
}
