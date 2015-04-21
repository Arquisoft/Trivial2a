package persistence.impl;

import model.User;
import persistence.ScoreDao;
import bussines.gameClasses.Score;

public class ScoreJdbcDao implements ScoreDao {

	@Override
	public Score getMaxScore() {
		return null;
	}

	@Override
	public User getUserMaxScore() {
		return null;
	}

}
