package persistence;

public interface PersistenceServices {
	
	UserDao createUserDao();
	ScoreDao createScoreDao();
	StatisticDao createStatisticDao();
	QuestionDao createQuestionDao();

}
