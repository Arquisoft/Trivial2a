package visitor;

import ast.*;
import java.util.*;

/*
DefaultVisitor. Implementaci�n base del visitor para ser derivada por nuevos visitor.
	No modificar esta clase. Para crear nuevos visitor usar el fichero "_PlantillaParaVisitors.txt".
	DefaultVisitor ofrece una implementaci�n por defecto de cada nodo que se limita a visitar los nodos hijos.
*/
public class DefaultVisitor implements Visitor {

	//	class Fichero { List<Elemento> elementos; }
	public Object visit(Fichero node, Object param) {
		visitChildren(node.getElementos(), param);
		return null;
	}

	//	class Elemento { String categoria;  Pregunta pregunta; }
	public Object visit(Elemento node, Object param) {
		if (node.getPregunta() != null)
			node.getPregunta().accept(this, param);
		return null;
	}

	//	class Pregunta { String titulo;  String texto;  String correcta;  List<String> incorrectas; }
	public Object visit(Pregunta node, Object param) {
		return null;
	}
	
	// M�todo auxiliar -----------------------------
	protected void visitChildren(List<? extends AST> children, Object param) {
		if (children != null)
			for (AST child : children)
				child.accept(this, param);
	}
}
