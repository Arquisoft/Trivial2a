package es.uniovi.asw.trivial.bussines;

import java.util.List;

import es.uniovi.asw.trivial.bussines.exceptions.IllegalActionException;
import es.uniovi.asw.trivial.model.BoardOption;
import es.uniovi.asw.trivial.model.User;

public interface GameAPI {

	// Game configurations methods

	/**
	 * Method used to get the different boards available to play.
	 * 
	 * @return List of BoardOptions.
	 */
	List<BoardOption> getBoardOptions();

	/**
	 * Method used to select the board the game will me played on.
	 * 
	 * @param optionId
	 *            The id of the selected BoardOption
	 */
	void selectBoardOption(int optionId);

	/**
	 * Method used to get a list of the names of the existing users.
	 * 
	 * @return List of user names.
	 */
	List<String> getUserNameList();

	/**
	 * Method used to add a new user to the system.
	 * 
	 * @param userName
	 *            user name of the new user.
	 * @return
	 */
	void createUser(String userName);

	// Gameplay methods

	User getCurentUser();

	/**
	 * Method used to roll the dice. If the dice is already rolled it returns
	 * the same value again.
	 * 
	 * @return dice number
	 */
	int rollDice();

	// Sin implementar, llamaria al m�todo recorrido en profundidad el cual hay
	// que cambiar
	// tambien para que mire solo un numero de nodos

	/**
	 * Method used to move the active user to the square with the selected
	 * square number. It checks if the movement is allowed or not.
	 * 
	 * @param squareNumber
	 *            Target square number.
	 * @throws IllegalActionException
	 *             If the target square number is not valid or the action can't
	 *             be performed.
	 */
	void movePlayerTo(int squareNumber) throws IllegalActionException;

	/**
	 * Method used to get the list of square numbers available for the next
	 * move.
	 * 
	 * @return List of square numbers available for the next move.
	 */
	List<Integer> getMovements();

	User getWinner();

	boolean isFinished();

	void correctAnswer();

	void wrongAnswer();

	// Control methods
	boolean canThrowDice();

	boolean canMove();

}
