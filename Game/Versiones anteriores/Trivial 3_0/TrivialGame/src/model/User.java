package model;

/** Clase "User" del modelo.
 * @author González Fernandez Cristian y Velázquez Vico álvaro
 * @version 1 - Last changes: -
 */
public class User {
	
	private String login;
	private Score score;
	private Statistic statistics;
	private Square square;
	
	
	public Square getSquare() {
		return square;
	}

	public void setSquare(Square square) {
		this.square = square;
	}

	public User(String login){
		this.login = login;
		
		score = new Score();
		statistics = new Statistic();
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Score getScore() {
		return score;
	}

	public Statistic getStatistics() {
		return statistics;
	}

	public void setStatistics(Statistic statistics) {
		this.statistics = statistics;
	}
	
}
