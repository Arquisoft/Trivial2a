package es.uniovi.asw.trivial.model;

/** Clase "User" del modelo.
 * @author Gonz�lez Fernandez Cristian y Vel�zquez Vico �lvaro
 * @version 1 - Last changes: -
 */
public class User {
	
	private String login;
	private Score score;
	private Statistic statistics;
	private int position;
	

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
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
