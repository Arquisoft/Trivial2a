package model;

import java.util.List;

/** Clase "Score" del modelo.
 * @author González Fernandez Cristian y Velázquez Vico álvaro
 * @version 1 - Last changes: -
 */
public class Panel {
	
	private List<Square> squares;
	
	public Panel(List<Square> squares){
		this.squares = squares;
	}
	
	public List<Square> getSquares() {
		return squares;
	}
	
	public void setSquares(List<Square> squares) {
		this.squares = squares;
	}	

}
