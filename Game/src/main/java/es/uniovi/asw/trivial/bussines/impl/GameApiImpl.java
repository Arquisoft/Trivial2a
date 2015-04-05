package es.uniovi.asw.trivial.bussines.impl;

import java.util.Arrays;
import java.util.List;

import es.uniovi.asw.trivial.bussines.Dice;
import es.uniovi.asw.trivial.bussines.GameAPI;
import es.uniovi.asw.trivial.model.BoardOptions;
import es.uniovi.asw.trivial.model.Category;
import es.uniovi.asw.trivial.model.Panel;
import es.uniovi.asw.trivial.model.Square;
import es.uniovi.asw.trivial.model.User;

public class GameApiImpl implements GameAPI {
	
	public static final int MAX_PLAYERS = 6;
	
	//Control the actual state game
	private StateGame state = new StateGame();
	private Panel panel;
	private List<User> users;

	@Override
	public List<BoardOptions> getBoardOptions() {
		List<BoardOptions> options = Arrays.asList(BoardOptions.values());
		return options;
	}

	@Override
	public void selectBoardOption(BoardOptions option) {
		panel = new Panel(option);
		
	}

	@Override
	public List<User> getUserList() {
		return users;
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
			user.setSquare(panel.getSquare(0));
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
	public List<Square> getMovements() {
		
		if(!canMove()) return null;
		else
		{
			Square origin = getCurentUser().getSquare();
			return panel.getNodesToVisit(origin, state.getDiceNumber());
		}
	}

	@Override
	public boolean movePlayer(Square square) {
		//Comprobar primero que la casilla est� dentro de la lista que devolveria el metodo getMoves.
		if(!getMovements().contains(square))
		{
			return false;
		}
		getCurentUser().setSquare(square);
		
		//Si la casilla es de tirar otra vez
		if(square.getCategory().equals(Category.DICE))
		{
			state.resetTurn();
		}
		//Si no, sacamos de la base de datos la pregunta
		else
		{
//			Category cat = getCurentUser().getSquare().getCategory();
			//Actualizamos el estado con la pregunta
			
			
		}
		return true;
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

	
	
}
