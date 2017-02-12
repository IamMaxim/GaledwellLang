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

    public void parse(String src) {
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

            // check if this is scope
            if (c == '(' || c == ')' || c == '{' || c == '}') {
                cutOffToken();
                tokens.add(new Token(TokenType.SCOPE, String.valueOf(c)));
                continue;
            }

            //check if this is operator
            if (c == '=' && chars[i + 1] == '=') {
                cutOffToken();
                tokens.add(new Token(TokenType.OPERATOR, "=="));
                i++; // skip next '='
                continue;
            } else if (c == '+' || c == '-' || c == '*' || c == '/' || c == '=') {
                cutOffToken();
                tokens.add(new Token(TokenType.OPERATOR, String.valueOf(c)));
                continue;
            }

            //check if this is delimiter
            if (c == ';' || c == ',') {
                cutOffToken();
                tokens.add(new Token(TokenType.DELIMITER, String.valueOf(c)));
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
