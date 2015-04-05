package codigo;

import java.io.Writer;

import ast.AST;
import visitor.DefaultVisitor;

/**
 * Esta clase genera el fichero de salida con el formato elegido
 */
public class GeneracionDeFormatoSalida {
	
	private DefaultVisitor selecciona;

	public void genera(String sourceFile, AST raiz, Writer out, String formatoSalida) {

		if(formatoSalida.toUpperCase().equals("JSON")){
			selecciona = new FormateadoJSON(out, sourceFile);
		} else {
			throw new IllegalArgumentException("Formato de salida no válido");
		}
		raiz.accept(selecciona, null);
	}

}
