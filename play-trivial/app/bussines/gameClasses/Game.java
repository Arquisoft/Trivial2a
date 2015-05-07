package bussines.gameClasses;

import java.awt.Point;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import models.Question;
import models.Score;
import models.Square;
import models.SquareType;
import models.Statistic;
import models.User;
import persistence.PersistenceServices;
import persistence.QuestionDao;
import persistence.StatisticDao;
import persistence.UserDao;
import bussines.exceptions.IllegalActionException;
import factories.PersistenceFactory;

public class Game {
	private Board board;
	private Dice dice;

	private Map<String, User> players;

	private Question question;
	private User activePlayer;

	private User winner;

	public Game(BoardOption boardOption, List<String> players)
			throws IllegalActionException {
		validateUsers(players);

		board = new Board(boardOption);
		this.players = new HashMap<String, User>();
		dice = new Dice();
		for (String userName : players) {
			// TODO cambiar contraseña al usuario
			User player = new User(userName);
			player.setLocation(getStartSquare());
			this.players.put(userName, player);
		}
		activePlayer = this.players.get(players.get(0));
		dice.makeAvailable();
	}

	private void validateUsers(List<String> players)
			throws IllegalActionException {
		UserDao factory = PersistenceFactory.persistenceFactory()
				.createUserDao();

		for (String name : players) {
			if (!factory.isValidLogin(name)) {
				throw new IllegalActionException(
						"Alguno de los jugadores que van a jugar no están registrados en la BD");
			}
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
				square.getSquareNumber()))
			throw new IllegalActionException("The player " + userName
					+ " can't move to the requested square.");

		players.get(userName).setLocation(squareNumber);

		// Obteniendo todas las preguntas
		QuestionDao factory = PersistenceFactory.persistenceFactory()
				.createQuestionDao();

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
			question = factory.getQuestionByCategory(square.getCategory());
			break;
		case NORMAL:
			dice.makeUnavailable();
			question = factory.getQuestionByCategory(square.getCategory());
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
		
		updateStatistics(correcta, question);
		
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

		if (!correcta){
			passTurn();
		}
		dice.reset();
		return correcta;
	}

	private void updateStatistics(boolean correcta, Question question) {
		Statistic estadisticas = activePlayer.getStatistics();
		
		int inicial = 0;
		
		if(correcta)
			inicial = 1;
		
		switch(question.getCategory()){
			case ART_AND_LITERATURE:
				inicial += estadisticas.getArtAndLiterature();
				estadisticas.setArtAndLiterature(inicial);
				estadisticas.incrementTotalartAndLiterature();
				break;
			case GEOGRAPHY:
				inicial += estadisticas.getGeography();
				estadisticas.setGeography(inicial);
				estadisticas.incrementTotalgeography();
				break;
			case HISTORY:
				inicial += estadisticas.getHistory();
				estadisticas.setHistory(inicial);
				estadisticas.incrementTotalhistory();
				break;
			case SCIENCE_AND_TECHNOLOGY:
				inicial += estadisticas.getScienceAndTechnology();
				estadisticas.setScienceAndTechnology(inicial);
				estadisticas.incrementTotalscienceAndTechnology();
				break;
			case SHOWS_AND_ENTERTAINMENT:
				inicial += estadisticas.getShowsAndEntertainment();
				estadisticas.setShowsAndEntertainment(inicial);
				estadisticas.incrementTotalshowsAndEntertainment();
				break;
			case SPORTS:
				inicial += estadisticas.getSports();
				estadisticas.setSports(inicial);
				estadisticas.incrementTotalsports();
				break;
		default:
			break;
		}
	}

	public String getWinner() {
		return (winner == null) ? null : winner.getLogin();
	}

	public boolean isFinished() {
		if(winner != null){
			PersistenceFactory.persistenceFactory().createStatisticDao().updateStatistic(activePlayer);
		}
		
		return (winner == null) ? false : true;

	}

	public String getActivePlayer() throws IllegalActionException {
		return activePlayer.getLogin();
	}

	public int getStartSquare() throws IllegalActionException {
		return board.getStartPosition();
	}

	public Map<Integer, Point> getSquares() throws IllegalActionException {
		Map<Integer, Point> aux = new HashMap<Integer, Point>();
		for (int i = 0; i < board.getSquareList().size(); i++) {
			aux.put(i + 1, board.getSquare(i + 1).getPosition());
		}
		return aux;
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
		if (player.getLocation() != squareNumber)
			throw new IllegalActionException("The player " + player.getLogin()
					+ " is not at square " + squareNumber);
	}

	private void assertQuestionCanBeAnswered() throws IllegalActionException {
		if (question == null)
			throw new IllegalActionException("No hay pregunta disponible.");
	}

	private void passTurn() {
		String[] nombres = (String[]) players.keySet().toArray(
				new String[players.size()]);

		String nextPlayer = activePlayer.getLogin();

		for (int i = 0; i < nombres.length; i++) {
			if (nextPlayer.equals(nombres[i])) {
				if (i == (nombres.length - 1)) {
					nextPlayer = nombres[0];
					break;
				} else {
					nextPlayer = nombres[i + 1];
					break;
				}
			}
		}
		activePlayer = players.get(nextPlayer);
		dice.makeAvailable();
	}

	public void addUserToGame(String id) {

		User u = null;

		for (User user : PersistenceFactory.persistenceFactory()
				.createUserDao().getUsers()) {
			if (user.getLogin().equals(id)) {
				u = new User(user.getLogin(), user.getPasswd());
			}
		}

		players.put(u.getLogin(), u);
	}

	public SquareType getSquareType(int squareNumber) throws IllegalActionException {
		return getSquareIfExists(squareNumber).getSquareType();
	}
}
