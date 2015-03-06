package es.uniovi.asw.trivial.sintactico;

import java.io.FileReader;

import es.uniovi.asw.trivial.main.GestorErrores;

public class AnalizadorEntrada {
	
	public GIFTParser getSintactico(String sourceName, String formatoEntrada, GestorErrores gestor) throws Exception{
		if(formatoEntrada.toUpperCase().equals("GIFT")){
			GIFTYylex lexico = new GIFTYylex(new FileReader(sourceName), gestor);
			return new GIFTParser(lexico, gestor, false);
		} else {
			throw new IllegalArgumentException("Formato de entrada no valido");
		}
	}

}
