package ru.iammaxim.GaledwellLang.Operations;

import ru.iammaxim.GaledwellLang.Runtime;
import ru.iammaxim.GaledwellLang.Values.Value;
import ru.iammaxim.GaledwellLang.Values.ValueFunction;
import ru.iammaxim.GaledwellLang.Values.ValueObject;
import ru.iammaxim.GaledwellLang.Values.ValueReference;

/**
 * Created by maxim on 2/12/17 at 11:13 AM.
 */
public class OperationCall extends Operation {
    public int argsCount;

    public OperationCall(int argsCount) {
        this.argsCount = argsCount;
    }

    @Override
    public Value run(Runtime runtime) throws InvalidOperationException {
        //cleanup return value
        runtime.returnValueTmp = null;

        String name = ((ValueReference) runtime.stack.pop()).name;
        ValueObject parent = (ValueObject) runtime.stack.pop();

        Value[] newArgs = new Value[argsCount];
        for (int i = 0; i < newArgs.length; i++) {
            Value arg = runtime.stack.pop();
            if (arg instanceof ValueReference) {
                String argName = ((ValueReference) arg).name;
                ValueObject argParent = (ValueObject) runtime.stack.pop();
                newArgs[i] = argParent.getField(argName);
            } else {
                newArgs[i] = arg;
            }
        }

        int currentPosBackup = runtime.currentCursorPos;
        ((ValueFunction)parent.getField(name)).call(runtime, newArgs);
        runtime.currentCursorPos = currentPosBackup;
        return null;
    }

    @Override
    public String toString(Runtime runtime, int indent) {
        return "call";
    }
}
