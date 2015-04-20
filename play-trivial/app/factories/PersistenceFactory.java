package factories;

import persistence.PersistenceServices;
import persistence.impl.SimplePersistenceFactory;

public class PersistenceFactory {
	
	public static PersistenceServices persistenceFactory()
	{
		return new SimplePersistenceFactory();
	}

}
