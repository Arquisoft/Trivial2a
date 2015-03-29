package bussines;

import java.util.List;

import model.BoardOptions;
import model.Square;
import model.User;

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
	//Sin implementar, llamaria al método recorrido en profundidad el cual hay que cambiar
	// tambien para que mire solo un numero de nodos
	List<Square> getMoves();
	boolean movePlayer(Square square);
	
	
	//Control methods
	boolean canThrowDice();
	boolean canMove();

}
