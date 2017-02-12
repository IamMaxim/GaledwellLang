package ru.iammaxim.GaledwellLang.Types;

import ru.iammaxim.GaledwellLang.Runtime;
import ru.iammaxim.GaledwellLang.Utils;

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
    public String toString(Runtime runtime, int indent) {
        return "\"function\":\n" +
                Utils.indent(indent + 1) + "{\n" +
                Utils.indent(indent + 2) + "\"name\": " + name + ",\n" +
                Utils.indent(indent + 2) + "\"args\": " + Arrays.toString(args) + "\n" +
                Utils.indent(indent + 1) + "}";
    }

    public abstract void call(Runtime runtime, Type... args);
}
