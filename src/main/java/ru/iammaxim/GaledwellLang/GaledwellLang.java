package ru.iammaxim.GaledwellLang;

import ru.iammaxim.GaledwellLang.Compiler.Compiler;
import ru.iammaxim.GaledwellLang.Functions.FunctionParsed;
import ru.iammaxim.GaledwellLang.Parser.*;
import ru.iammaxim.GaledwellLang.Values.ValueFunction;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by maxim on 2/18/17 at 11:30 PM.
 */
public class GaledwellLang {
    public static FileOutputStream fos1;
    public static FileOutputStream fos2;
    public static FileOutputStream fos3;

    static {
        try {
            GaledwellLang.fos1 = new FileOutputStream("tokens.txt");
            GaledwellLang.fos2 = new FileOutputStream("ops.txt");
            GaledwellLang.fos3 = new FileOutputStream("log.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void loadSrcInto(String src, Runtime runtime) throws InvalidTokenException {
        ArrayList<Token> tokens = Parser.parse(src);

        tokens.forEach(t -> {
            try {
                fos1.write((t.toString() + "\n").getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        ArrayList<ParsedFunction> parsedFuncs = new FunctionParser(tokens).build();
        ArrayList<ValueFunction> funcs = new ArrayList<>(parsedFuncs.size());
        for (ParsedFunction parsedFunc : parsedFuncs) {
            funcs.add(new FunctionParsed(parsedFunc.id, parsedFunc.args, Compiler.compileFunction(parsedFunc.tokens)));
        }

        for (ValueFunction f : funcs) {
            runtime.variableStorage.setField(f.id, f);
        }

        funcs.forEach(f -> {
            try {
                fos2.write(("function: " + f.id + "\n").getBytes());
                ((FunctionParsed)f).operations.forEach(o -> {
                    try {
                        fos2.write((o.toString(runtime, 0) + "\n").getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        System.gc();
    }
}
