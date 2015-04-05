package es.uniovi.asw.trivial.codigo;

import java.io.PrintWriter;
import java.io.Writer;

import es.uniovi.asw.trivial.ast.Elemento;
import es.uniovi.asw.trivial.ast.Pregunta;
import es.uniovi.asw.trivial.visitor.DefaultVisitor;

public class FormateadoJSON extends DefaultVisitor {

	private PrintWriter writer;
	@SuppressWarnings("unused")
	private String sourceFile;

	public FormateadoJSON(Writer writer, String sourceFile) {
		this.writer = new PrintWriter(writer);
		this.sourceFile = sourceFile;
	}

	/*
	 * Poner aquí los visit necesarios.
	 * Si se ha usado VGen solo hay que copiarlos de la clase 'visitor/EsqueletoVisitor.java'.
	 */

	//	Ejemplo:
	//
	//	public Object visit(Programa node, Object param) {
	//		out("#source \"" + sourceFile + "\"");
	//		out("call main");
	//		out("halt");
	//		super.visit(node, param);	// Recorrer los hijos
	//		return null;
	//	}

	@Override
	public Object visit(Elemento node, Object param) {
		out("{ \"category\":\"" + node.getCategoria() + "\",");
		super.visit(node, param);
		out("}");
		out("");
		return null;
	}

	@Override
	public Object visit(Pregunta node, Object param) {
		out("  \"question\":\"" + node.getTitulo() + "\",");
		out("  \"text\":\"" + node.getTexto() + "\",");
		out("  \"correct\":\"" + node.getCorrecta() + "\",");
		out("  \"incorrect\": [");
		int i;
		for (i = 0; i < node.getIncorrectas().size() - 1; i++)
			out("\t\t\"" + node.getIncorrectas().get(i) + "\",");
		out("\t\t\"" + node.getIncorrectas().get(i) + "\" ]");
		return null;
	}
	
	
	
	

	
	
	
	
	
	
	// M�todo auxiliar recomendado -------------
	private void out(String text) {
		writer.println(text);
	}

}
