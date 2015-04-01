package ast;

import java.util.*;
import visitor.*;

//	Fichero -> elementos:Elemento* ;

public class Fichero extends AbstractTraceable implements AST {

	public Fichero(List<Elemento> elementos) {
		this.elementos = elementos;

		searchForPositions(elementos);	// Obtener linea/columna a partir de los hijos
	}

	@SuppressWarnings("unchecked")
	public Fichero(Object elementos) {
		this.elementos = (List<Elemento>) elementos;

		searchForPositions(elementos);	// Obtener linea/columna a partir de los hijos
	}

	public List<Elemento> getElementos() {
		return elementos;
	}
	public void setElementos(List<Elemento> elementos) {
		this.elementos = elementos;
	}

	@Override
	public Object accept(Visitor v, Object param) { 
		return v.visit(this, param);
	}

	private List<Elemento> elementos;
}

