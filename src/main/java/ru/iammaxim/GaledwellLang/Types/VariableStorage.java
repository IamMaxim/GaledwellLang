package ru.iammaxim.GaledwellLang.Types;

import java.util.HashMap;
import java.util.StringJoiner;

/**
 * Created by maxim on 1/22/17 at 3:38 PM.
 */
public class VariableStorage extends Class {
    private HashMap<String, Type> globalVariables = new HashMap<>();
    private HashMap<String, Type> variables = new HashMap<>();

    public VariableStorage(String value) {
        super(value);
    }

    public VariableStorage() {}

    public void setGlobalVar(String name, Type value) {
        globalVariables.put(name, value);
    }

    public Type getGlobalVar(String name) {
        return globalVariables.get(name);
    }

    public void setVar(String name, Type value) {
        variables.put(name, value);
    }

    public Type getVar(String name) {
        Type value = variables.get(name);
        if (value == null) value = globalVariables.get(name);
        return value;
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(", ");
        variables.forEach((name, value) -> sj.add(name + ": " + value));
        return sj.toString();
    }
}
