package es.uniovi.asw.trivial.bussines.gameClasses;

import java.util.Map;
import java.util.Set;

import es.uniovi.asw.trivial.bussines.exceptions.IllegalActionException;
import es.uniovi.asw.trivial.model.Question;
import es.uniovi.asw.trivial.model.Score;
import es.uniovi.asw.trivial.model.Square;
import es.uniovi.asw.trivial.model.User;

public class Game {
	private Board board;
	private Dice dice;

	private Map<String, User> players;

	private Question question;
	private User activePlayer;

	private User winner;

	public int getPlayerLocation(String userName) throws IllegalActionException {
		User player = getPlayerIfExists(userName);
		return player.getLocation();
	}

	public Score getPlayerScore(String userName) throws IllegalActionException {
		User player = getPlayerIfExists(userName);
		return player.getScore();
	}

	public int rollDice() throws IllegalActionException {
		// TODO
		return dice.roll();
	}

	public Square movePlayerTo(int squareNumber, String userName)
			throws IllegalActionException {
		Square square = getSquareIfExists(squareNumber);
		if (!getMovements(userName, getPlayerLocation(userName)).contains(
				square))
			throw new IllegalActionException("The player " + userName
					+ "can't move to the requested square.");


		players.get(userName).setLocation(squareNumber);
		dice.reset();
		//TODO actuar en funcion de la casilla, recordar interacción dado
		return square;
	}

	public Set<Integer> getMovements(String userName, int squareNumber)
			throws IllegalActionException {
		User player = getPlayerIfExists(userName);
		assertIsActivePlayer(userName);
		assertUserIsAt(userName, squareNumber);
		return board.getSquaresAtDistance(squareNumber, dice.roll());
	}

	public Question getQuestion(String userName, int squareNumber)
			throws IllegalActionException {
		return question;
	}

	public boolean isAnswerCorrect(int questionId, String answer,
			String userName, int squareNumber) throws IllegalActionException {
		// TODO
		return false;
	}

	public String getWinner() {
		return (winner == null) ? null : winner.getLogin();
	}

	public boolean isFinished() {
		return (winner == null) ? false : true;

	}

	public String getActivePlayer() throws IllegalActionException {
		return activePlayer.getLogin();
	}

	public int getStartSquare() throws IllegalActionException {
		return board.getStartPosition();
	}

	public String[] getSquares() throws IllegalActionException {
		return null;
	}

	// //////////////////////////////////////////////////////////////////////////////////////

	private Square getSquareIfExists(int squareNumber)
			throws IllegalActionException {
		Square square = board.getSquare(squareNumber);
		if (square == null)
			throw new IllegalActionException("The requested square "
					+ squareNumber + " does not exist.");
		return square;
	}

	private User getPlayerIfExists(String userName)
			throws IllegalActionException {
		User player = players.get(userName);
		if (player == null)
			throw new IllegalActionException("User " + userName
					+ "not found in the current game.");
		return player;
	}

	private void assertIsActivePlayer(String userName)
			throws IllegalActionException {
		if (!activePlayer.getLogin().equals(userName))
			throw new IllegalActionException();
	}

	private void assertUserIsAt(String userName, int squareNumber) throws IllegalActionException {
		User player = getPlayerIfExists(userName);
		if (player.getLocation() == squareNumber)
			throw new IllegalActionException("The player " + player.getLogin() + "is not at square " + squareNumber);
	}
}
