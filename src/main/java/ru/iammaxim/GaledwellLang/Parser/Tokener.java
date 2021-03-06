package ru.iammaxim.GaledwellLang.Parser;

import java.util.ArrayList;

/**
 * Created by maxim on 2/13/17 at 4:25 PM.
 */
public class Tokener {
    public ArrayList<Token> tokens;
    public int index = 0;

    public Tokener(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

    public Tokener(Token... tokens) {
        this.tokens = new ArrayList<>();
        for (Token token : tokens) {
            this.tokens.add(token);
        }
    }

    public int left() {
        return tokens.size() - index;
    }

    public Token eat() {
        Token t = tokens.get(index++);
//        System.out.println("ate " + t.token);
        return t;
    }

    public Tokener readTo(Token token) throws InvalidTokenException {
        ArrayList<Token> tokens = new ArrayList<>();
        while (left() > 0) {
            Token t = eat();
            if (t.equals(token))
                break;

            tokens.add(t);
        }
        if (index == tokens.size())
            throw new InvalidTokenException("Excepted " + token.token);
        return new Tokener(tokens);
    }

    public ArrayList<Tokener> split(Token token) {
        ArrayList<Tokener> parts = new ArrayList<>();
        ArrayList<Token> tokens = new ArrayList<>();
        index = 0;
        while (left() > 0) {
            Token t = eat();
            if (t.equals(token)) {
                parts.add(new Tokener(tokens));
                tokens = new ArrayList<>();
            } else
                tokens.add(t);
        }
        //add last part that is not followed by delimiter
        parts.add(new Tokener(tokens));
        return parts;
    }

    @Override
    public String toString() {
        return tokens.toString();
    }

    /*public String toShortString() {
        StringBuilder sb = new StringBuilder();
        for (Token token : tokens) {
            sb.append(token.toShortString()).append('\n');
        }
        return sb.substring(0, sb.length() - 1);
    }*/

    public boolean contains(TokenType type) {
        for (int i = index; i < tokens.size(); i++) {
            Token t = tokens.get(i);
            if (t.type.equals(type))
                return true;
        }
        return false;
    }

    public boolean contains(Token token) {
        for (int i = index; i < tokens.size(); i++) {
            Token t = tokens.get(i);
            if (t.equals(token))
                return true;
        }
        return false;
    }

    public boolean isEmpty() {
        return tokens.isEmpty();
    }

    public ArrayList<Tokener> splitByFirst(Token token) {
        ArrayList<Tokener> parts = new ArrayList<>();
        ArrayList<Token> tokens = new ArrayList<>();
        index = 0;
        while (left() > 0) {
            Token t = eat();
            if (t.equals(token)) {
                parts.add(new Tokener(tokens));
                tokens = new ArrayList<>();
                while (left() > 0) {
                    tokens.add(eat());
                }
                break;
            } else
                tokens.add(t);
        }
        //add last part that is not followed by delimiter
        parts.add(new Tokener(tokens));
        return parts;
    }

    //including begin, excluding end
    public Tokener subtokener(int begin, int end) {
        ArrayList<Token> subList = new ArrayList<>(end - begin);
        for (int i = begin; i < end; i++)
            subList.add(tokens.get(i));
        return new Tokener(subList);
    }

    public ArrayList<Tokener> divideByTopOrderOperatorSkippingParentheses() {
        return null;
    }

    private int indexOfTokenSkippingParentheses(Token token) {
        int level = 0;
        index = 0;
        for (int i = 0; left() > 0; i++) {
            Token t = eat();
            if (t.token.equals("("))
                level++;
            else if (t.token.equals(")"))
                level--;

            if (level == 0 && t.equals(token))
                return i;
        }
        return -1;
    }

    public int size() {
        return tokens.size();
    }

    public String toShortString() {
        StringBuilder sb = new StringBuilder();
        for (Token token : tokens) {
            sb.append(token.token).append("\n");
        }
        return sb.substring(0, sb.length() - 1);
    }

    public void trimParentheses() throws InvalidTokenException {
        while (tokens.get(0).equals(new Token("(")) && tokens.get(tokens.size() - 1).equals(new Token(")"))) {
            tokens.remove(0);
            tokens.remove(tokens.size() - 1);
        }
    }
}
