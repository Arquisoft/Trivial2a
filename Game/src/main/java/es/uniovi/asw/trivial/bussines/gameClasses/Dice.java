package es.uniovi.asw.trivial.bussines.gameClasses;

import java.util.Random;

/** Clase "Dice" del modelo.
 * @author Gonz�lez Fernandez Cristian y Vel�zquez Vico �lvaro
 * @version 1 - Last changes: -
 */
public class Dice {
	
	private boolean available;
	private int lastRoll;
	public int roll(){
		Random r = new Random();
		
		return r.nextInt(5) + 1;
	}
	
	public void reset() {
		available = true;
	}

}
