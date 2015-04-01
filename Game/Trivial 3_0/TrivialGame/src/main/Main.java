package main;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;

import ast.AST;
import codigo.GeneracionDeFormatoSalida;
import sintactico.AnalizadorEntrada;
import visitor.ASTPrinter;

/**
 * Clase que inicia el compilador e invoca a todas sus fases.
 * 
 * No es necesario modificar este fichero. 
 * En su lugar hay modificar:
 * - En Análisis Sintactico: 'sintactico/sintac.y' y 'sintactico/lexico.l'
 * - En Generación de Codigo: 'codigo/GestionDeMemoria.java' y 'codigo/SeleccionDeInstrucciones.java'
 */
public class Main {
	//FICHEROS
	public static String sourceFile;
	public static String outputFile;
	
	//FORMATOS
	public static String formatoEntrada;
	public static String formatoSalida;

	public static void main(String[] args) throws Exception {	
		//Main con opciones
//		sourceFile = args[0];		
//		formatoEntrada = args[1];
//		outputFile = args[2];
//		formatoSalida = args[3];
		
		//Main sin opciones
		sourceFile = "src/entrada.txt";
		formatoEntrada = "GIFT";
		outputFile = "salida.txt";
		formatoSalida = "JSON";
		
		GestorErrores gestor = new GestorErrores();

		AST raiz = compile(sourceFile, gestor);
		if (!gestor.hayErrores())
			System.out.println("El archivo de entrada no contiene errores.");

		ASTPrinter.toHtml(sourceFile, raiz, "Traza arbol"); // Utilidad generada por VGen (opcional)
	}

	/**
	 * M�todo que coordina todas las fases del compilador
	 */
	public static AST compile(String sourceName, GestorErrores gestor) throws Exception {

		// 1. Fases de Análisis Léxico y Sintáctico
		AST raiz = new AnalizadorEntrada().process(sourceName, formatoEntrada, gestor);
		if (raiz == null) // Hay errores o AST no implementado
			return null;

		// 2. Fase de Generación de Código
		File sourceFile = new File(sourceName);
		Writer out = new FileWriter(new File(sourceFile.getParent(), outputFile));

		GeneracionDeFormatoSalida generador = new GeneracionDeFormatoSalida();
		generador.genera(sourceFile.getName(), raiz, out, formatoSalida);
		out.close();

		return raiz;
	}
}
