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

    public boolean empty() {
        return token.isEmpty();
    }

    @Override
    public String toString() {
        return "{\"type\": " + type + ", \"token\": \"" + token + "\"}";
    }
}
