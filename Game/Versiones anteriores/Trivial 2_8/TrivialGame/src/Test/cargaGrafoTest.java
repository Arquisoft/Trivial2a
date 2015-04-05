package Test;

import static org.junit.Assert.*;

import java.util.List;

import model.BoardOptions;
import model.Panel;
import model.Square;

import org.junit.Test;

public class cargaGrafoTest {

	@Test
	public void test() {
		Panel panel = new Panel(BoardOptions.BIG);
		List<Square> nodos = panel.getSquares();
		assertEquals(73,nodos.size());
		
	}

}
