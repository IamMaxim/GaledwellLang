package ru.iammaxim.GaledwellLang.Operations;

import ru.iammaxim.GaledwellLang.Runtime;
import ru.iammaxim.GaledwellLang.Values.Value;

/**
 * Created by maxim on 2/17/17 at 11:11 PM.
 */
public class OperationDiv extends Operation {
    @Override
    public Value run(Runtime runtime) throws InvalidOperationException {
        Value first = runtime.stack.pop();
        Value second = runtime.stack.pop();
        Value result = first.operatorPlus(second);
        runtime.stack.push(result);

        return result;
    }

    @Override
    public String toString(Runtime runtime, int indent) {
        return "div";
    }
}
