package ru.iammaxim.GaledwellLang.Functions;

import ru.iammaxim.GaledwellLang.Operations.Operation;
import ru.iammaxim.GaledwellLang.Operations.OperationAssign;
import ru.iammaxim.GaledwellLang.Operations.OperationCall;
import ru.iammaxim.GaledwellLang.Runtime;
import ru.iammaxim.GaledwellLang.Types.Function;
import ru.iammaxim.GaledwellLang.Types.Type;
import ru.iammaxim.GaledwellLang.Types.TypeString;
import ru.iammaxim.GaledwellLang.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by maxim on 2/12/17 at 12:29 PM.
 */
public class FunctionParsed extends Function {
    public String body;
    public ArrayList<Operation> statements;

    public FunctionParsed(String name, String[] args, String body) {
        super(name, args);

        this.body = body.substring(1, body.length()-1).trim();
        statements = parseFunctionBody(this.body);

        System.out.println(name + " | " + Arrays.toString(args) + " | " + statements);
    }

    @Override
    public void call(Runtime runtime, Type[] args) {
        //TODO: implement args
        for (Operation statement : statements) {
            statement.run(runtime);
        }
    }

    public static ArrayList<Operation> parseFunctionBody(String src) {
        src = src.replace("\n", "");
        ArrayList<String> tokens = new ArrayList<>();
        Scanner scanner = new Scanner(src).useDelimiter(";");
        while (scanner.hasNext())
            tokens.add(scanner.next().trim());

        ArrayList<Operation> statements = new ArrayList<>();
        for (String token : tokens) {
            statements.add(parseStatement(token));
        }

        return statements;
    }

    public static Operation parseStatement(String src) {
        //check for assignment
        if (src.contains("=")) {
            int divisionIndex = src.indexOf('=');
            String left = src.substring(0, divisionIndex).trim();
            String right = src.substring(divisionIndex + 1, src.length()).trim();
            return new OperationAssign(left, right);
        }
        //check for call
        if (src.contains("(") && src.contains(")")) {
            int divisionIndex = src.indexOf("(");
            String funcName = src.substring(0, divisionIndex);
            String[] argStrs = Utils.splitByComma(src.substring(divisionIndex + 1, src.length() - 1));
            Type[] args = new Type[argStrs.length];
            for (int i = 0; i < argStrs.length; i++)
                args[i] = Type.get(argStrs[i]);
            return new OperationCall(funcName, args);
        }

        return new OperationCall("print", new TypeString(src));
    }
}
