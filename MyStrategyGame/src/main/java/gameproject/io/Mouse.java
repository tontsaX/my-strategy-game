package gameproject.io;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter; // kevyempi kuin swing kirjastosta. ei huomattavaa eroa, mutta kevyempi
import java.awt.event.MouseEvent;

import javax.swing.JViewport;

public class Mouse extends MouseAdapter {
	
	private volatile Point clickedPoint, pressedPoint;
	private volatile boolean mouseClicked, mouseDragged;
	private volatile Rectangle dragView;
	private volatile JViewport gamePanelViewport;
	
	public Mouse() {
		mouseClicked = false;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		pressedPoint = e.getPoint();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		mouseClicked = true;
		clickedPoint = e.getPoint();
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
        mouseDragged = true;
        if(pressedPoint != null) {
        	if(gamePanelViewport != null) {
            	int deltaX = pressedPoint.x - e.getX();
                int deltaY = pressedPoint.y - e.getY(); 
                
                dragView = gamePanelViewport.getViewRect();
                dragView.x += deltaX;
                dragView.y += deltaY;
        	}
        }
	}
	
	public void setViewport(JViewport viewport) {
		gamePanelViewport = viewport;
	}
	
	public Point getClickedPoint() {
		return clickedPoint;
	}
	
	public int getX() {
		mouseClicked = false;
		return clickedPoint.x;
	}
	
	public int getY() {
		mouseClicked = false;
		return clickedPoint.y;
	}
	
	public Rectangle getDraggedView() {
		mouseDragged = false;
		return dragView;
	}
	
	public boolean clicked() {
		return mouseClicked;
	}
	
	public boolean dragged() {
		return mouseDragged;
	}
}
