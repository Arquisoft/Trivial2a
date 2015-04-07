package es.uniovi.asw.trivial.persistence;

import java.util.List;

import es.uniovi.asw.trivial.model.Question;

/**
 * Persistencia para Question
 * @author ivana
 *
 */

public interface QuestionDao {
	
	List<Question> getQuestions();
	void save(Question question);

}
