package it.ghellimanca.ast;

import org.antlr.v4.runtime.*;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Handles the error management.
 *
 */
public class SimpLanPlusErrorListener extends BaseErrorListener {

    List<String> errors = new ArrayList<>();


    public List<String> getErrors() {
        return errors;
    }


    /**
     * For each error detected by the Error Listener, it adds to the errors array its error message.
     *
     * @param recognizer            can be either the ANTR Lexer or Parser
     * @param offendingSymbol       symbol in the input stream where the error was detected
     * @param line                  line of code where the error was detected
     * @param charPositionInLine    position in line of the code where the error was detected
     * @param msg                   error message generated by the Error Listener
     * @param e                     exception raised by the Recognizer
     */
    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {

        String finalError = "line " + line + ":" + charPositionInLine + " " + msg + "\n";

        if (recognizer instanceof Parser) {
            String underlineError = underlineError(recognizer, (Token) offendingSymbol, line, charPositionInLine);
            errors.add(finalError + underlineError);
        }
        else {
            errors.add(finalError);
        }
    }

    /**
     * Formats the error so that the line of code where it occurs is printed in the underlined form
     *
     *
     * @param recognizer            can be either an ANTLR Lexer or Parser
     * @param offendingToken        token in the input stream where the error has been detected
     * @param line                  position in code where the error occurs
     * @param charPositionInLine    position in line of the code where the error occurs
     * @return  the underlined line of code where the error has been detected
     */
    protected String underlineError(Recognizer recognizer, Token offendingToken, int line, int charPositionInLine) {

        StringBuilder res = new StringBuilder();
        CommonTokenStream tokens = (CommonTokenStream) recognizer.getInputStream();
        String input = tokens.getTokenSource().getInputStream().toString();
        String[] lines = input.split("\n");
        String errorLine = lines[line - 1];

        res.append(errorLine).append("\n");

        for (int i=0; i<charPositionInLine; i++)
            res.append(" ");

        int start = offendingToken.getStartIndex();
        int stop = offendingToken.getStopIndex();
        if ( start>=0 && stop>=0 ) {
            for (int i=start; i<=stop; i++)
                res.append("^");
        }

        res.append("\n");

        return res.toString();
    }
}