package it.ghellimanca.ast;

import it.ghellimanca.ast.declaration.DecVarNode;
import it.ghellimanca.ast.statement.IteNode;
import it.ghellimanca.ast.statement.ReturnNode;
import it.ghellimanca.ast.type.VoidTypeNode;
import it.ghellimanca.semanticanalysis.*;
import it.ghellimanca.ast.declaration.DeclarationNode;
import it.ghellimanca.ast.statement.StatementNode;
import it.ghellimanca.ast.type.TypeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Node of the AST for a block
 *
 *todo: aggiorna la codegen in caso di aggiunta registri al RdA, o in caso si usi meglio il RA
 */
public class BlockNode extends StatementNode {

    final private List<DecVarNode> variableDeclarations;
    final private List<StatementNode> statements;



    public BlockNode(List<DecVarNode> variableDeclarations, List<StatementNode> statements) {
        this.variableDeclarations = variableDeclarations;
        this.statements = statements;
    }



    public List<DecVarNode> getVariableDeclarations() {
        return variableDeclarations;
    }

    public List<StatementNode> getStatements() {
        return statements;
    }


    @Override
    public String toPrint(String indent) {
        String res = "\n" + indent + "BLOCK";

        if (this.variableDeclarations != null) {
            for (DeclarationNode dec : variableDeclarations) {
                res += dec.toPrint(indent + "\t");
            }
        }

        if (this.statements != null) {
            for (StatementNode stat : statements) {
                res += stat.toPrint(indent + "\t");
            }
        }

        return res;
    }


    @Override
    public String toString() {
        return toPrint("");
    }


    @Override
    public ArrayList<SemanticWarning> checkSemantics(Environment env) throws MultipleDeclarationException, MissingDeclarationException, MissingInitializationException, ParametersCountException {

        ArrayList<SemanticWarning> err = new ArrayList<>();
        Map<String, STEntry> currentScope;

        env.newScope();

        if (this.variableDeclarations != null) {
            for (DeclarationNode dec : variableDeclarations) {
                err.addAll(dec.checkSemantics(env));
            }
        }

        if (this.statements != null) {
            for (StatementNode stat : statements) {
                err.addAll(stat.checkSemantics(env));
            }
        }

        // CHECK ERRORS IN BLOCK FOR EFFECT ANALYSIS

        // getting sigma_1'' from sigma'' = sigma_0'' ⋅ sigma_1''
        currentScope = env.currentScope();

        // for every x_i in dom(sigma_1'')
        for (var id : currentScope.keySet()) {

            var idEntry = currentScope.get(id);
            var idStatus = idEntry.getVarStatus();

            // sigma_1''(x) has to be <= USED otherwise there was an error
            if (idStatus.equals(new Effect(Effect.ERROR))) {
                throw new MissingInitializationException(id + " was used before initialization.");
            } else if (idStatus.equals(new Effect(Effect.INIT)) || idStatus.equals(new Effect(Effect.DECLARED))) {
                err.add(new SemanticWarning("Variable " + id + " was declared but never used."));
            }
        }

        // returning just sigma_0''
        env.exitScope();

        return err;
    }


    @Override
    public TypeNode typeCheck() throws TypeCheckingException {

        if (this.variableDeclarations != null) {
            for (DeclarationNode dec : variableDeclarations) {
                dec.typeCheck();
            }
        }

        if (this.statements != null) {

            // gathering the eventual itenode/blocknode/returnnode types inside the block in a list
            ArrayList<TypeNode> stmTypesRet = new ArrayList<>();

            for (StatementNode stat : statements) {
                if (stat instanceof ReturnNode || stat instanceof IteNode || stat instanceof BlockNode){
                    TypeNode stmTypeRet = stat.typeCheck();
                    stmTypesRet.add(stmTypeRet);
                }
                else {
                    stat.typeCheck(); // always continuing the typecheck anyway
                }
            }

            // if there are multiple return types from the nodes that can return types that are not void
            if(stmTypesRet.size() > 0) {
                for (int i = 0; i < stmTypesRet.size() - 1; i++) {

                    // we check that they are the same
                    if (!(stmTypesRet.get(i).equals(stmTypesRet.get(i + 1)))) {
                        throw new TypeCheckingException("Different types to return.");
                    }
                }

                // returning the common type
                return stmTypesRet.get(0);
            }
        }

        return new VoidTypeNode();
    }

    @Override
    public String codeGeneration() {
        StringBuilder buff = new StringBuilder();

        // build AR
        if (this.variableDeclarations != null){
            buff.append("subi $sp $sp").append(variableDeclarations.size()).append("\n");
        }
        buff.append("li $t0 0\n");    // using a fake RA
        buff.append("push $t0\n");
        buff.append("push $fp\n");
        buff.append("mv $fp $sp\n");

        if (this.variableDeclarations != null) {
            for (DeclarationNode varDec : variableDeclarations) {
                buff.append(varDec.codeGeneration());
            }
        }
        if (this.statements != null){
            for (StatementNode stm : statements) {
                buff.append(stm.codeGeneration());
            }
        }

        // restore registers to previous values
        buff.append("lw $fp 0($sp)\n");     // restore old $fp
        buff.append("pop ;pop old $fp\n");
        buff.append("pop ;pop fake RA\n");
        buff.append("addi $sp $sp").append(variableDeclarations.size()).append("\n");

        return buff.toString();
    }


}
