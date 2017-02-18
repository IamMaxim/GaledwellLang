package ru.iammaxim.GaledwellLang;

import ru.iammaxim.GaledwellLang.Compiler.Compiler;
import ru.iammaxim.GaledwellLang.Functions.FunctionParsed;
import ru.iammaxim.GaledwellLang.Operations.InvalidOperationException;
import ru.iammaxim.GaledwellLang.Parser.*;
import ru.iammaxim.GaledwellLang.Values.ValueFunction;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by maxim on 2/12/17 at 12:14 AM.
 */
public class Main {
    public static FileOutputStream fos1, fos2, fos3;

    static {
        try {
            fos1 = new FileOutputStream("tokens.txt");
            fos2 = new FileOutputStream("ops.txt");
            fos3 = new FileOutputStream("log.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(new File("src.txt")).useDelimiter("\\A")) {
            String src = scanner.next();
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
                funcs.add(new FunctionParsed(parsedFunc.name, parsedFunc.args, Compiler.compileFunction(parsedFunc.tokens)));
            }


            Runtime runtime = new Runtime();
            runtime.initStandartFunctions();

            for (ValueFunction f : funcs) {
                runtime.variableStorage.setField(f.name, f);
            }

            funcs.forEach(f -> {
                try {
                    fos2.write(("function: " + f.name + "\n").getBytes());
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

/*            long start = System.currentTimeMillis();
            for (int i = 0; i < 100000000; i++)*/
                ((ValueFunction)runtime.variableStorage.getField("main")).call(runtime);
//            System.out.println("elapsed time: " + (float)(System.currentTimeMillis() - start)/1000 + "sec");

            System.out.println(runtime.variableStorage.toString(runtime, 0));

        } catch (FileNotFoundException | InvalidTokenException | InvalidOperationException e) {
            e.printStackTrace();
        }
    }
}
