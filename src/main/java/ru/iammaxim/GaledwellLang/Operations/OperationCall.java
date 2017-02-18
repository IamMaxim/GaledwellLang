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

        int id = ((ValueReference) runtime.stack.pop()).id;
        ValueObject parent = (ValueObject) runtime.stack.pop();

        Value[] newArgs = new Value[argsCount];
        for (int i = 0; i < newArgs.length; i++) {
            Value arg = runtime.stack.pop();
            if (arg instanceof ValueReference) {
                int argID = ((ValueReference) arg).id;
                ValueObject argParent = (ValueObject) runtime.stack.pop();
                newArgs[i] = argParent.getField(argID);
            } else {
                newArgs[i] = arg;
            }
        }

        int currentPosBackup = runtime.currentCursorPos;
        ((ValueFunction)parent.getField(id)).call(runtime, newArgs);
        runtime.currentCursorPos = currentPosBackup;
        return null;
    }

    @Override
    public String toString(Runtime runtime, int indent) {
        return "call";
    }
}
