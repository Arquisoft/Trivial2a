package es.uniovi.asw.trivial.semantico;

import es.uniovi.asw.trivial.ast.AST;
import es.uniovi.asw.trivial.main.GestorErrores;

/**
 * Esta clase coordina las dos fases del An�lisis Sem�ntico:
 * 1- Fase de Identificaci�n
 * 2- Fase de Inferencia
 * 
 * No es necesario modificar esta clase.
 * En su lugar hay que modificar "FaseIdentificaci�n.java" y "FaseInferencia.java"
 */
public class Semantico {
	
	public Semantico(GestorErrores gestor) {
		this.gestorErrores = gestor;
	}
	
	public void analiza(AST raiz) {
		FaseIdentificacion identificacion = new FaseIdentificacion(gestorErrores);
		raiz.accept(identificacion, null);

		if (gestorErrores.hayErrores())
			return;

		FaseInferencia inferencia = new FaseInferencia(gestorErrores);
		raiz.accept(inferencia, null);
	}

	private GestorErrores gestorErrores;
}
