package model;

import java.awt.Point;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/** Clase "Score" del modelo.
 * @author Gonz�lez Fernandez Cristian
 * @author Vel�zquez Vico �lvaro
 * @version 1 - Last changes: -
 * 
 * @author Montero Hernández, José Antonio
 * @version 2 - Last changes: -
 * 		- Added interconnections between Squares.
 * 		- Revamped attributes.
 * 		- Implemented Comparable
 * 		- Added hashCode() and equals()
 */

//This class will be the node of the graph, which will be implemented in the 
// logic package
public class Square implements Comparable<Square>{

	private int squareNumber;
	private Category category;
	private SquareType squareType;
	
	private Point position;

	private Map<Integer, Square> connectedSquares;

	public Square(int squareNumber, Category category, SquareType squareType,Point point) {
		this.squareNumber = squareNumber;
		this.category = category;
		this.squareType = squareType;
		connectedSquares = new HashMap<Integer,Square>();
		this.position=point;
	}

	public int getSquareNumber() {
		return squareNumber;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public SquareType getSquareType() {
		return squareType;
	}

	public void setSquareType(SquareType squareType) {
		this.squareType = squareType;
	}

	public void connect(Square square) {
		connectedSquares.put(square.getSquareNumber(), square);
	}
	
	public Collection<Square> getConnectedSquares() {
		return connectedSquares.values();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + squareNumber;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Square other = (Square) obj;
		if (squareNumber != other.squareNumber)
			return false;
		return true;
	}

	@Override
	public int compareTo(Square square) {
		return new Integer(this.getSquareNumber()).compareTo(square.getSquareNumber());
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}
}
