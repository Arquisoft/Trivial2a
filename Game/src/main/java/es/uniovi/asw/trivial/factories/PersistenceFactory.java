package es.uniovi.asw.trivial.factories;

import es.uniovi.asw.trivial.persistence.PersistFactory;
import es.uniovi.asw.trivial.persistence.impl.SimplePersistenceFactory;

public class PersistenceFactory {
	
	public static PersistFactory persistenceFactory()
	{
		return new SimplePersistenceFactory();
	}

}
