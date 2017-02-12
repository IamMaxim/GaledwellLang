package ru.iammaxim.GaledwellLang;

import ru.iammaxim.GaledwellLang.Types.Class;
import ru.iammaxim.GaledwellLang.Types.Type;

import java.util.HashMap;
import java.util.StringJoiner;

/**
 * Created by maxim on 1/22/17 at 3:38 PM.
 */
public class VariableStorage extends Class {
    private HashMap<String, Type> variables = new HashMap<>();

    public VariableStorage(String value) {
        super(value);
    }

    public VariableStorage() {}

    public void setVar(String name, Type value) {
        variables.put(name, value);
    }

    public Type getVar(String name) {
        return variables.get(name);
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(", ");
        variables.forEach((name, value) -> sj.add(name + ": " + value));
        return sj.toString();
    }
}
