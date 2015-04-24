package es.uniovi.asw.trivial.persistencia;
public interface PersistenceServices {
	
	QuestionDao createQuestionDao();
	AnswerDao createAnswerDao();

}