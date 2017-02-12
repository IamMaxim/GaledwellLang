package ru.iammaxim.GaledwellLang.Types;

import ru.iammaxim.GaledwellLang.Runtime;

/**
 * Created by maxim on 2/12/17 at 12:59 PM.
 */
public class TypeInt extends Type {
    public int value;

    public TypeInt(int value) {
        this.value = value;
    }

    @Override
    public String toString(Runtime runtime, int indent) {
        return String.valueOf(value);
    }

    public static boolean isValid(String value) {
        return value.matches("[0-9]*");
    }
}
