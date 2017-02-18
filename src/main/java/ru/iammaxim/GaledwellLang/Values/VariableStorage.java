package ru.iammaxim.GaledwellLang.Values;

import ru.iammaxim.GaledwellLang.Runtime;

import java.util.HashMap;

/**
 * Created by maxim on 1/22/17 at 3:38 PM.
 */
public class VariableStorage extends ValueObject {
    private HashMap<Integer, Value> globalVariables = new HashMap<>();

    public VariableStorage(String value) {
        super(value);
    }

    public VariableStorage() {}

    public void setGlobalVar(int id, Value value) {
        globalVariables.put(id, value);
    }

    public Value getGlobalVar(int id) {
        return globalVariables.get(id);
    }

    @Override
    public String toString(Runtime runtime, int indent) {
        /*StringJoiner sj1 = new StringJoiner(",\n" + Utils.indent(indent + 2));
        StringJoiner sj2 = new StringJoiner(",\n" + Utils.indent(indent + 2));
        fields.forEach((path, value) -> sj1.add("\"" + path + "\"" + ": " + value.toString(runtime, indent + 2)));
        globalVariables.forEach((path, value) -> sj2.add("\"" + path + "\"" + ": " + value.toString(runtime, indent + 2)));
        return
                Utils.indent(indent) + "\"VariableStorage\": {\n" +
                Utils.indent(indent + 1) + "\"variables\": {" + "\n" +
                Utils.indent(indent + 2) + sj1.toString() + "\n" +
                Utils.indent(indent + 1) + "},\n" +
                Utils.indent(indent + 1) + "\"globalVariables\": {\n" +
                Utils.indent(indent + 2) + sj2.toString() + "\n" +
                Utils.indent(indent + 1) + "}\n" +
                Utils.indent(indent) + "}";*/

        return "variableStorage";
    }
}
