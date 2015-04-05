package es.uniovi.asw.trivial.bussines;

import java.util.List;

import es.uniovi.asw.trivial.model.BoardOptions;
import es.uniovi.asw.trivial.model.Square;
import es.uniovi.asw.trivial.model.User;

public interface GameAPI {
	
	//Game configurations methods
	
	/**Method that gives you information about the different types of the panel
	 * 
	 * @return List<BoardOptions>
	 */
	List<BoardOptions> getBoardOptions();
	
	void selectBoardOption(BoardOptions option);
	List<User> getUserList();
	boolean createUser(User user);
	
	//Gameplay methods
	
	User getCurentUser();
	int rollDice();
	//Sin implementar, llamaria al m�todo recorrido en profundidad el cual hay que cambiar
	// tambien para que mire solo un numero de nodos
	
	boolean movePlayer(Square square);
	List<Square> getMovements();
	User getWinner();
	boolean isFinished();
	void correctAnswer();
	void wrongAnswer();
	
	//Control methods
	boolean canThrowDice();
	boolean canMove();

}
