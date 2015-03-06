package es.uniovi.asw.trivial.sintactico;

import es.uniovi.asw.trivial.ast.AST;

public interface Parser {

	public int parse();
	public AST getAST();
	
}
