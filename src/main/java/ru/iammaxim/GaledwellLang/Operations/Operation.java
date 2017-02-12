package ru.iammaxim.GaledwellLang.Operations;

import ru.iammaxim.GaledwellLang.Runtime;

/**
 * Created by maxim on 2/12/17 at 10:56 AM.
 */
public abstract class Operation {
    public abstract void run(Runtime runtime);
    public abstract String toString(Runtime runtime);
}
