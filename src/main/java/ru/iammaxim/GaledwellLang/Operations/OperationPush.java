package ru.iammaxim.GaledwellLang.Operations;

import ru.iammaxim.GaledwellLang.Runtime;
import ru.iammaxim.GaledwellLang.Values.Value;

/**
 * Created by maxim on 2/17/17 at 9:32 PM.
 */
public class OperationPush extends Operation {
    public Value value;

    public OperationPush(Value value) {
        this.value = value;
    }

    @Override
    public Value run(Runtime runtime) {
        runtime.stack.push(value);
        return value;
    }

    @Override
    public String toString(Runtime runtime, int indent) {
        return "push: " + value.toString(runtime, 0);
    }
}
