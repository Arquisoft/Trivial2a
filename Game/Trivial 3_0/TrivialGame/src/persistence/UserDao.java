package persistence;

import model.Statistic;
import model.User;

public interface UserDao {
	
	boolean isValidLogin();
	
	void save (User user);
	
	void update(User user);
	
	void delete(User user);
	
	void delete(String login);
	
	Statistic getStatistic(User user);

}
