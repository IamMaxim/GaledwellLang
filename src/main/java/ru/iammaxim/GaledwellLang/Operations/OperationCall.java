package ru.iammaxim.GaledwellLang.Operations;

import ru.iammaxim.GaledwellLang.Runtime;
import ru.iammaxim.GaledwellLang.Types.Function;
import ru.iammaxim.GaledwellLang.Types.Type;

/**
 * Created by maxim on 2/12/17 at 11:13 AM.
 */
public class OperationCall extends Operation {
    public String name;
    public Type[] args;

    public OperationCall(String name, Type... args) {
        this.name = name;
        this.args = args;
    }

    @Override
    public void run(Runtime runtime) {
        ((Function)runtime.variableStorage.getVar(name)).call(runtime, args);
    }

    @Override
    public String toString() {
        return "call(): " + name;
    }
}
