package persistence.impl;

import persistence.PersistenceFactory;
import persistence.ScoreDao;
import persistence.StatisticDao;
import persistence.UserDao;

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
