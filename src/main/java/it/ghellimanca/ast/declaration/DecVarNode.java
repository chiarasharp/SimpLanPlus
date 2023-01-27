package it.ghellimanca.ast.declaration;

import it.ghellimanca.semanticanalysis.*;
import it.ghellimanca.ast.IdNode;
import it.ghellimanca.ast.exp.ExpNode;
import it.ghellimanca.ast.type.TypeNode;

import java.util.ArrayList;


/**
 * Node of the AST for a variable declaration
 *
 * A variable declaration has the form:
 * type ID ('=' exp)? ';' ;
 *
 */

public class DecVarNode extends DeclarationNode {

    final private TypeNode type;
    final private IdNode id;
    final private ExpNode exp;



    public DecVarNode(TypeNode type, IdNode id, ExpNode exp) {
        this.type = type;
        this.id = id;
        this.exp = exp;
    }



    @Override
    public String toPrint(String indent) {
        String res = "\n" + indent + "DECVAR" + type.toPrint(indent + "\t") + id.toPrint(indent + "\t");
        if (this.exp != null) {
            res += exp.toPrint(indent + "\t");
        }
        return res;
    }


    @Override
    public String toString() { return toPrint("");}


    @Override
    public ArrayList<SemanticWarning> checkSemantics(Environment env) throws MultipleDeclarationException, MissingDeclarationException, MissingInitializationException, ParametersCountException {

        ArrayList<SemanticWarning> err = new ArrayList<>();

        if (exp != null) {  // if the variable is also initialized
                err.addAll(exp.checkSemantics(env));
                env.addDeclaration(id.getIdentifier(), type, Effect.INIT);

            } else {
                env.addDeclaration(id.getIdentifier(), type, Effect.DECLARED);
            }

        return err;
    }


    @Override
    public TypeNode typeCheck() throws TypeCheckingException {
        if (exp != null) {
            TypeNode expNodeType = exp.typeCheck();

            if (!type.equals(expNodeType)){
                throw new TypeCheckingException("Type mismatch: expression " + exp + " type does not match variable " + id.getIdentifier() + " type.");
            }
        }

        return type;
    }

}
