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
    public void call(Runtime runtime, Type[] args) {
        String[] newArgs = new String[args.length];
        for (int i = 0; i < args.length; i++) {
            System.out.println("to print: " + args[i].toString(runtime));
            newArgs[i] = args[i].toString(runtime);
        }
        System.out.println(String.join(" ", newArgs));
    }
}
