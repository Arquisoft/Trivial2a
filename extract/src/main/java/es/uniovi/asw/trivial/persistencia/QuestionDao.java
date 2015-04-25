package es.uniovi.asw.trivial.persistencia;

import es.uniovi.asw.trivial.model.Question;

public interface QuestionDao {
	void save(Question question);
	int getId(Question question);
}