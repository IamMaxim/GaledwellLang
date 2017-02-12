package ru.iammaxim.GaledwellLang;

import ru.iammaxim.GaledwellLang.Functions.FunctionPrint;
import ru.iammaxim.GaledwellLang.Types.VariableStorage;

/**
 * Created by maxim on 2/12/17 at 1:33 AM.
 */
public class Runtime {
    private byte[] stack = new byte[4096];
    public VariableStorage variableStorage = new VariableStorage();

    public void initStandartFunctions() {
        variableStorage.setVar("print", new FunctionPrint());
    }

    public byte[] getAt(int pos, int count) {
        byte[] dest = new byte[count];
        System.arraycopy(stack, pos, dest, 0, count);
        return dest;
    }

    public void setAt(int pos, byte[] data) {
        System.arraycopy(data, 0, stack, pos, data.length);
    }
}
