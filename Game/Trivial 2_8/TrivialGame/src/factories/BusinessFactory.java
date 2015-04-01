package factories;

import bussines.GameAPI;
import bussines.impl.GameApiImpl;

public class BusinessFactory {
	
	public static GameAPI getGameAPI()
	{
		return new GameApiImpl();
	}

}
