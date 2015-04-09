package es.uniovi.asw.trivial.persistence;

public interface PersistFactory {
	
	UserDao createUserDao();
	ScoreDao createScoreDao();
	StatisticDao createStatisticDao();
	QuestionDao createQuestionDao();

}
