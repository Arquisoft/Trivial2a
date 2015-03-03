package es.uniovi.asw.trivial.semantico;

import es.uniovi.asw.trivial.ast.Position;
import es.uniovi.asw.trivial.main.GestorErrores;
import es.uniovi.asw.trivial.visitor.DefaultVisitor;

public class FaseInferencia extends DefaultVisitor {

	public FaseInferencia(GestorErrores gestor) {
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
	// 		predicado(): M�todo para ayudar a implementar los predicados de la Gram�tica Atribuida.
	//
	// Ejemplo de uso (suponiendo implementado el m�todo "esPrimitivo"):
	//		predicado(esPrimitivo(expr.tipo), "La expresi�n debe ser de un tipo primitivo", expr.getStart());
	//
	// NOTA: El m�todo getStart() indica la linea/columna del fichero fuente de donde se ley� el nodo.
	// Si se usa VGen dicho m�todo ser� generado en todos los nodos AST.
	// Si no se quiere usar getStart() y por tanto omitir la informaci�n de linea/columna se puede invocar sin dicho argumento:
	//		predicado(esPrimitivo(expr.tipo), "La expresi�n debe ser un tipo primitivo");
	//
	private void predicado(boolean condicion, String mensajeError, Position posicionError) {
		if (!condicion)
			gestorErrores.error("Inferencia", mensajeError, posicionError);
	}

	private void predicado(boolean condicion, String mensajeError) {
		predicado(condicion, mensajeError, null);
	}

	private GestorErrores gestorErrores;
}
