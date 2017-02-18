package ru.iammaxim.GaledwellLang.Operations;

import ru.iammaxim.GaledwellLang.Runtime;
import ru.iammaxim.GaledwellLang.Values.Value;

/**
 * Created by maxim on 2/17/17 at 8:59 PM.
 */
public class OperationReturn extends Operation {
    public OperationReturn() {
    }

    @Override
    public Value run(Runtime runtime) {
//        runtime.returnValueTmp = runtime.stack.pop();
        runtime.goToFunctionEnd();
        return null;
    }

    @Override
    public String toString(Runtime runtime, int indent) {
        return "return";
    }
}
