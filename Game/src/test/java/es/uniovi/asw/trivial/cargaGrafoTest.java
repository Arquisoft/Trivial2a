package es.uniovi.asw.trivial;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import es.uniovi.asw.trivial.model.BoardOptions;
import es.uniovi.asw.trivial.model.Panel;
import es.uniovi.asw.trivial.model.Square;

public class cargaGrafoTest {

	@Test
	public void test() {
		Panel panel = new Panel(BoardOptions.BIG);
		List<Square> nodos = panel.getSquares();
		assertEquals(73,nodos.size());
		
	}

}
