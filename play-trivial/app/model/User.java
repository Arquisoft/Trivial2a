package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model.Finder;
import bussines.gameClasses.Score;
import bussines.gameClasses.Statistic;

/** Clase "User" del modelo.
 * @author Gonz�lez Fernandez Cristian y Vel�zquez Vico �lvaro
 * @version 1 - Last changes: -
 */
@Entity
public class User {
	
	@Id
	private String login;
	@Required
	private String passwd;
	@Transient
	private Score score;
	@Transient
	private Statistic statistics;
	@Transient
	private int location;
	
	private static Finder<String, User> finder = new Finder<String, User>(String.class, User.class);
	
	
	public User(String login, String passwd) {
		super();
		this.login = login;
		this.passwd = passwd;
		
		score = new Score();
		statistics = new Statistic();
	}
	
	public static User get(String login){
		return finder.byId(login);
	}
	
	public static User validate(String login, String passwd){
		User user = finder.byId(login);
		
		if (user != null && user.passwd.equals(passwd))
			return user;
		else
			return null;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
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
	
}
