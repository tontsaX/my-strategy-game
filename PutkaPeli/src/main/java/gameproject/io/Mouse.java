package gameproject.io;

import java.awt.event.MouseAdapter; // kevyempi kuin swing kirjastosta. ei huomattavaa eroa, mutta kevyempi
import java.awt.event.MouseEvent;

public class Mouse extends MouseAdapter {
	
	private IO<int[]> io;

	/* Toiminta-idea
	 * Klikatessa näytölle piirtyy näkymätön tai hyvin pieni muoto,
	 * jonka avulla voidaan tarkistaa risteävätkö juuri piirretty muoto
	 * ja klikkauksen alueella oleva muoto. Kun muodot risteävät on true,
	 * niin se tarkoittaa, että muoto on valittu.
	 * */
	
	public Mouse(IO<int[]> io) {
		this.io = io;
	}
	
	public void mouseClicked(MouseEvent e) {
		int[] coordinates = {e.getX(), e.getY()};
		io.userInput(coordinates);
	}
}
