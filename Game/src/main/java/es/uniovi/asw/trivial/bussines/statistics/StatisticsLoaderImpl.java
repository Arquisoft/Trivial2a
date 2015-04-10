package es.uniovi.asw.trivial.bussines.statistics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import es.uniovi.asw.trivial.bussines.StatisticsLoader;
import es.uniovi.asw.trivial.factories.PersistenceFactory;
import es.uniovi.asw.trivial.model.User;
import es.uniovi.asw.trivial.persistence.PersistFactory;

public class StatisticsLoaderImpl implements StatisticsLoader{

	@Override
	public int getGlobalCorrectAnswers() {
		
		PersistFactory factory = PersistenceFactory.persistenceFactory();
		
		return factory.createStatisticDao().getRespuestasCorrectas();
		
	}

	@Override
	public int getGlobalWrongAnwers() {
		PersistFactory factory = PersistenceFactory.persistenceFactory();
		
		return factory.createStatisticDao().getRespuestasIncorrectas();
	}

	@Override
	public int getGlobalCorrectSportsAnswers() {
		PersistFactory factory = PersistenceFactory.persistenceFactory();
		
		int total = 0;
		
		for(User u: factory.createUserDao().getUsers()){
			Map<String, Integer[]> info = factory.createStatisticDao().getStatisticUser(u);
			
			total += info.get("SPORTS")[2];
		}
		
		return total;
	}

	@Override
	public int getGlobalCorrectGeographyAnswers() {
		PersistFactory factory = PersistenceFactory.persistenceFactory();
		
		int total = 0;
		
		for(User u: factory.createUserDao().getUsers()){
			Map<String, Integer[]> info = factory.createStatisticDao().getStatisticUser(u);
			
			total += info.get("GEOGRAPHY")[2];
		}
		
		return total;
	}

	@Override
	public int getGlobalCorrectShowsAndEntertainmentAnswers() {
		PersistFactory factory = PersistenceFactory.persistenceFactory();
		
		int total = 0;
		
		for(User u: factory.createUserDao().getUsers()){
			Map<String, Integer[]> info = factory.createStatisticDao().getStatisticUser(u);
			
			total += info.get("SHOWS_AND_ENTERTAINMENT")[2];
		}
		
		return total;
	}

	@Override
	public int getGlobalCorrectHistoryAnswers() {
		PersistFactory factory = PersistenceFactory.persistenceFactory();
		
		int total = 0;
		
		for(User u: factory.createUserDao().getUsers()){
			Map<String, Integer[]> info = factory.createStatisticDao().getStatisticUser(u);
			
			total += info.get("HISTORY")[2];
		}
		
		return total;
	}

	@Override
	public int getGlobalCorrectArtAndLiteratureAnswers() {
		PersistFactory factory = PersistenceFactory.persistenceFactory();
		
		int total = 0;
		
		for(User u: factory.createUserDao().getUsers()){
			Map<String, Integer[]> info = factory.createStatisticDao().getStatisticUser(u);
			
			total += info.get("ART_AND_LITERATURE")[2];
		}
		
		return total;
	}

	@Override
	public int getGlobalCorrectScienceAndTechnologyAnswers() {
		PersistFactory factory = PersistenceFactory.persistenceFactory();
		
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
		
		PersistFactory factory = PersistenceFactory.persistenceFactory();
		
		for(User u: factory.createUserDao().getUsers()){
			String nombre = u.getLogin();
			
			Map<String, Integer[]> info = factory.createStatisticDao().getStatisticUser(u);
			
<<<<<<< HEAD
			int totales = info.get(category)[1];
			int acertadas = info.get(category)[0];
=======
			int totales = info.get(category)[0];
			int acertadas = info.get(category)[1];
>>>>>>> 13c1d20b8ca89c66898be3d8b2758c72474d7f96
			
			datos.add(new Object[]{nombre, totales, acertadas});
		}
		
		return datos;
	}
	
	

}
