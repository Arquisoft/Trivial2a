package persistence.impl;

import models.Score;
import models.User;
import persistence.ScoreDao;

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
