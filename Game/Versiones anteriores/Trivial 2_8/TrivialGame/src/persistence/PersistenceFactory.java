package persistence;

public interface PersistenceFactory {
	UserDao createUserDao();
	ScoreDao createScoreDao();
	StatisticDao createStatisticDao();

}
