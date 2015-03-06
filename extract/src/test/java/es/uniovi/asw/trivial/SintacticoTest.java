package es.uniovi.asw.trivial;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import es.uniovi.asw.trivial.ast.AST;
import es.uniovi.asw.trivial.main.GestorErrores;
import es.uniovi.asw.trivial.sintactico.GIFTParser;
import es.uniovi.asw.trivial.sintactico.GIFTYylex;
import es.uniovi.asw.trivial.sintactico.Parser;
import es.uniovi.asw.trivial.visitor.ASTPrinter;

public class SintacticoTest {
	
	@Test
	public void emptyExtractor() throws IOException{
		GestorErrores gestor = new GestorErrores();
		String sourceFile="src/test/java/es/uniovi/asw/trivial/entradaTest1.txt";
		
		File entrada = new File(sourceFile);
		System.out.println(entrada.getAbsolutePath());
		
		GIFTYylex lexico = new GIFTYylex(new FileReader(sourceFile), gestor);
		Parser sintactico = new GIFTParser(lexico, gestor, false);
		sintactico.parse();
		AST raiz = sintactico.getAST();
		ASTPrinter.toHtml(sourceFile, raiz, "trazaArbolTest1");
		
		File trazaArbolEsperada = new File("trazaArbolEsperadaTest1.html");
		File trazaArbol = new File("trazaArbolTest1.html");

		
		assertThat(FileUtils.contentEquals(trazaArbol, trazaArbolEsperada)).isFalse();
	}

}
