package es.uniovi.asw.trivial.persistence;

public interface PersistenceServices {
	
	UserDao createUserDao();
	ScoreDao createScoreDao();
	StatisticDao createStatisticDao();
	QuestionDao createQuestionDao();

}
