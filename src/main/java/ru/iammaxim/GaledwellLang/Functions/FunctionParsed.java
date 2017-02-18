package ru.iammaxim.GaledwellLang.Functions;

import ru.iammaxim.GaledwellLang.Operations.InvalidOperationException;
import ru.iammaxim.GaledwellLang.Operations.Operation;
import ru.iammaxim.GaledwellLang.Runtime;
import ru.iammaxim.GaledwellLang.Values.ValueFunction;
import ru.iammaxim.GaledwellLang.Values.Value;

import java.util.ArrayList;

/**
 * Created by maxim on 2/12/17 at 12:29 PM.
 */
public class FunctionParsed extends ValueFunction {
    public ArrayList<Operation> operations;

    public FunctionParsed(String name, String[] args, ArrayList<Operation> operations) {
        super(name, args);
        this.operations = operations;
    }

    @Override
    public void call(Runtime runtime, Value... arguments) throws InvalidOperationException {
        //add args
        for (int i = 0; i < arguments.length; i++)
            runtime.variableStorage.setField(args[i], arguments[i]);

        //run program
        //iterate over all operators
        runtime.currentCursorPos = 0;
        int size = operations.size();
        while (runtime.currentCursorPos < size) {
            Operation op = operations.get(runtime.currentCursorPos);
            op.run(runtime);
            runtime.currentCursorPos++;
        }

        //remove args
        for (int i = 0; i < arguments.length; i++) {
            runtime.variableStorage.removeField(args[i]);
        }
    }
}
