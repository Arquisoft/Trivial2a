package conf;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Conf {

	public static List<String> getNodes(final String fichero) {
		FileReader f = null;
		BufferedReader b = null;
		String ruta = "src/" + fichero;

		List<String> nodes = new ArrayList<>();
		try {

			f = new FileReader(ruta);
			b = new BufferedReader(f);

			String linea = b.readLine();
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
		String ruta = "src/" + fichero;

		List<String> edges = new ArrayList<>();
		try {

			f = new FileReader(ruta);
			b = new BufferedReader(f);

			String linea = b.readLine();
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
}
