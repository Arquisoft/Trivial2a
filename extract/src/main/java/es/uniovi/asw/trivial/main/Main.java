package es.uniovi.asw.trivial.main;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;

import es.uniovi.asw.trivial.ast.AST;
import es.uniovi.asw.trivial.codigo.GeneracionDeCodigo;
import es.uniovi.asw.trivial.sintactico.Parser;
import es.uniovi.asw.trivial.sintactico.Yylex;
import es.uniovi.asw.trivial.visitor.ASTPrinter;

/**
 * Clase que inicia el compilador e invoca a todas sus fases.
 * 
 * No es necesario modificar este fichero. 
 * En su lugar hay modificar:
 * - En An�lisis Sint�ctico: 'sintactico/sintac.y' y 'sintactico/lexico.l'
 * - En An�lisis Sem�ntico: 'semantico/FaseIdentificaci�n.java' y 'semantico/FaseInferencia.java'
 * - En Generaci�n de C�digo: 'codigo/GestionDeMemoria.java' y 'codigo/SeleccionDeInstrucciones.java'
 */
public class Main {
	public static String sourceFile="src/entrada.txt";

	public static void main(String[] args) throws Exception {
		//sourceFile = args[0];
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
		Yylex lexico = new Yylex(new FileReader(sourceName), gestor);
		Parser sintactico = new Parser(lexico, gestor, false);
		sintactico.parse();

		AST raiz = sintactico.getAST();
		if (raiz == null) // Hay errores o AST no implementado
			return null;

		// 2. Fase de Generación de Código
		File sourceFile = new File(sourceName);
		Writer out = new FileWriter(new File(sourceFile.getParent(), "salida.txt"));

		GeneracionDeCodigo generador = new GeneracionDeCodigo();
		generador.genera(sourceFile.getName(), raiz, out);
		out.close();

		return raiz;
	}
}
