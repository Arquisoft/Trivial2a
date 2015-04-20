package persistence;

import model.Score;
import model.User;

public interface ScoreDao {
	
	Score getMaxScore();
	
	User getUserMaxScore();
}
