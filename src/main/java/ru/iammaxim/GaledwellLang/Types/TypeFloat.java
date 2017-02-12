package ru.iammaxim.GaledwellLang.Types;

import ru.iammaxim.GaledwellLang.Runtime;

/**
 * Created by maxim on 2/12/17 at 12:59 PM.
 */
public class TypeFloat extends Type {
    public float value;

    public TypeFloat(float value) {
        this.value = value;
    }

    @Override
    public String toString(Runtime runtime) {
        return String.valueOf(value);
    }

    public static boolean isValid(String value) {
        return value.endsWith("f") && value.matches("[0-9]*.[0-9]*f");
    }
}
