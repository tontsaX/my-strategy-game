package gameproject.io;

import java.awt.event.MouseAdapter; // kevyempi kuin swing kirjastosta. ei huomattavaa eroa, mutta kevyempi
import java.awt.event.MouseEvent;

public class Mouse extends MouseAdapter {

	/* Toiminta-idea
	 * Klikatessa näytölle piirtyy näkymätön tai hyvin pieni muoto,
	 * jonka avulla voidaan tarkistaa risteävätkö juuri piirretty muoto
	 * ja klikkauksen alueella oleva muoto. Kun muodot risteävät on true,
	 * niin se tarkoittaa, että muoto on valittu.
	 * */
	
	private int x, y;
	private boolean clicked;
	
	public Mouse() {
		clicked = false;
	}
	
	public void mouseClicked(MouseEvent e) {
		clicked = true;
		x = e.getX();
		y = e.getY();
	}
	
	public int getX() {
		clicked = false;
		return x;
	}
	
	public int getY() {
		clicked = false;
		return y;
	}
	
	public boolean clicked() {
		return clicked;
	}
}
