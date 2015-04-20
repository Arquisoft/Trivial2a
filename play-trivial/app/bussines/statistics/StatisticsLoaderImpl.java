package bussines.statistics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import bussines.StatisticsLoader;
import factories.PersistenceFactory;
import model.User;
import persistence.PersistenceServices;

public class StatisticsLoaderImpl implements StatisticsLoader{

	@Override
	public int getGlobalCorrectAnswers() {
		
		PersistenceServices factory = PersistenceFactory.persistenceFactory();
		
		return factory.createStatisticDao().getRespuestasCorrectas();
		
	}

	@Override
	public int getGlobalWrongAnwers() {
		PersistenceServices factory = PersistenceFactory.persistenceFactory();
		
		return factory.createStatisticDao().getRespuestasIncorrectas();
	}

	@Override
	public int getGlobalCorrectSportsAnswers() {
		PersistenceServices factory = PersistenceFactory.persistenceFactory();
		
		int total = 0;
		
		for(User u: factory.createUserDao().getUsers()){
			Map<String, Integer[]> info = factory.createStatisticDao().getStatisticUser(u);
			
			total += info.get("SPORTS")[2];
		}
		
		return total;
	}

	@Override
	public int getGlobalCorrectGeographyAnswers() {
		PersistenceServices factory = PersistenceFactory.persistenceFactory();
		
		int total = 0;
		
		for(User u: factory.createUserDao().getUsers()){
			Map<String, Integer[]> info = factory.createStatisticDao().getStatisticUser(u);
			
			total += info.get("GEOGRAPHY")[2];
		}
		
		return total;
	}

	@Override
	public int getGlobalCorrectShowsAndEntertainmentAnswers() {
		PersistenceServices factory = PersistenceFactory.persistenceFactory();
		
		int total = 0;
		
		for(User u: factory.createUserDao().getUsers()){
			Map<String, Integer[]> info = factory.createStatisticDao().getStatisticUser(u);
			
			total += info.get("SHOWS_AND_ENTERTAINMENT")[2];
		}
		
		return total;
	}

	@Override
	public int getGlobalCorrectHistoryAnswers() {
		PersistenceServices factory = PersistenceFactory.persistenceFactory();
		
		int total = 0;
		
		for(User u: factory.createUserDao().getUsers()){
			Map<String, Integer[]> info = factory.createStatisticDao().getStatisticUser(u);
			
			total += info.get("HISTORY")[2];
		}
		
		return total;
	}

	@Override
	public int getGlobalCorrectArtAndLiteratureAnswers() {
		PersistenceServices factory = PersistenceFactory.persistenceFactory();
		
		int total = 0;
		
		for(User u: factory.createUserDao().getUsers()){
			Map<String, Integer[]> info = factory.createStatisticDao().getStatisticUser(u);
			
			total += info.get("ART_AND_LITERATURE")[2];
		}
		
		return total;
	}

	@Override
	public int getGlobalCorrectScienceAndTechnologyAnswers() {
		PersistenceServices factory = PersistenceFactory.persistenceFactory();
		
		int total = 0;
		
		for(User u: factory.createUserDao().getUsers()){
			Map<String, Integer[]> info = factory.createStatisticDao().getStatisticUser(u);
			
			total += info.get("SCIENCE_AND_TECHNOLOGY")[2];
		}
		
		return total;
	}

	@Override
	/**
	 *	SPORTS
		SHOWS_AND_ENTERTAINMENT
		SCIENCE_AND_TECHNOLOGY
		ART_AND_LITERATURE
		GEOGRAPHY
		HISTORY
	 */
	public List<Object[]> getInfoQuestionsByCategory(String category) {
		
		ArrayList<Object[]> datos = new ArrayList<Object[]>();
		
		PersistenceServices factory = PersistenceFactory.persistenceFactory();
		
		for(User u: factory.createUserDao().getUsers()){
			String nombre = u.getLogin();
			
			Map<String, Integer[]> info = factory.createStatisticDao().getStatisticUser(u);
			int totales = info.get(category)[1];
			int acertadas = info.get(category)[0];
			
			datos.add(new Object[]{nombre, totales, acertadas});
		}
		
		return datos;
	}
	
	

}
