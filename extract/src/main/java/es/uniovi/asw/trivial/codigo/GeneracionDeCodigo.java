package es.uniovi.asw.trivial.codigo;

import java.io.Writer;

import es.uniovi.asw.trivial.ast.AST;

/**
 * Esta clase coordina las dos fases principales de la Generaci�n de C�digo:
 * 1- Gesti�n de Memoria (asignaci�n de direcciones)
 * 2- Selecci�n de Instrucciones
 * 
 * No es necesario modificar esta clase.
 * En su lugar hay que modificar "Gesti�nDeMemoria.java" y "Selecci�nDeInstrucciones.java".
 */
public class GeneracionDeCodigo {

	public void genera(String sourceFile, AST raiz, Writer out) {

		FormateadoJSON selecciona = new FormateadoJSON(out, sourceFile);
		raiz.accept(selecciona, null);
	}

}
