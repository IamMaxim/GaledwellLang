package ru.iammaxim.GaledwellLang.Types;

import ru.iammaxim.GaledwellLang.Runtime;
import ru.iammaxim.GaledwellLang.Utils;

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
        if (name.contains(".")) {
            int divisionIndex = name.indexOf(".");
            String fieldName = name.substring(0, divisionIndex);
            String innerFieldName = name.substring(divisionIndex + 1, name.length());
            ((Class) variables.get(fieldName)).setField(innerFieldName, value);
        } else {
            variables.put(name, value);
        }
    }

    public Type getVar(String name) {
        Type value;
        if (name.contains(".")) {
            int divisionIndex = name.indexOf(".");
            String fieldName = name.substring(0, divisionIndex);
            String innerFieldName = name.substring(divisionIndex + 1, name.length());
            value = ((Class) variables.get(fieldName)).getField(innerFieldName);
        } else {
            value = variables.get(name);
        }
        if (value == null) value = globalVariables.get(name);
        return value;
    }

    @Override
    public String toString(Runtime runtime, int indent) {
        StringJoiner sj1 = new StringJoiner(",\n" + Utils.indent(indent + 2));
        StringJoiner sj2 = new StringJoiner(",\n" + Utils.indent(indent + 2));
        variables.forEach((name, value) -> sj1.add("\"" + name + "\"" + ": " + value.toString(runtime, indent + 2)));
        globalVariables.forEach((name, value) -> sj2.add("\"" + name + "\"" + ": " + value.toString(runtime, indent + 2)));
        return
                Utils.indent(indent) + "\"VariableStorage\": {\n" +
                Utils.indent(indent + 1) + "\"variables\": {" + "\n" +
                Utils.indent(indent + 2) + sj1.toString() + "\n" +
                Utils.indent(indent + 1) + "},\n" +
                Utils.indent(indent + 1) + "\"globalVariables\": {\n" +
                Utils.indent(indent + 2) + sj2.toString() + "\n" +
                Utils.indent(indent + 1) + "}\n" +
                Utils.indent(indent) + "}";
    }
}
