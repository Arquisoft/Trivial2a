package bussines.gameClasses;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import model.Category;
import model.Square;
import model.SquareType;
import bussines.fileLoaders.GraphFileLoader;

/**
 * Class Board, used to store the Squares, keep their connections and calculate movements.
 * 
 * @author Montero Hernández, José Antonio
 */
public class Board {

	private Map<Integer, Square> squareList = new HashMap<Integer, Square>();
	public Map<Integer, Square> getSquareList() {
		return squareList;
	}

	private BoardOption boardOption;
	private Square startPosition;
	
	public Board(BoardOption boardOption) {
		this.boardOption = boardOption;
		squareList = new GraphFileLoader().loadGrapthFile(boardOption
				.getBoardTextFileUrl());
		setStartSquare();
	}

	public String getName() {
		return boardOption.getName();
	}

	public String getMiniatureImageUrl() {
		return boardOption.getMiniatureImageUrl();
	}

	public String getBoardImageUrl() {
		return boardOption.getBoardImageUrl();
	}

	public String getBoardTextFileUrl() {
		return boardOption.getBoardTextFileUrl();
	}

	public int getMinPlayers() {
		return boardOption.getMinPlayers();
	}

	public int getMaxPlayers() {
		return boardOption.getMaxPlayers();
	}

	public Square getSquare(int squareNumber) {
		return squareList.get(squareNumber);
	}
	
	public int size() {
		return squareList.size();
	}

	public int getStartPosition() {
		return startPosition.getSquareNumber();
	}

	public Category getSquareCategory(int squareNumber) {
		return getSquare(squareNumber).getCategory();
	}

	public Set<Integer> getSquaresAtDistance(int currentSquareNumber, int dice) {

		Square currentSquare;
		if ((currentSquare = getSquare(currentSquareNumber)) == null
				|| dice < 0)
			throw new IllegalArgumentException();

		boolean[] visited = new boolean[size()];
		Set<Integer> resultSquares = new HashSet<Integer>();

		return getSquaresAtDistanceDFS(currentSquare, dice, 0, visited,
				resultSquares);
	}

	private Set<Integer> getSquaresAtDistanceDFS(Square currentSquare,
			int dice, int distance, boolean[] visited,
			Set<Integer> resultSquares) {
		
		visited[currentSquare.getSquareNumber() - 1] = true;
		
		if(dice == distance) {
			resultSquares.add(currentSquare.getSquareNumber());
			return resultSquares;
		}
		
		for (Square square : currentSquare.getConnectedSquares())
			if (!visited[square.getSquareNumber() - 1])
				getSquaresAtDistanceDFS(square, dice, distance + 1, visited, resultSquares);
		
		return resultSquares;
	}
	
	private void setStartSquare() {
		for(Square square : squareList.values())
			if (square.getSquareType().equals(SquareType.FINAL))
				startPosition = square;
	}

}
