package ru.iammaxim.GaledwellLang.Parser;

import java.util.ArrayList;

/**
 * Created by maxim on 2/12/17 at 2:24 PM.
 */
public class Parser {
    private Token ct = new Token(); //current token
    public ArrayList<Token> tokens = new ArrayList<>();

    public Parser() {
    }

    public void parse(String src) throws InvalidTokenException {
        char[] chars = src.toCharArray();
        for (int i = 0; i < src.length(); i++) {
            char c = chars[i];

            if (c == '/' && chars[i + 1] == '/') { // comment
                i = src.indexOf('\n', i); // go to next line
                continue;
            }

            //parse string
            if (c == '"') {
                ct.token += c;
                while ((c = chars[++i]) != '"') {
                    ct.token += c;
                }
                ct.token += chars[i];
                continue;
            }

            if (c == ' ') {
                cutOffToken();
                continue;
            }

            String s;

            //check if this is operator
            //check double-character operators (==, ++, --)
            if (src.length() - i > 1) {
                s = new String(new char[]{c, chars[i + 1]});
                if (Token.is(TokenType.OPERATOR, s)) {
                    cutOffToken();
                    tokens.add(new Token(s));
                    i++; // skip next '='
                    continue;
                }
            }
            //check single-character operators (+, -, *, /, *)
            s = String.valueOf(c);
            if (Token.is(TokenType.OPERATOR, s)) {
                cutOffToken();
                tokens.add(new Token(s));
                continue;
            }

            // check if this is scope
            if (Token.is(TokenType.SCOPE, s)) {
                cutOffToken();
                tokens.add(new Token(s));
                continue;
            }

            //check if this is delimiter
            if (Token.is(TokenType.DELIMITER, s)) {
                cutOffToken();
                tokens.add(new Token(s));
                continue;
            }

            ct.token += c;
        }
    }

    private void cutOffToken() {
        ct.token = ct.token.trim();
        if (!ct.empty()) {
            tokens.add(ct);
            ct = new Token();
        }
    }
}
