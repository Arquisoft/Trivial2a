package es.uniovi.asw.trivial.factories;

import es.uniovi.asw.trivial.bussines.GameAPI;
import es.uniovi.asw.trivial.bussines.StatisticsLoader;
import es.uniovi.asw.trivial.bussines.impl.GameApiImpl;
import es.uniovi.asw.trivial.bussines.statistics.StatisticsLoaderImpl;

public class BusinessFactory {

	public static GameAPI getGameAPI() {
		return new GameApiImpl();
	}
	
	public static StatisticsLoader getStatisticsLoader(){
		return new StatisticsLoaderImpl();		
	}

}
