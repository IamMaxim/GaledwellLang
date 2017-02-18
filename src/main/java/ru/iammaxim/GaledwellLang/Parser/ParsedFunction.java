package ru.iammaxim.GaledwellLang.Parser;

import ru.iammaxim.GaledwellLang.Parser.Expression.Expression;

import java.util.ArrayList;

/**
 * Created by maxim on 2/17/17 at 7:27 PM.
 */
public class ParsedFunction {
    public String name;
    public String[] args;
    public ArrayList<Expression> tokens;

    public ParsedFunction(String name, String[] args, ArrayList<Expression> tokens) {
        this.name = name;
        this.args = args;
        this.tokens = tokens;
    }
}
