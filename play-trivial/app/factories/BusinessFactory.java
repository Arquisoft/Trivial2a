package factories;

import bussines.GameAPI;
import bussines.StatisticsLoader;
import bussines.impl.GameApiImpl;
import bussines.statistics.StatisticsLoaderImpl;

public class BusinessFactory {

	public static GameAPI getGameAPI() {
		return new GameApiImpl();
	}
	
	public static StatisticsLoader getStatisticsLoader(){
		return new StatisticsLoaderImpl();		
	}

}
