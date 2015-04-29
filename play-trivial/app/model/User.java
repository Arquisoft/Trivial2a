package model;

import bussines.GameAPI;
import bussines.impl.GameApiImpl;

/** Clase "User" del modelo.
 * @author Gonz�lez Fernandez Cristian y Vel�zquez Vico �lvaro
 * @version 1 - Last changes: -
 */
public class User {
	
	private String login;
	private String passwd;
	private Score score;
	private Statistic statistics;
	private int location;
	
	public User(String login, String passwd){
		this.login = login;
		this.passwd = passwd;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
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
	
	public boolean canWin() {
		return score.hasAllThePieces();
	}

	public static Object authenticate(String id, String password) {
		GameAPI api = new GameApiImpl();
		boolean existe = api.existUser(id, password);
		System.out.println("Existe usuario = " + existe);
		if (existe == false)
			return null;
		
		return true;
	}
	
	
	
}
