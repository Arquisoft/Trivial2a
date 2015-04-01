package persistence.impl;

import model.Statistic;
import model.User;
import persistence.UserDao;

public class UserJdbcDao implements UserDao {

	@Override
	public boolean isValidLogin() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void save(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String login) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Statistic getStatistic(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
