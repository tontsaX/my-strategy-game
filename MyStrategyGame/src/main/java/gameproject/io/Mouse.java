package gameproject.io;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter; // kevyempi kuin swing kirjastosta. ei huomattavaa eroa, mutta kevyempi
import java.awt.event.MouseEvent;

public class Mouse extends MouseAdapter {
	
	private volatile Point point;
	private volatile boolean mouseClicked, mouseDragged;
	private volatile Rectangle dragView;
	
	public Mouse() {
		mouseClicked = false;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		mouseClicked = true;
		point = e.getPoint();
		Machine.printCurrentThreadName("Mouse clicked and the thread name is ");
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		System.out.println("Mouse dragged.");
        mouseDragged = true;
        dragView = new Rectangle(e.getX(), e.getY(), 1,1);
	}
	
	public Point getPoint() {
		return point;
	}
	
	public int getX() {
		mouseClicked = false;
		return point.x;
	}
	
	public int getY() {
		mouseClicked = false;
		return point.y;
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
