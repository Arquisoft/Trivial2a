package es.uniovi.asw.trivial.ast;

import es.uniovi.asw.trivial.visitor.*;

public interface AST extends Traceable {
	public Object accept(Visitor visitor, Object param);
}

