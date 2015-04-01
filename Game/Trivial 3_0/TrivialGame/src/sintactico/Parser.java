package sintactico;

import ast.AST;

public interface Parser {
	public int parse();

	public AST getAST();
}
