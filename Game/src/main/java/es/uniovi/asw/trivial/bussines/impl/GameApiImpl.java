package es.uniovi.asw.trivial.bussines.impl;

import java.util.List;
import java.util.Set;

import es.uniovi.asw.trivial.bussines.GameAPI;
import es.uniovi.asw.trivial.bussines.exceptions.IllegalActionException;
import es.uniovi.asw.trivial.bussines.gameClasses.Board;
import es.uniovi.asw.trivial.bussines.gameClasses.BoardOptionsFactory;
import es.uniovi.asw.trivial.bussines.gameClasses.Dice;
import es.uniovi.asw.trivial.factories.PersistenceFactory;
import es.uniovi.asw.trivial.model.BoardOption;
import es.uniovi.asw.trivial.model.Category;
import es.uniovi.asw.trivial.model.Question;
import es.uniovi.asw.trivial.model.Score;
import es.uniovi.asw.trivial.model.SquareType;
import es.uniovi.asw.trivial.model.User;

public class GameApiImpl implements GameAPI {
	
	public static final int MAX_PLAYERS = 6;
	
	//Control the actual state game
	private StateGame state = new StateGame();
	private Board board;
	private List<User> users;

	@Override
	public List<BoardOption> getBoardOptions() {
		return BoardOptionsFactory.getBoardOptions();
	}
	
	@Override
	public void selectBoardOption(int boardOptionId) {
		board = new Board(BoardOptionsFactory.getBoardOption(boardOptionId));
	}

	@Override
	public boolean createUser(User user) {
		if(users.size()==MAX_PLAYERS)
		{
			return false;
		}
		else
		{
			users.add(user);
			user.setPosition(board.getStartPosition());
			return true;
		}
		
	}

	@Override
	public User getCurentUser() {
		return users.get(state.getCurrentPlayer());
	}

	@Override
	public int rollDice() {
		state.setDiceThrown(true);
		return Dice.throwingDice();
	}

	@Override
	public List<Integer> getMovements() {
		if(!canMove())
			return null;
		
		int origin = getCurentUser().getPosition();
		return board.getSquaresAtDistance(origin, state.getDiceNumber());
	}

	@Override
	public void movePlayerTo(int squareNumber) throws IllegalActionException {
		//Comprobar primero que la casilla est� dentro de la lista que devolveria el metodo getMoves.
		if(!getMovements().contains(squareNumber))
			throw new IllegalActionException("Invalid movement requested.");
		
		getCurentUser().setPosition(squareNumber);
		
		//Si la casilla es de tirar otra vez
		if(board.getSquareCategory(squareNumber).equals(SquareType.DICE))
		{
			state.resetTurn();
		}
		//Si no, sacamos de la base de datos la pregunta
		else
		{
			Category cat = board.getSquareCategory(squareNumber);
			//Actualizamos el estado con la pregunta
			
			
		}
	}

	@Override
	public boolean canThrowDice() {
		return getCurentUser()!=null && !state.isDiceThrown();
	}

	@Override
	public boolean canMove() {
		return getCurentUser()!=null && state.isDiceThrown() && state.getQuestion()==null;
	}

	@Override
	public User getWinner() {
		return users.get(state.getWinner());
	}

	@Override
	public boolean isFinished() {
		if(getWinner()!=null) return true;
		else return false;
	}

	@Override
	public void correctAnswer() {
		// TODO Auto-generated method stub
	}

	@Override
	public void wrongAnswer() {
		// TODO Auto-generated method stub
	}

	@Override
	public void startGame(List<String> userNames, BoardOption boardOption)
			throws IllegalActionException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getPlayerLocation(String userName) throws IllegalActionException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Score getPlayerScore(String userName) throws IllegalActionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Integer> getMovements(int userName, int squareNumber)
			throws IllegalActionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Question getQuestion(String userName, int squareNumber)
			throws IllegalActionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAnswerCorrect(int questionId, String answer,
			String userName, int squareNumber) throws IllegalActionException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getWinner() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getActivePlayer() throws IllegalActionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> getUserNameList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createUser(String userName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String[] getSquares() throws IllegalActionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getWinner() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getUsers() {
		return PersistenceFactory.persistenceFactory().createUserDao().getUsers();
	}

	
	
}
