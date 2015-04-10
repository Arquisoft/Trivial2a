package es.uniovi.asw.trivial.steps;

import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;
import es.uniovi.asw.trivial.bussines.gameClasses.Board;
import es.uniovi.asw.trivial.model.BoardOption;
import static org.junit.Assert.assertEquals;

public class GameSteps {

	private Board tablero;
	private BoardOption boardOption;
	

	@Dado("^las opciones de un tablero$")
	public void las_opciones_de_un_tablero() throws Throwable {
		boardOption = new BoardOption(1);
		boardOption.setMiniatureImageUrl("");
		boardOption.setBoardImageUrl("");
		boardOption.setBoardTextFileUrl(System.getProperty("user.dir")
				+ "/resources/boards/board1/board1Graph.txt");
		boardOption.setMinPlayers(2);
		boardOption.setMaxPlayers(6);
	}

	@Cuando("^le establezco su nombre y creo un tablero \"(.*?)\"$")
	public void le_establezco_su_url(String name) throws Throwable {
		boardOption.setName(name);
		tablero = new Board(boardOption);
	}

	@Entonces("^el nombre del tablero es \"(.*?)\"$")
	public void el_nombre_del_tablero_es(String name) throws Throwable {
		assertEquals(name, tablero.getName());
	}

}
