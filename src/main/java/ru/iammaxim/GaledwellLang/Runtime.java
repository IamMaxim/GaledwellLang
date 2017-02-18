package ru.iammaxim.GaledwellLang;

import ru.iammaxim.GaledwellLang.Functions.FunctionPrint;
import ru.iammaxim.GaledwellLang.Values.Value;
import ru.iammaxim.GaledwellLang.Values.VariableStorage;

/**
 * Created by maxim on 2/12/17 at 1:33 AM.
 */
public class Runtime {
    public Stack stack = new Stack(128);
    public VariableStorage variableStorage = new VariableStorage();

    //this variable stores return value for current function
    public Value returnValueTmp = null;
    //this variable stores current operation index
    public int currentCursorPos = -1;
    //this variable stores current function length
    public int currentFunctionLength = -1;

    public Value vr1 = null, //variable register 1. Used while popping values from stack
            vr2 = null;

    public void initStandartFunctions() {
        variableStorage.setField("print".hashCode(), new FunctionPrint());
    }

    public void goToFunctionEnd() {
        currentCursorPos = currentFunctionLength - 1;
    }

/*    public byte[] getAt(int pos, int count) {
        byte[] dest = new byte[count];
        System.arraycopy(stack, pos, dest, 0, count);
        return dest;
    }

    public void setAt(int pos, byte[] data) {
        System.arraycopy(data, 0, stack, pos, data.length);
    }*/
}
