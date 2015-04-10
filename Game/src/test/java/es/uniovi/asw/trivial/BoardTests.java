package es.uniovi.asw.trivial;

import static org.assertj.core.api.Assertions.assertThat;


import org.junit.Before;
import org.junit.Test;

import es.uniovi.asw.trivial.bussines.gameClasses.Board;
import es.uniovi.asw.trivial.model.BoardOption;

public class BoardTests {

	private Board board;
	private BoardOption boardOption;

	@Before
	public void setUp() {
		boardOption = new BoardOption(1);
		boardOption.setName("");
		boardOption.setMiniatureImageUrl("");
		boardOption.setBoardImageUrl("");
		boardOption.setBoardTextFileUrl(System.getProperty("user.dir")
				+ "/resources/boards/board1/board1Graph.txt");
		boardOption.setMinPlayers(2);
		boardOption.setMaxPlayers(6);

		board = new Board(boardOption);
	}

	@Test
	public void loadGraphTest() {
		assertThat(board.size()).isEqualTo(73);

	}

//	@Test
//	public void nodesAtDistanceTest() {
//		List<Integer> destinos = board.getSquaresAtDistance(73, 1).toArray();
//
//		assertThat(destinos.size()).isEqualTo(6);
//		assertThat(destinos.get(0)).isEqualTo(67);
//		assertThat(destinos.get(1)).isEqualTo(52);
//		assertThat(destinos.get(2)).isEqualTo(72);
//		assertThat(destinos.get(3)).isEqualTo(57);
//		assertThat(destinos.get(4)).isEqualTo(62);
//		assertThat(destinos.get(5)).isEqualTo(47);
//	}

}