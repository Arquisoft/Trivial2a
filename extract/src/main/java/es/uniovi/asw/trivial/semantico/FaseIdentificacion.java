package es.uniovi.asw.trivial.semantico;

import es.uniovi.asw.trivial.ast.Position;
import es.uniovi.asw.trivial.main.GestorErrores;
import es.uniovi.asw.trivial.visitor.DefaultVisitor;

public class FaseIdentificacion extends DefaultVisitor {

	public FaseIdentificacion(GestorErrores gestor) {
		this.gestorErrores = gestor;
	}

	/*
	 * Poner aqu� los visit necesarios.
	 * Si se ha usado VGen solo hay que copiarlos de la clase 'visitor/EsqueletoVisitor.java'.
	 */

	// public Object visit(Programa prog, Object param) {
	// ...
	// }


	
	
	// --------------------------------------------------------
	// M�todos auxiliares recomendados
	//
	// Ejemplo de uso:
	//		error("Variable no definida", node.getStart());
	//
	// NOTA: El m�todo getStart() indica la linea/columna del fichero fuente de donde se ley� el nodo.
	// Si se usa VGen dicho m�todo ser� generado en todos los nodos AST.
	// Si no se quiere usar getStart() y por tanto omitir la informaci�n de linea/columna se puede invocar sin dicho argumento:
	//		error("Variable no definida");
	//

	private void error(String mensajeError, Position posicionError) {
		gestorErrores.error("Identificaci�n", mensajeError, posicionError);
	}

	private void error(String mensajeError) {
		error(mensajeError, null);
	}

	private GestorErrores gestorErrores;
}
