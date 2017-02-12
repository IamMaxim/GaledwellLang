package ru.iammaxim.GaledwellLang.Parser;

import ru.iammaxim.GaledwellLang.Functions.FunctionParsed;
import ru.iammaxim.GaledwellLang.Operations.Operation;
import ru.iammaxim.GaledwellLang.Operations.OperationAssign;
import ru.iammaxim.GaledwellLang.Operations.OperationAssignGlobal;
import ru.iammaxim.GaledwellLang.Operations.OperationCall;
import ru.iammaxim.GaledwellLang.Types.Function;
import ru.iammaxim.GaledwellLang.Types.Type;

import java.util.ArrayList;

/**
 * Created by maxim on 2/12/17 at 3:03 PM.
 */
public class FunctionBuilder {
    public ArrayList<Token> tokens;
    private int index = 0;
    private Token t; // current token

    public FunctionBuilder(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

    private Token eat() {
        if (index >= tokens.size())
            return null;
        Token t = tokens.get(index++);
        return t;
    }

    private int left() {
        return tokens.size() - index;
    }

    public ArrayList<Function> build() {
        ArrayList<Function> functions = new ArrayList<>();

        while (left() > 0) {
            t = eat();
            //check if this is function
            if (t.type == TokenType.IDENTIFIER) {
                if (left() >= 4) { // (){}
                    String name = t.token;
                    ArrayList<Token> args = new ArrayList<>();
                    ArrayList<Token> body = new ArrayList<>();

                    //functions args
                    if ((t = eat()).token.equals("(")) {
                        int level = 1; //already ate '('
                        while (level > 0) {
                            t = eat();

                            if (t.token.equals("("))
                                level++;
                            else if (t.token.equals(")"))
                                level--;

                            if (level == 0)
                                continue;

                            if (t.type != TokenType.DELIMITER)
                                args.add(t);
                        }

                        //function body
                        if ((t = eat()).token.equals("{")) {
                            level = 1; //already ate '{'
                            while (true) {
                                t = eat();

                                if (t.token.equals("{"))
                                    level++;
                                else if (t.token.equals("}"))
                                    level--;

                                if (level == 0)
                                    break;

                                //TODO: use delimiter to detect statements
                                if (t.type != TokenType.DELIMITER) {
                                    body.add(t);
                                }
                            }
                        }
                        functions.add(new FunctionBuilder_inner().buildFunction(name, args, body));
                    }
                }
            }
        }

        return functions;
    }

    private String[] tokensToStringArray(ArrayList<Token> tokens) {
        String[] strs = new String[tokens.size()];
        for (int i = 0; i < tokens.size(); i++)
            strs[i] = tokens.get(i).token;
        return strs;
    }

    private class FunctionBuilder_inner {
        private ArrayList<Token> tokens;
        private int index = 0;
        private Token t; // current token

        private Token eat() {
            if (index >= tokens.size())
                return null;
            Token t = tokens.get(index++);
            return t;
        }

        private int left() {
            return tokens.size() - index;
        }

        private Function buildFunction(String name, ArrayList<Token> args, ArrayList<Token> body) {
            ArrayList<Operation> operations = new ArrayList<>();
            tokens = body;

            while (left() > 0) {
                t = eat();

                // global-space assignation (stored in variableStorage.globalVariables)
                if (t.token.equals("global")) {
                    if (left() >= 3) { // left, =, right
                        t = eat();
                        if (t.type == TokenType.IDENTIFIER) {
                            index++; // skip '='
                            operations.add(new OperationAssignGlobal(t.token, Type.get((t = eat()).token)));
                        }
                    }
                    continue;
                }

                //assignation
                if (left() >= 3 && tokens.get(index).token.equals("=")) {
                    index++; // skip '='
                    operations.add(new OperationAssign(t.token, Type.get((t = eat()).token)));
                }

                // TODO: use delimiter to detect arguments and their count
                if (left() >= 3 && tokens.get(index).token.equals("(")) {
                    index++;
                    int level = 1;
                    String name1 = t.token;
                    ArrayList<Type> args1 = new ArrayList<>();
                    while (true) {
                        t = eat();

                        if (t.token.equals("("))
                            level++;
                        if (t.token.equals(")"))
                            level--;

                        if (level == 0)
                            break;

                        args1.add(Type.get(t.token));
                    }

                    operations.add(new OperationCall(name1, args1.toArray(new Type[0])));
                }
            }


            return new FunctionParsed(name, tokensToStringArray(args), operations);
        }
    }
}
