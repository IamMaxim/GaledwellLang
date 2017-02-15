package ru.iammaxim.GaledwellLang.Parser;

import ru.iammaxim.GaledwellLang.Types.Function;

import java.util.ArrayList;

/**
 * Created by maxim on 2/12/17 at 3:03 PM.
 */
public class FunctionBuilder {
    private Tokener tokener;
    private Token t;

    public FunctionBuilder(ArrayList<Token> tokens) {
        tokener = new Tokener(tokens);
    }

    private Token eat() {
        return tokener.eat();
    }

    public ArrayList<Function> build() throws InvalidTokenException {
        ArrayList<Function> functions = new ArrayList<>();

        while (tokener.left() > 0) {
            Token functionName;

            //read function name
            if ((functionName = eat()).type != TokenType.IDENTIFIER)
                throw new InvalidTokenException("Excepted identifier");

            //read function args
            if (!eat().equals(new Token("(")))
                throw new InvalidTokenException("Excepted (");
            Tokener args = tokener.readTo(new Token(")"));

            //read function body
            if (!eat().equals(new Token("{")))
                throw new InvalidTokenException("Excepted {");
            Tokener body = tokener.readTo(new Token("}"));

            //process body
            ArrayList<Tokener> bodyParts = body.split(new Token(";"));
            for (Tokener statement : bodyParts) {
                //skip empty statements
                if (statement.isEmpty())
                    continue;
                StatementTree tree = parseExpression(statement);

                System.out.println("tree: " + tree);
            }
        }

        return functions;
    }

    private StatementTree parseExpression(Tokener tokener) throws InvalidTokenException {
        tokener.trimParentheses();

        int level = 0;
        Token t, highest = null;
        int highestPriorityIndex = -1;
        for (int i = 0; tokener.left() > 0; i++) {
            t = tokener.eat();
            if (t.token.equals("("))
                level++;
            else if (t.token.equals(")")) {
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
            return new StatementTree(tokener.tokens.get(0));

        return new StatementTree(highest,
                parseExpression(tokener.subtokener(0, highestPriorityIndex)),
                parseExpression(tokener.subtokener(highestPriorityIndex + 1, tokener.size())));
    }


    /**
     * @param first  first token
     * @param second second token
     * @return true if second's order is higher than first's
     * @throws InvalidTokenException
     */
    private boolean isOrderHigher(Token first, Token second) throws InvalidTokenException {
        return getOrder(first) < getOrder(second);
    }


    /**
     * @param t token to check
     * @return operator's order
     * @throws InvalidTokenException if operator unknown
     */
    private int getOrder(Token t) throws InvalidTokenException {
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

    private class StatementTree {
        public Token operator;
        public StatementTree left, right;


        public StatementTree(Token operator, StatementTree left, StatementTree right) {
            this.operator = operator;
            this.left = left;
            this.right = right;
        }

        public StatementTree(Token operator) {
            this.operator = operator;
        }

        @Override
        public String toString() {
            if (operator == null)
                return "null";

            StringBuilder sb = new StringBuilder();
            if (left != null)
                sb.append("(").append(left).append(")");
            sb.append(operator.token);
            if (right != null)
                sb.append("(").append(right).append(")");
            return sb.toString();
        }
    }

/*    private class StatementFunctionCall extends StatementTree {
        public String functionName;
        public ArrayList<String> args;

        public StatementFunctionCall(Token operator) {
            super(null);
            this.functionName =
        }


    }*/
}
