package bussines.fileLoaders;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import models.Category;
import models.Square;
import models.SquareType;

/**
 * 
 * @author Montero Hernández, José Antonio
 *
 */
public class GraphFileLoader {
	private Map<Integer, Square> result = new HashMap<Integer, Square>();

	public Map<Integer, Square> loadGrapthFile(final String route) {

		String line;
		BufferedReader br = null;
		try {
			InputStream fis = new FileInputStream(route);
			InputStreamReader isr = new InputStreamReader(fis,
					Charset.forName("UTF-8"));
			br = new BufferedReader(isr);

			while ((line = br.readLine()) != null) {
				line = line.trim();
				if (line.startsWith("ID:")) {
					putUnsplittedNode(line);
				} else if (line.startsWith("ED:")) {
					connectUnsplittedNodes(line);
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (br != null)
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}

		return result;
	}

	private void putUnsplittedNode(String line) {

		String[] subStrings = line.substring(3).split("-");

		int squareNumber = Integer.parseInt(subStrings[0].trim());
		String categoryString = subStrings[1].trim();
		Category category = null;
		if (!categoryString.isEmpty()) {
			category = Category.valueOf(categoryString);
		}
		SquareType squareType = SquareType.valueOf(subStrings[2].trim());
		Point point= new Point(Integer.valueOf(subStrings[3].trim()),Integer.valueOf(subStrings[4].trim()));
		result.put(squareNumber, new Square(squareNumber, category, squareType,point));
	}

	private void connectUnsplittedNodes(String line) {
		String[] subStrings = line.substring(3).split("\\|");

		Square square1 = result.get(Integer.parseInt(subStrings[0].trim()));
		Square square2 = result.get(Integer.parseInt(subStrings[1].trim()));

		square1.connect(square2);
		square2.connect(square1);
	}
}
