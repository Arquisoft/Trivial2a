package es.uniovi.asw.trivial.bussines.gameClasses;

<<<<<<< HEAD
=======
import java.awt.Point;
import java.util.HashMap;
import java.util.List;
>>>>>>> API-Logica
import java.util.Map;
import java.util.Set;

import es.uniovi.asw.trivial.bussines.exceptions.IllegalActionException;
import es.uniovi.asw.trivial.model.BoardOption;
import es.uniovi.asw.trivial.model.Question;
import es.uniovi.asw.trivial.model.Score;
import es.uniovi.asw.trivial.model.Square;
import es.uniovi.asw.trivial.model.SquareType;
import es.uniovi.asw.trivial.model.User;

public class Game {
	private Board board;
	private Dice dice;

	private Map<String, User> players;

	private Question question;
	private User activePlayer;

	private User winner;

	public Game(BoardOption boardOption, List<String> players) throws IllegalActionException {
		//TODO comprobar que los jugadores existen en la base de datos
		board = new Board(boardOption);
		this.players = new HashMap<String, User>();
		
		for (String userName : players) {
			User player = new User(userName);
			player.setLocation(getStartSquare());
			this.players.put(userName, player);
		}
	}

	public int getPlayerLocation(String userName) throws IllegalActionException {
		User player = getPlayerIfExists(userName);
		return player.getLocation();
	}

	public Score getPlayerScore(String userName) throws IllegalActionException {
		User player = getPlayerIfExists(userName);
		return player.getScore();
	}

	public int rollDice() throws IllegalActionException {
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

		switch (square.getSquareType()) {
		case DICE:
			dice.reset();
			break;
		case FINAL:
			if (activePlayer.canWin())
				winner = activePlayer;
			break;
		case GAME_PIECE:
			dice.makeUnavailable();
			// TODO Obtener pregunta de la base de datos
			break;
		case NORMAL:
			dice.makeUnavailable();
			// TODO Obtener pregunta de la base de datos
			break;
		}
		return square;
	}

	public Set<Integer> getMovements(String userName, int squareNumber)
			throws IllegalActionException {
		assertExistsPlayer(userName);
		assertIsActivePlayer(userName);
		assertUserIsAt(userName, squareNumber);

		return board.getSquaresAtDistance(squareNumber, dice.roll());
	}

	public Question getQuestion(String userName, int squareNumber)
			throws IllegalActionException {
		assertExistsPlayer(userName);
		assertIsActivePlayer(userName);
		assertUserIsAt(userName, squareNumber);
		assertQuestionCanBeAnswered();

		return question;
	}

	public boolean isAnswerCorrect(int questionId, String answer,
			String userName, int squareNumber) throws IllegalActionException {
		assertExistsPlayer(userName);
		assertIsActivePlayer(userName);
		assertUserIsAt(userName, squareNumber);
		assertQuestionCanBeAnswered();
		asserIsCurrentQuestion(questionId);

		boolean correcta = question.isCorrectAnswer(answer);
		SquareType squareType = board.getSquare(squareNumber).getSquareType();
		if (squareType.equals(SquareType.GAME_PIECE)) {
			switch (question.getCategory()) {
			case ART_AND_LITERATURE:
				activePlayer.getScore().setArtAndLiterature(correcta);
				break;
			case GEOGRAPHY:
				activePlayer.getScore().setGeography(correcta);
				break;
			case HISTORY:
				activePlayer.getScore().setHistory(correcta);
				break;
			case SCIENCE_AND_TECHNOLOGY:
				activePlayer.getScore().setScienceAndTechnology(correcta);
				break;
			case SHOWS_AND_ENTERTAINMENT:
				activePlayer.getScore().setShowsAndEntertainment(correcta);
				break;
			case SPORTS:
				activePlayer.getScore().setSports(correcta);
				break;
			default:
				throw new IllegalActionException(
						"No se puede responder una pregunta de esa categoría.");
			}
		}

		if (!correcta)
			passTurn();
		else
			dice.reset();
		return correcta;
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

	public Map<Integer, Point> getSquares() throws IllegalActionException {
		// TODO pendiente de que en el fichero se guarden las coordenadas
		// TODO pendiente de que las Square almacenen sus coordenadas
		return null;
	}

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

	private void assertExistsPlayer(String userName)
			throws IllegalActionException {
		if (players.get(userName) == null)
			throw new IllegalActionException("User " + userName
					+ "not found in the current game.");
	}

	private void assertIsActivePlayer(String userName)
			throws IllegalActionException {
		if (!activePlayer.getLogin().equals(userName))
			throw new IllegalActionException();
	}

	private void asserIsCurrentQuestion(int questionId)
			throws IllegalActionException {
		if (question == null || question.getId() != questionId)
			throw new IllegalActionException(
					"Intento de acceso a pregunta no activa.");
	}

	private void assertUserIsAt(String userName, int squareNumber)
			throws IllegalActionException {
		User player = getPlayerIfExists(userName);
		if (player.getLocation() == squareNumber)
			throw new IllegalActionException("The player " + player.getLogin()
					+ "is not at square " + squareNumber);
	}

	private void assertQuestionCanBeAnswered() throws IllegalActionException {
		if (question == null)
			throw new IllegalActionException("No hay pregunta disponible.");
	}

	private void passTurn() {
		// TODO
	}
}
