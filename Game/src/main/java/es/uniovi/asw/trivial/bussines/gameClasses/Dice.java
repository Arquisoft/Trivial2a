package es.uniovi.asw.trivial.bussines.gameClasses;

import java.util.Random;

/** Clase "Dice" del modelo.
 * @author Gonz�lez Fernandez Cristian y Vel�zquez Vico �lvaro
 * @version 1 - Last changes: -
 */
public class Dice {
	
	/**
	 * This method throws a dice to get a value in 1-6
	 * @return the value of the dice
	 */
	public static int throwingDice(){
		Random r = new Random();
		
		return r.nextInt(5) + 1;
	}

}
