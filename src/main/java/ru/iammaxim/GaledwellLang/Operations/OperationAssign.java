package ru.iammaxim.GaledwellLang.Operations;

import ru.iammaxim.GaledwellLang.Runtime;
import ru.iammaxim.GaledwellLang.Types.Type;

/**
 * Created by maxim on 2/12/17 at 10:59 AM.
 */
public class OperationAssign extends Operation {
    public String left, right;

    public OperationAssign(String left, String right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public void run(Runtime runtime) {
        runtime.variableStorage.setVar(left, Type.get(right));
    }

    @Override
    public String toString() {
        return "assign: " + left + " <- " + right;
    }
}
