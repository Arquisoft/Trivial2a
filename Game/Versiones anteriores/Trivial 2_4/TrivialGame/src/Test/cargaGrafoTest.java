package Test;

import static org.junit.Assert.*;

import java.util.List;

import model.Panel;
import model.Square;

import org.junit.Test;

public class cargaGrafoTest {

	@Test
	public void test() {
		Panel panel = new Panel("imagen", "grafo.txt");
		List<Square> nodos = panel.getSquares();
		assertEquals(73,nodos.size());
		
	}

}
