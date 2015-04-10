package es.uniovi.asw.trivial.bussines.impl;

import java.util.List;

import es.uniovi.asw.trivial.bussines.GameAPI;
import es.uniovi.asw.trivial.bussines.exceptions.IllegalActionException;
import es.uniovi.asw.trivial.bussines.gameClasses.Board;
import es.uniovi.asw.trivial.bussines.gameClasses.BoardOptionsFactory;
import es.uniovi.asw.trivial.bussines.gameClasses.Dice;
import es.uniovi.asw.trivial.model.BoardOption;
import es.uniovi.asw.trivial.model.Category;
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
	public String getWinner() {
		return users.get(state.getWinner()).getLogin();
	}

	@Override
	public boolean isFinished() {
		if(getWinner()!=null) return true;
		else return false;
	}

	
	
}
