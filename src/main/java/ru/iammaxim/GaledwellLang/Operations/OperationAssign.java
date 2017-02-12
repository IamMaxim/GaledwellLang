package ru.iammaxim.GaledwellLang.Operations;

import ru.iammaxim.GaledwellLang.Runtime;
import ru.iammaxim.GaledwellLang.Types.Type;
import ru.iammaxim.GaledwellLang.Types.TypeReference;

/**
 * Created by maxim on 2/12/17 at 10:59 AM.
 */
public class OperationAssign extends Operation {
    public String left;
    public Type right;

    public OperationAssign(String left, Type right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public void run(Runtime runtime) {
        if (right instanceof TypeReference)
            runtime.variableStorage.setVar(left, ((TypeReference) right).get(runtime));
        else
            runtime.variableStorage.setVar(left, right);
    }

    @Override
    public String toString(Runtime runtime) {
        return "assign: " + left + " <- " + right.toString(runtime);
    }
}
