package ru.iammaxim.GaledwellLang.Operations;

import ru.iammaxim.GaledwellLang.Runtime;
import ru.iammaxim.GaledwellLang.Values.Value;

/**
 * Created by maxim on 2/12/17 at 10:56 AM.
 */
public abstract class Operation {
    public abstract Value run(Runtime runtime) throws InvalidOperationException;
    public abstract String toString(Runtime runtime, int indent);
}
