package ru.iammaxim.GaledwellLang.Operations;

import ru.iammaxim.GaledwellLang.Runtime;
import ru.iammaxim.GaledwellLang.Values.Value;

/**
 * Created by maxim on 2/17/17 at 9:35 PM.
 */
public class OperationPop extends Operation {
    @Override
    public Value run(Runtime runtime) {
        runtime.vr1 = runtime.stack.pop();
        return runtime.vr1;
    }

    @Override
    public String toString(Runtime runtime, int indent) {
        return "pop";
    }
}
