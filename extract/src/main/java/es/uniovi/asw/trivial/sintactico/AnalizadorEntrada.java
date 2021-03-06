package es.uniovi.asw.trivial.sintactico;

import java.io.FileReader;

import es.uniovi.asw.trivial.ast.AST;
import es.uniovi.asw.trivial.main.GestorErrores;

public class AnalizadorEntrada {
	
	public AST process(String sourceName, String formatoEntrada, GestorErrores gestor) throws Exception{
		if(formatoEntrada.toUpperCase().equals("GIFT")){
			GIFTYylex lexico = new GIFTYylex(new FileReader(sourceName), gestor);
			GIFTParser parser = new GIFTParser(lexico, gestor, false);
			parser.parse();
			return parser.getAST();
		} else {
			throw new IllegalArgumentException("Formato de entrada no valido");
		}
	}

}
