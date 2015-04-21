package persistence;

import model.User;
import bussines.gameClasses.Score;

public interface ScoreDao {
	
	Score getMaxScore();
	
	User getUserMaxScore();
}
