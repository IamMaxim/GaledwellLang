package ru.iammaxim.GaledwellLang.Parser;

/**
 * Created by maxim on 2/12/17 at 2:28 PM.
 */
public class Token {
    public TokenType type = TokenType.IDENTIFIER;
    public String token = "";

    public Token() {}

    public Token(TokenType type, String token) {
        this.type = type;
        this.token = token;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Token))
            return false;
        Token t = (Token) obj;
        return t.type == type && t.token.equals(token);
    }

    public Token(String t) throws InvalidTokenException {
        this.token = t;

        if (is(TokenType.SCOPE, t))
            this.type = TokenType.SCOPE;
        else if (is(TokenType.OPERATOR, t))
            this.type = TokenType.OPERATOR;
        else if (is(TokenType.DELIMITER, t))
            this.type = TokenType.DELIMITER;
        else if (is(TokenType.KEYWORD, t))
            this.type = TokenType.KEYWORD;
        else
            this.type = TokenType.IDENTIFIER;
    }

    public static boolean is(TokenType type, String t) throws InvalidTokenException {
        switch (type) {
            case SCOPE: return t.equals("(") ||
                            t.equals(")") ||
                            t.equals("{") ||
                            t.equals("}");
            case OPERATOR: return
                    t.equals("==") ||
                    t.equals("+") ||
                            t.equals("-") ||
                            t.equals("*") ||
                            t.equals("/") ||
                            t.equals("=") ||
                            t.equals("++") ||
                            t.equals("--");
            case DELIMITER: return
                    t.equals(";") ||
                            t.equals(",");
            case KEYWORD: return
                    t.equals("global");
            case IDENTIFIER: return true;

            default:
                throw new InvalidTokenException("Token.is() is not implemented for type " + type.toString());
        }
    }

    public boolean empty() {
        return token.isEmpty();
    }

    @Override
    public String toString() {
        return "{\"type\": \"" + type + "\", \"token\": \"" + token + "\"}";
    }

    public String toShortString() {
        return token;
    }
}
