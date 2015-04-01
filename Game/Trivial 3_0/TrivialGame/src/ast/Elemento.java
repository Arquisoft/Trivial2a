package ast;

import visitor.*;

//	Elemento -> categoria:String  pregunta:Pregunta ;

public class Elemento extends AbstractTraceable implements AST {

	public Elemento(String categoria, Pregunta pregunta) {
		this.categoria = categoria;
		this.pregunta = pregunta;

		searchForPositions(pregunta);	// Obtener linea/columna a partir de los hijos
	}

	public Elemento(Object categoria, Object pregunta) {
		this.categoria = (categoria instanceof Token) ? ((Token)categoria).getLexeme() : (String) categoria;
		this.pregunta = (Pregunta) pregunta;

		searchForPositions(categoria, pregunta);	// Obtener linea/columna a partir de los hijos
	}

	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Pregunta getPregunta() {
		return pregunta;
	}
	public void setPregunta(Pregunta pregunta) {
		this.pregunta = pregunta;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private String categoria;
	private Pregunta pregunta;
}

