package es.uniovi.asw.trivial.persistence;

import es.uniovi.asw.trivial.model.Score;
import es.uniovi.asw.trivial.model.User;

public interface ScoreDao {
	
	Score getMaxScore();
	
	User getUserMaxScore();
}
