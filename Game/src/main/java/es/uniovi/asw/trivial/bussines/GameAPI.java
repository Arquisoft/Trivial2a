package es.uniovi.asw.trivial.bussines;

import java.util.List;
import java.util.Set;

import es.uniovi.asw.trivial.bussines.exceptions.IllegalActionException;
import es.uniovi.asw.trivial.model.BoardOption;
import es.uniovi.asw.trivial.model.Question;
import es.uniovi.asw.trivial.model.Score;

public interface GameAPI {

	// ##########################################################
	// # Game-play methods
	// ##########################################################

	/**
	 * Method that starts a new game.
	 * 
	 * @param userNames
	 *            List of user names of the players for the new game.
	 * @param boardOption
	 *            Board selected for this game.
	 * @throws IllegalActionException
	 *             Thrown if there is a game already started.
	 */
	void startGame(List<String> userNames, BoardOption boardOption)
			throws IllegalActionException;

	/**
	 * Method used to query the square number of the square a given player is
	 * at.
	 * 
	 * @param userName
	 *            user name of the player that needs to be found.
	 * @return square number of the current square the player is located at.
	 * @throws IllegalActionException
	 *             Thrown if the user is not a player of the current game or
	 *             there is no game in progress.
	 */
	int getPlayerLocation(String userName) throws IllegalActionException;

	/**
	 * Method used to obtain the current score of a given player.
	 * 
	 * @param userName
	 *            user name of the player.
	 * @return Score of the player.
	 * @throws IllegalActionException
	 *             Thrown if there is no player with the requested name or there
	 *             is no game in progress.
	 */
	Score getPlayerScore(String userName) throws IllegalActionException;

	/**
	 * Method used to roll the dice. If the dice is already rolled it returns
	 * the same value again.
	 * 
	 * @return dice number.
	 * @throws IllegalActionException
	 *             Thrown if there is no game running.
	 */
	int rollDice() throws IllegalActionException;

	/**
	 * Method used to move the active player to the square with the selected
	 * square number. It checks if the movement is allowed or not.
	 * 
	 * @param squareNumber
	 *            Target square number.
	 * @throws IllegalActionException
	 *             If the target square number is not valid, the action can't be
	 *             performed or there is no game in progress.
	 */
	void movePlayerTo(int squareNumber) throws IllegalActionException;

	/**
	 * Method used to get the list of square numbers available for the next
	 * move.
	 * 
	 * @param userName
	 *            User name of the player which available moves we want to know.
	 * @param squareNumber
	 *            Square number of the square the player is at.
	 * @return List of square numbers available for the next move.
	 * @throws IllegalActionException
	 *             Thrown if userName is not the active player, he is not at the
	 *             given square, the dice still has to be rolled or there is no
	 *             game in progress.
	 */
	Set<Integer> getMovements(int userName, int squareNumber)
			throws IllegalActionException;

	/**
	 * Method used to get the question corresponding to the square the player is
	 * at.
	 * 
	 * @param userName
	 *            User name of the player we want to get a question for.
	 * @param squareNumber
	 *            Square number of the square the player is at.
	 * @return Question required.
	 * @throws IllegalActionException
	 *             Thrown if userName is not the active player, he is not at the
	 *             given location or there is no game in progress.
	 */
	Question getQuestion(String userName, int squareNumber)
			throws IllegalActionException;

	/**
	 * Method used to send the player answer and know if it is the right one or
	 * not. It checks if the question, user name of the player answering the
	 * question and the square number the player is at are correct. If they
	 * aren't, it throws an exception.
	 * 
	 * @param questionId
	 *            Id of the question to answer.
	 * @param answer
	 *            Answer that has to be checked.
	 * @param userName
	 *            User name of the player that is answering the question.
	 * @param squareNumber
	 *            Location of the player.
	 * @return True if the answer is correct, false otherwise.
	 * @throws IllegalActionException
	 *             Exception thrown if the question if the player can't answer a
	 *             question, it's not the proper player, the location is wrong ,
	 *             the question answered is not the correct one or there is no
	 *             game in progress.
	 */
	boolean isAnswerCorrect(int questionId, String answer, String userName,
			int squareNumber) throws IllegalActionException;

	// ##########################################################
	// # Game Query Methods
	// ##########################################################

	/**
	 * Method used to obtain the name of the winner. If there is no winner it
	 * returns null. To win a game, a player has to score in every single
	 * category of the game and go back to the starting square.
	 * 
	 * @return User name of the winner. Null if there is no winner.
	 */
	String getWinner();

	/**
	 * Method to query is the game is still in progress or someone already win.
	 * 
	 * @return Returns true if the game already ended, returns false otherwise.
	 * @throws IllegalActionException
	 *             Thrown if there is no game in progress.
	 */
	boolean isFinished() throws IllegalActionException;

	/**
	 * Method used to query the user name of the active user.
	 * 
	 * @return Active user's name.
	 * @throws IllegalActionException
	 *             Thrown if there is no game in progress.
	 */
	String getActivePlayer() throws IllegalActionException;

	/**
	 * Method used to get the different boards available to play.
	 * 
	 * @return List of BoardOptions.
	 */
	List<BoardOption> getBoardOptions();

	/**
	 * Method used to get a list of the names of the existing users.
	 * 
	 * @return List of user names.
	 */
	Set<String> getUserNameList();

	/**
	 * Method used to add a new user to the system.
	 * 
	 * @param userName
	 *            user name of the new user.
	 */
	void createUser(String userName);

	/**
	 * Method used to get the relation of the square numbers and the
	 * corresponding (x,y) coordinates of the point of that square in which
	 * players and other tokens should be drawn.
	 * 
	 * @return String[][] Relation of [squareNumber, [xPoint, yPoint]]
	 * @throws IllegalActionException
	 *             Thrown if there is no board loaded.
	 */
	String[] getSquares() throws IllegalActionException;

	// ##########################################################
	// # Data Query Methods
	// ##########################################################

}
