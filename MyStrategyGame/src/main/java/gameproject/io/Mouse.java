package gameproject.io;

import java.awt.Point;
import java.awt.event.MouseAdapter; // kevyempi kuin swing kirjastosta. ei huomattavaa eroa, mutta kevyempi
import java.awt.event.MouseEvent;

public class Mouse extends MouseAdapter {
	
	//private volatile int x, y;
	private volatile Point point;
	private volatile boolean clicked, dragged;
	
	public Mouse() {
		clicked = false;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		clicked = true;
		point = new Point(e.getX(), e.getY());
		System.out.println("Mouse clicked and the thread name is " + Thread.currentThread().getName());
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
        dragged = true;
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		dragged = false;
	}
	
	public Point getPoint() {
		return point;
	}
	
	public int getX() {
		clicked = false;
		return point.x;
	}
	
	public int getY() {
		clicked = false;
		return point.y;
	}
	
	public boolean clicked() {
		return clicked;
	}
}
