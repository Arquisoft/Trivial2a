package es.uniovi.asw.trivial.bussines;

import java.util.List;

import es.uniovi.asw.trivial.bussines.exceptions.IllegalActionException;
import es.uniovi.asw.trivial.model.BoardOption;
import es.uniovi.asw.trivial.model.User;

public interface GameAPI {
	
	//Game configurations methods
	
	/**Method that gives you information about the different types of the panel
	 * 
	 * @return List<BoardOptions>
	 */
	List<BoardOption> getBoardOptions();
	void selectBoardOption(int optionId);
	List<User> getUserList();
	boolean createUser(User user);
	
	//Gameplay methods
	
	User getCurentUser();
	int rollDice();
	//Sin implementar, llamaria al m�todo recorrido en profundidad el cual hay que cambiar
	// tambien para que mire solo un numero de nodos
	
	void movePlayerTo(int squareNumber) throws IllegalActionException;
	List<Integer> getMovements();
	User getWinner();
	boolean isFinished();
	void correctAnswer();
	void wrongAnswer();
	
	//Control methods
	boolean canThrowDice();
	boolean canMove();

}
