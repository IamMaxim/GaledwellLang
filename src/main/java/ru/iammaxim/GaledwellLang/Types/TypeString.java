package ru.iammaxim.GaledwellLang.Types;

import ru.iammaxim.GaledwellLang.Runtime;

/**
 * Created by maxim on 2/12/17 at 10:39 AM.
 */
public class TypeString extends Type {
    public String string;

    public TypeString(String value) {
        super(value);
        string = value;
    }

    public static boolean isValid(String value) {
        return value.startsWith("\"") && value.endsWith("\"");
    }

    @Override
    public String toString(Runtime runtime) {
        return string;
    }
}
