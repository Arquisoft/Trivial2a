package es.uniovi.asw.trivial.conf;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Conf {

	public static List<String> getNodes(final String fichero) {
		FileReader f = null;
		BufferedReader b = null;
		String ruta = System.getProperty("user.dir") + fichero;

		List<String> nodes = new ArrayList<String>();
		try {

			f = new FileReader(ruta);
			b = new BufferedReader(f);

			String linea = b.readLine().trim();
			while (linea  != null) {

				if (linea.startsWith("ID:")) {
					nodes.add(linea.substring(3));
				}
				linea = b.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != f) {
					f.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return nodes;
	}

	public static List<String> getEdges(final String fichero) {

		FileReader f = null;
		BufferedReader b = null;
		String ruta =  System.getProperty("user.dir") + fichero;

		List<String> edges = new ArrayList<String>();
		try {

			f = new FileReader(ruta);
			b = new BufferedReader(f);

			String linea = b.readLine().trim();
			while (linea != null) {

				if (linea.startsWith("ED:")) {
					edges.add(linea.substring(3));
				}
				
				linea = b.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != f) {
					f.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return edges;
	}
	
private static final String CONF_FILE = "configuration.properties";
	
	private static Conf instance;
	private Properties properties;

	private Conf() {
		properties = new Properties();
		try {
			properties.load(Conf.class.getClassLoader().getResourceAsStream(CONF_FILE));
		} catch (IOException e) {
			throw new RuntimeException("Propeties file can not be loaded", e);
		}
	}
	
	public static String get(String key) {
		return getInstance().getProperty( key );
	}

	private String getProperty(String key) {
		String value = properties.getProperty(key);
		if (value == null) {
			throw new RuntimeException("Property not found in config file");
		}
		return value;
	}

	private static Conf getInstance() {
		if (instance == null) {
			instance = new Conf();
		}
		return instance;
	}
}
