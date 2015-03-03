package es.uniovi.asw.trivial.ast;

import java.util.*;
import es.uniovi.asw.trivial.visitor.*;

//	Pregunta -> titulo:String  texto:String  correcta:String  incorrectas:String* ;

public class Pregunta extends AbstractTraceable implements AST {

	public Pregunta(String titulo, String texto, String correcta, List<String> incorrectas) {
		this.titulo = titulo;
		this.texto = texto;
		this.correcta = correcta;
		this.incorrectas = incorrectas;
	}

	@SuppressWarnings("unchecked")
	public Pregunta(Object titulo, Object texto, Object correcta, Object incorrectas) {
		this.titulo = (titulo instanceof Token) ? ((Token)titulo).getLexeme() : (String) titulo;
		this.texto = (texto instanceof Token) ? ((Token)texto).getLexeme() : (String) texto;
		this.correcta = (correcta instanceof Token) ? ((Token)correcta).getLexeme() : (String) correcta;
		this.incorrectas = tokensToStrings(incorrectas);

		searchForPositions(titulo, texto, correcta, incorrectas);	// Obtener linea/columna a partir de los hijos
	}

	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getCorrecta() {
		return correcta;
	}
	public void setCorrecta(String correcta) {
		this.correcta = correcta;
	}

	public List<String> getIncorrectas() {
		return incorrectas;
	}
	public void setIncorrectas(List<String> incorrectas) {
		this.incorrectas = incorrectas;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}


	@SuppressWarnings("unchecked")
	static private List<String> tokensToStrings(Object objectList) {
		if (objectList == null)
			return null;
		List<String> strings = new ArrayList<String>();
		for (Object o : (List<Object>) objectList)
			strings.add((o instanceof Token) ? ((Token) o).getLexeme() : (String) o);
		return strings;
	}

	private String titulo;
	private String texto;
	private String correcta;
	private List<String> incorrectas;
}

