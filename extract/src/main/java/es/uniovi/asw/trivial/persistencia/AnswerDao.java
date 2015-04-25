package es.uniovi.asw.trivial.persistencia;


public interface AnswerDao {
	void saveCorrect(int id,String answer);
	void saveIncorrect(int id, String answer);
}
