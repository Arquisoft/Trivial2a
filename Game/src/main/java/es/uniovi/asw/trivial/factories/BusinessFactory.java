package es.uniovi.asw.trivial.factories;

import es.uniovi.asw.trivial.bussines.GameAPI;
import es.uniovi.asw.trivial.bussines.impl.GameApiImpl;

public class BusinessFactory {
	
	public static GameAPI getGameAPI()
	{
		return new GameApiImpl();
	}

}
