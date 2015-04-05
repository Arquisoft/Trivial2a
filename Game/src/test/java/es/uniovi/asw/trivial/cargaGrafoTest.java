package es.uniovi.asw.trivial;

import static org.assertj.core.api.Assertions.*;
import org.junit.Test;

import java.util.List;

import es.uniovi.asw.trivial.model.BoardOptions;
import es.uniovi.asw.trivial.model.Panel;
import es.uniovi.asw.trivial.model.Square;

public class cargaGrafoTest {

	@Test
	public void test() {
		Panel panel = new Panel(BoardOptions.BIG);
		List<Square> nodos = panel.getSquares();
		assertThat(nodos.size()).isEqualTo(73);
		
	}

}