package ru.iammaxim.GaledwellLang.Parser;

import ru.iammaxim.GaledwellLang.Main;
import ru.iammaxim.GaledwellLang.Parser.Expression.*;
import ru.iammaxim.GaledwellLang.Values.Value;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by maxim on 2/12/17 at 3:03 PM.
 */
public class FunctionParser {
    private Tokener tokener;
    private Token t;

    public FunctionParser(ArrayList<Token> tokens) {
        tokener = new Tokener(tokens);
    }

    public static Expression parseExpression(Tokener tokener) throws InvalidTokenException {
        tokener.trimParentheses();

        try {
            Main.fos3.write(("parsing " + tokener + "\n").getBytes());

            //check if this is value
            if (tokener.size() == 1) {
                Value val = Value.get(tokener.tokens.get(0).token);
                if (val != null)
                    return new ExpressionValue(val);
            }

            //check if this is return
            if (tokener.size() >= 2) {
                if (tokener.tokens.get(0).token.equals("return")) {
                    return new ExpressionReturn(parseExpression(tokener.subtokener(1, tokener.size())));
                }
            }

            int level = 0;
            Token t, highest = null;
            int highestPriorityIndex = -1;
            for (int i = 0; tokener.left() > 0; i++) {
                t = tokener.eat();
                if (t.token.equals("(")) {
                    //check if this is function call
                    if (highest == null && i > 0 && tokener.tokens.get(i - 1).type == TokenType.IDENTIFIER)
                        try {
                            int index = tokener.index;
                            Tokener argsTokener = tokener.readTo(new Token(")"));
                            ArrayList<Tokener> args = argsTokener.split(new Token(","));
                            tokener.index = index;
                            return new ExpressionFunctionCall(tokener.tokens.get(i - 1), args);
                        } catch (InvalidTokenException e) {
                            e.printStackTrace();
                        }

                    level++;
                } else if (t.token.equals(")")) {
                    level--;
                }
                if (level == 0) {
                    if (t.type == TokenType.OPERATOR) {
                        if (highest == null) {
                            highest = t;
                            highestPriorityIndex = i;
                        } else {
                            if (isOrderHigher(highest, t)) {
                                highest = t;
                                highestPriorityIndex = i;
                            }
                        }
                    }
                }
            }

            if (highest == null)
                return new ExpressionTree(tokener.tokens.get(0));

            return new ExpressionTree(highest,
                    parseExpression(tokener.subtokener(0, highestPriorityIndex)),
                    parseExpression(tokener.subtokener(highestPriorityIndex + 1, tokener.size())));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * @param first  first token
     * @param second second token
     * @return true if second's order is higher than first's
     * @throws InvalidTokenException if one of operators unknown
     */
    private static boolean isOrderHigher(Token first, Token second) throws InvalidTokenException {
        return getOrder(first) < getOrder(second);
    }

    /**
     * @param t token to check
     * @return operator's order
     * @throws InvalidTokenException if operator unknown
     */
    private static int getOrder(Token t) throws InvalidTokenException {
        int level = 0;
        if (t.token.equals("*") || t.token.equals("/"))
            level = 1;
        else if (t.token.equals("+") || t.token.equals("-"))
            level = 2;
        else if (t.token.equals("++") || t.token.equals("--"))
            level = 3;
        else if (t.token.equals("=="))
            level = 4;
        else if (t.token.equals("="))
            level = 5;
        else throw new InvalidTokenException("Excepted operator, but got " + t.token);

        return level;
    }

    private Token eat() {
        return tokener.eat();
    }

    public ArrayList<ParsedFunction> build() throws InvalidTokenException {
        ArrayList<ParsedFunction> functions = new ArrayList<>();

        while (tokener.left() > 0) {
            Token functionName;
            String[] args;
            ArrayList<Expression> exps = new ArrayList<>();

            //read function name
            if ((functionName = eat()).type != TokenType.IDENTIFIER)
                throw new InvalidTokenException("Excepted identifier while parsing function name");

            //read function args
            if (!eat().equals(new Token("(")))
                throw new InvalidTokenException("Excepted (");
            Tokener argsTokener = tokener.readTo(new Token(")"));
            ArrayList<Tokener> argsTokeners = argsTokener.split(new Token(","));
            args = new String[argsTokeners.size()];
            for (int i = 0; i < argsTokeners.size(); i++) {
                Tokener argTokener1 = argsTokeners.get(i);
                if (argTokener1.size() > 1)
                    throw new InvalidTokenException("Excepted 1 identifier, got " + argTokener1.size() + " while parsing argument");
                if (argTokener1.size() > 0)
                    args[i] = argTokener1.tokens.get(0).token;
            }

            //read function body
            if (!eat().equals(new Token("{")))
                throw new InvalidTokenException("Excepted { while parsing function body");
            Tokener body = tokener.readTo(new Token("}"));

            //process body
            ArrayList<Tokener> bodyParts = body.split(new Token(";"));
            for (Tokener statement : bodyParts) {
                //skip empty statements
                if (statement.isEmpty())
                    continue;
                Expression exp = parseExpression(statement);
                exps.add(exp);

//                System.out.println("tree: " + exp);
            }

            //build function
            functions.add(new ParsedFunction(functionName.token, args, exps));
        }

        return functions;
    }
}
