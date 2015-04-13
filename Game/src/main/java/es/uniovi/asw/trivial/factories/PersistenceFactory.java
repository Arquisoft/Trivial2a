package es.uniovi.asw.trivial.factories;

import es.uniovi.asw.trivial.persistence.PersistenceServices;
import es.uniovi.asw.trivial.persistence.impl.SimplePersistenceFactory;

public class PersistenceFactory {
	
	public static PersistenceServices persistenceFactory()
	{
		return new SimplePersistenceFactory();
	}

}
