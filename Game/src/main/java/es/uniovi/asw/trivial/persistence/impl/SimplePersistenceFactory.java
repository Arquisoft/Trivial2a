package es.uniovi.asw.trivial.persistence.impl;

import es.uniovi.asw.trivial.persistence.PersistenceFactory;
import es.uniovi.asw.trivial.persistence.ScoreDao;
import es.uniovi.asw.trivial.persistence.StatisticDao;
import es.uniovi.asw.trivial.persistence.UserDao;

public class SimplePersistenceFactory implements PersistenceFactory{

	@Override
	public UserDao createUserDao() {		
		return new UserJdbcDao();
	}

	@Override
	public ScoreDao createScoreDao() {
		return new ScoreJdbcDao();
	}

	@Override
	public StatisticDao createStatisticDao() {
		return new StatisticJdbcDao();
	}

}
