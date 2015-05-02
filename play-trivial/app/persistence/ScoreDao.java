package persistence;

import models.Score;
import models.User;

public interface ScoreDao {
	
	Score getMaxScore();
	
	User getUserMaxScore();
}
