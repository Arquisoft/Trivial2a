package persistence.impl;


import persistence.PersistenceServices;
import persistence.QuestionDao;
import persistence.ScoreDao;
import persistence.StatisticDao;
import persistence.UserDao;

public class SimplePersistenceFactory implements PersistenceServices {

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

	@Override
	public QuestionDao createQuestionDao() {
		return new QuestionJdbcDao();
	}

}
