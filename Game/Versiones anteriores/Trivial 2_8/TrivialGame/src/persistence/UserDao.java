package persistence;

import java.util.List;

import model.Statistic;
import model.User;

public interface UserDao {
	
	boolean isValidLogin();
	
	void save (User user);
	
	void update(User user);
	
	void delete(User user);
	
	void delete(String login);
	
	Statistic getStatistic(User user);
	
	List<User> getUsers();

}
