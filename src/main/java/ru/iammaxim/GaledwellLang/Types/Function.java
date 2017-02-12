package ru.iammaxim.GaledwellLang.Types;

import ru.iammaxim.GaledwellLang.Runtime;

import java.util.Arrays;

/**
 * Created by maxim on 2/12/17 at 1:16 AM.
 */
public abstract class Function extends Type {
    public String name;
    public String[] args;

    public Function(String name, String... args) {
        this.name = name;
        this.args = args;
    }

    @Override
    public String toString(Runtime runtime) {
        return "function(): " + name + " | " + Arrays.toString(args);
    }

    public abstract void call(Runtime runtime, Type... args);
}
