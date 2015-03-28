package conf;

import java.io.File;



public class Conf {

	@SuppressWarnings("unused")
	public static String getFile(final String fichero) 
	{
		 return  new File(fichero).toString();
	}
}
