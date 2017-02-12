package ru.iammaxim.GaledwellLang.Types;

import ru.iammaxim.GaledwellLang.Runtime;

/**
 * Created by maxim on 2/12/17 at 1:13 PM.
 */
public class TypeReference extends Type {
    public String name;

    public TypeReference(String name) {
        this.name = name;
    }

    @Override
    public String toString(Runtime runtime) {
        Type value = get(runtime);
        if (value == null)
            return name + " (null)";
        return value.toString(runtime);
    }

    public Type get(Runtime runtime) {
        return runtime.variableStorage.getVar(name);
    }
}
