package es.uniovi.asw.trivial.bussines.gameClasses;

import java.util.List;
import es.uniovi.asw.trivial.bussines.fileLoaders.BoardOptionsLoader;
import es.uniovi.asw.trivial.model.BoardOption;

/**
 * Class that grant access and stores the different BoardOption items.
 * 
 * @author Montero Hernández, José Antonio
 *
 */
public class BoardOptionsFactory {

	public static final String BOARDS_FOLDER = System.getProperty("user.dir")
			+ "/resources/boards/";
	public static final String BOARDOPTIONS_FILE = "boardOptions.txt";

	private static List<BoardOption> boardOptions = new BoardOptionsLoader()
			.loadBoardOptions(BOARDS_FOLDER + BOARDOPTIONS_FILE);

	public static List<BoardOption> getBoardOptions() {
		return boardOptions;
	}
	
	public static BoardOption getBoardOption(int boardOptionId) {
		return boardOptions.get(boardOptionId);
	}
}
