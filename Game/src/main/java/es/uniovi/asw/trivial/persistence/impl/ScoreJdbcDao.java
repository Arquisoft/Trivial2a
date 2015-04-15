package es.uniovi.asw.trivial.persistence.impl;

import es.uniovi.asw.trivial.model.Score;
import es.uniovi.asw.trivial.model.User;
import es.uniovi.asw.trivial.persistence.ScoreDao;

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
