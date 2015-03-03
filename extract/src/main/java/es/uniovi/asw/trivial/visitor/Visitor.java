package es.uniovi.asw.trivial.visitor;

import es.uniovi.asw.trivial.ast.*;

public interface Visitor {
	public Object visit(Fichero node, Object param);
	public Object visit(Elemento node, Object param);
	public Object visit(Pregunta node, Object param);
}
