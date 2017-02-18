package ru.iammaxim.GaledwellLang.Operations;

import ru.iammaxim.GaledwellLang.Runtime;
import ru.iammaxim.GaledwellLang.Values.Value;
import ru.iammaxim.GaledwellLang.Values.ValueObject;
import ru.iammaxim.GaledwellLang.Values.ValueReference;

/**
 * Created by maxim on 2/12/17 at 10:59 AM.
 */
public class OperationAssign extends Operation {
    @Override
    public Value run(Runtime runtime) {
        String name = ((ValueReference) runtime.stack.pop()).name;
        ValueObject parent = (ValueObject) runtime.stack.pop();
        Value value = runtime.stack.pop();
        parent.setField(name, value);
        runtime.stack.push(value);
        return value;
    }

    @Override
    public String toString(Runtime runtime, int indent) {
        return "assign";
    }
}
