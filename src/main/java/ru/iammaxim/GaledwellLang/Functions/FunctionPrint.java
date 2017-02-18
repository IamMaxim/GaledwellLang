package ru.iammaxim.GaledwellLang.Functions;

import ru.iammaxim.GaledwellLang.Runtime;
import ru.iammaxim.GaledwellLang.Values.ValueFunction;
import ru.iammaxim.GaledwellLang.Values.Value;
import ru.iammaxim.GaledwellLang.Values.ValueVoid;

/**
 * Created by maxim on 2/12/17 at 12:27 PM.
 */
public class FunctionPrint extends ValueFunction {
    public FunctionPrint() {
        super("print".hashCode(), "values".hashCode());
    }

    @Override
    public void call(Runtime runtime, Value... arguments) {
        String[] newArgs = new String[arguments.length];
        for (int i = 0; i < arguments.length; i++) {
            newArgs[i] = arguments[i].toString(runtime, 0);
        }
        System.out.println(String.join(" ", newArgs));

        runtime.stack.push(new ValueVoid());
    }
}
