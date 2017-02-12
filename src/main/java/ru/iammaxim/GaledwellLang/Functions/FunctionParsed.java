package ru.iammaxim.GaledwellLang.Functions;

import ru.iammaxim.GaledwellLang.Operations.Operation;
import ru.iammaxim.GaledwellLang.Runtime;
import ru.iammaxim.GaledwellLang.Types.Function;
import ru.iammaxim.GaledwellLang.Types.Type;

import java.util.ArrayList;

/**
 * Created by maxim on 2/12/17 at 12:29 PM.
 */
public class FunctionParsed extends Function {
    public ArrayList<Operation> operations;

    public FunctionParsed(String name, String[] args, ArrayList<Operation> operations) {
        super(name, args);
        this.operations = operations;
    }

    @Override
    public void call(Runtime runtime, Type... arguments) {
        for (int i = 0; i < arguments.length; i++)
            runtime.variableStorage.setVar(args[i], arguments[i]);
        for (Operation statement : operations) {
            statement.run(runtime);
        }
    }
}
