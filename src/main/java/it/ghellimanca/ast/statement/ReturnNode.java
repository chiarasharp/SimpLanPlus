package it.ghellimanca.ast.statement;

import it.ghellimanca.ast.type.VoidTypeNode;
import it.ghellimanca.semanticanalysis.Environment;
import it.ghellimanca.semanticanalysis.MissingInitializationException;
import it.ghellimanca.semanticanalysis.SemanticError;
import it.ghellimanca.ast.Node;
import it.ghellimanca.ast.exp.ExpNode;
import it.ghellimanca.ast.type.TypeNode;
import it.ghellimanca.semanticanalysis.TypeCheckingException;

import java.util.ArrayList;

/**
 * Node of the AST for a return statement
 *
 * A return statement has the form:
 * 'return' (exp)?
 *
 */
public class ReturnNode extends StatementNode {

    final private ExpNode exp;

    public ReturnNode() {
        this.exp = null;
    }

    public ReturnNode(ExpNode exp) {
        this.exp = exp;
    }

    @Override
    public String toPrint(String indent) {
        String res = "\n" + indent + "RET";
        if (this.exp != null) {
            res += exp.toPrint(indent + "\t");
        }
        return res;
    }

    @Override
    public String toString() { return toPrint("");}

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) throws MissingInitializationException {
        if (this.exp != null) {
            return exp.checkSemantics(env);
        }

        return new ArrayList<>();
    }

    @Override
    public TypeNode typeCheck() throws TypeCheckingException {

        // if there is an expression we return its type
        if (this.exp != null) {
            return exp.typeCheck();
        }

        return new VoidTypeNode();
    }
}
