package ru.iammaxim.GaledwellLang.Operations;

import ru.iammaxim.GaledwellLang.Runtime;
import ru.iammaxim.GaledwellLang.Values.Value;
import ru.iammaxim.GaledwellLang.Values.ValueObject;

/**
 * Created by maxim on 2/18/17 at 4:19 PM.
 */
public class OperationGetAndPush extends Operation {
    public String name;

    public OperationGetAndPush(String name) {
        this.name = name;
    }

    @Override
    public Value run(Runtime runtime) throws InvalidOperationException {
        ValueObject obj = (ValueObject) runtime.stack.pop();
        runtime.stack.push(obj.getField(name));
        return null;
    }

    @Override
    public String toString(Runtime runtime, int indent) {
        return "getAndPush: " + name;
    }
}
