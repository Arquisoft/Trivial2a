package bussines.fileLoaders;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import bussines.gameClasses.BoardOption;

/**
 * Class that loads a file containing different BoardOption object attributes
 * and stores them into a List<BoardOption> item.
 * 
 * @author Montero Hernández, José Antonio
 *
 */
public class BoardOptionsLoader {
	private List<BoardOption> result = new ArrayList<BoardOption>();

	public List<BoardOption> loadBoardOptions(String route) {
		String line;
		BufferedReader br = null;
		try {
			InputStream fis = new FileInputStream(route);
			InputStreamReader isr = new InputStreamReader(fis,
					Charset.forName("UTF-8"));
			br = new BufferedReader(isr);

			BoardOption boardOption = new BoardOption(0);

			while ((line = br.readLine()) != null) {
				line = line.trim();
				if (!line.startsWith("#") && line.length() != 0) {

					if (line.startsWith("-Start boadOption-")) {
						boardOption = new BoardOption(result.size());

					} else if (line.startsWith("-End boardOption-")) {
						if (boardOption.hasMissingRequiredFields())
							throw new RuntimeException(
									"Error in boardOptions configuration file.");
						result.add(boardOption);
						boardOption = new BoardOption(result.size());

					} else if (line.startsWith("Name:")) {
						boardOption.setName(line.substring(5).trim());

					} else if (line.startsWith("MiniatureImageUrl:")) {
						boardOption.setMiniatureImageUrl(line.substring(18)
								.trim());

					} else if (line.startsWith("BoardImageUrl:")) {
						boardOption.setBoardImageUrl(line.substring(14).trim());

					} else if (line.startsWith("BoardTextFileUrl:")) {
						boardOption.setBoardTextFileUrl(line.substring(17)
								.trim());

					} else if (line.startsWith("MinPlayers:")) {
						boardOption.setMinPlayers(Integer.parseInt(line
								.substring(11).trim()));

					} else if (line.startsWith("MaxPlayers:")) {
						boardOption.setMaxPlayers(Integer.parseInt(line
								.substring(11).trim()));
					}
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
}
