package es.uniovi.asw.trivial.bussines.gameClasses;

import java.util.Random;

import es.uniovi.asw.trivial.bussines.exceptions.IllegalActionException;

/**
 * Clase "Dice" del modelo.
 * 
 * @author Gonz�lez Fernandez Cristian y Vel�zquez Vico �lvaro
 * @version 1 - Last changes: -
 */
public class Dice {

	private boolean available;
	private Integer lastRoll;

	public int roll() throws IllegalActionException {
		if (!isAvailable())
			throw new IllegalActionException("The dice can't be rolled at this state of the game.");
	
		if (lastRoll == null )
			lastRoll = new Random(System.currentTimeMillis()).nextInt(5) + 1;
		return lastRoll;
	}
	
	public void makeUnavailable() {
		available = false;
	}
	
	public void makeAvailable() {
		available = true;
	}
	
	public boolean isAvailable() {
		return available;
	}
	
	public void reset() {
		available = true;
		lastRoll = null;
	}

}
