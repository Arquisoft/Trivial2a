package conf;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Conf {

	public static List<String> getNodes(final String fichero) {

		List<String> nodes = new ArrayList<>();
		

		String file = new File(fichero).toString();
		String[] lineas = file.split("\r\n");

		for (String linea : lineas) {
			linea.trim();
			if (linea.startsWith("ID:"))
			{
				 nodes.add(linea.substring(2));
			}
		}
		
		return nodes;
	}
	
	public static List<String> getEdges(final String fichero) {

		List<String> edges = new ArrayList<>();
		

		String file = new File(fichero).toString();
		String[] lineas = file.split("\r\n");

		for (String linea : lineas) {
			linea.trim();
			if (linea.startsWith("ED:"))
			{
				 edges.add(linea.substring(2));
			}
		}
		
		return edges;
	}
}
