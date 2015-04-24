package es.uniovi.asw.trivial.persistencia.impl;

import es.uniovi.asw.trivial.persistencia.AnswerDao;
import es.uniovi.asw.trivial.persistencia.PersistenceServices;
import es.uniovi.asw.trivial.persistencia.QuestionDao;

public class SimplePersistenceFactory implements PersistenceServices {	

	@Override
	public QuestionDao createQuestionDao() {
		return new QuestionJdbcDao();
	}

	@Override
	public AnswerDao createAnswerDao() {
		return new AnswerJdbcDao();
	}

}