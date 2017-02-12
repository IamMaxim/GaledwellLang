package ru.iammaxim.GaledwellLang.Functions;

import ru.iammaxim.GaledwellLang.Runtime;
import ru.iammaxim.GaledwellLang.Types.Function;
import ru.iammaxim.GaledwellLang.Types.Type;

/**
 * Created by maxim on 2/12/17 at 12:27 PM.
 */
public class FunctionPrint extends Function {
    public FunctionPrint() {
        super("print", "value");
    }

    @Override
    public void call(Runtime runtime, Type... arguments) {
        String[] newArgs = new String[arguments.length];
        for (int i = 0; i < arguments.length; i++) {
            newArgs[i] = arguments[i].toString(runtime);
        }
        System.out.println(String.join(" ", newArgs));
    }
}
