package ru.iammaxim.GaledwellLang.Operations;

import ru.iammaxim.GaledwellLang.Runtime;
import ru.iammaxim.GaledwellLang.Types.Type;

/**
 * Created by maxim on 2/12/17 at 4:35 PM.
 */
public class OperationAssignGlobal extends Operation {
    public String left;
    public Type right;

    public OperationAssignGlobal(String left, Type right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public void run(Runtime runtime) {
        runtime.variableStorage.setGlobalVar(left, right);
    }

    @Override
    public String toString(Runtime runtime) {
        return "assignGlobal: " + left + " <- " + right.toString(runtime);
    }
}
