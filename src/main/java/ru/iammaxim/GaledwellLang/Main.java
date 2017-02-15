package ru.iammaxim.GaledwellLang;

import ru.iammaxim.GaledwellLang.Parser.FunctionBuilder;
import ru.iammaxim.GaledwellLang.Parser.InvalidTokenException;
import ru.iammaxim.GaledwellLang.Parser.Parser;
import ru.iammaxim.GaledwellLang.Types.Function;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by maxim on 2/12/17 at 12:14 AM.
 */
public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(new File("src.txt")).useDelimiter("\\A")) {
            String src = scanner.next();
            Parser parser = new Parser();
            parser.parse(src);

//            parser.tokens.forEach(System.out::println);

            ArrayList<Function> funcs = new FunctionBuilder(parser.tokens).build();

            Runtime runtime = new Runtime();
            runtime.initStandartFunctions();

            for (Function f : funcs) {
                runtime.variableStorage.setVar(f.name, f);
            }

/*            long start = System.currentTimeMillis();
            for (int i = 0; i < 100000000; i++)*/
                ((Function)runtime.variableStorage.getVar("main")).call(runtime);
//            System.out.println("elapsed time: " + (float)(System.currentTimeMillis() - start)/1000 + "sec");

            System.out.println(runtime.variableStorage.toString(runtime, 0));

        } catch (FileNotFoundException | InvalidTokenException e) {
            e.printStackTrace();
        }
    }
}
