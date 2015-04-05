package es.uniovi.asw.trivial.persistence;

public interface PersistenceFactory {
	UserDao createUserDao();
	ScoreDao createScoreDao();
	StatisticDao createStatisticDao();

}
