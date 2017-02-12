package ru.iammaxim.GaledwellLang.Types;

import ru.iammaxim.GaledwellLang.Runtime;

/**
 * Created by maxim on 2/12/17 at 10:09 AM.
 */
public abstract class Type {
    public abstract String toString(Runtime runtime);
    public Type(String value) {}
    public Type() {}
    public static Type get(String value) {
        if (TypeInt.isValid(value))
            return new TypeInt(Integer.parseInt(value));
        else if (TypeFloat.isValid(value))
            return new TypeFloat(Float.parseFloat(value));
        else if (TypeString.isValid(value))
            return new TypeString(value);
        else return new TypeReference(value);
    }
}
