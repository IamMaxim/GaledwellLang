package ru.iammaxim.GaledwellLang.Values;

import ru.iammaxim.GaledwellLang.Operations.InvalidOperationException;
import ru.iammaxim.GaledwellLang.Runtime;

import java.util.HashMap;

/**
 * Created by maxim on 2/12/17 at 10:18 AM.
 */
public class ValueObject extends Value {
    protected HashMap<Integer, Value> fields = new HashMap<>();

    public ValueObject(String value) {
    }

    public ValueObject() {}

    @Override
    public String toString() {
        return "value: " + this.toString(null, 0);
    }

    @Override
    public Value operatorPlus(Value right) throws InvalidOperationException {
        throw new InvalidOperationException("Not implemented");
    }

    @Override
    public Value operatorMinus(Value right) throws InvalidOperationException {
        throw new InvalidOperationException("Not implemented");
    }

    @Override
    public Value operatorMultiply(Value right) throws InvalidOperationException {
        throw new InvalidOperationException("Not implemented");
    }

    @Override
    public Value operatorDivide(Value right) throws InvalidOperationException {
        throw new InvalidOperationException("Not implemented");
    }

    @Override
    public Value operatorEquals(Value right) throws InvalidOperationException {
        throw new InvalidOperationException("Not implemented");
    }

    public void setField(int id, Value value) {
        /*if (path.contains(".")) {
            int divisionIndex = path.indexOf(".");
            String fieldName = path.substring(0, divisionIndex);
            String innerFieldName = path.substring(divisionIndex + 1, path.length());
            ((ValueObject) fields.get(fieldName)).setField(innerFieldName, value);
        } else {
            fields.put(path, value);
        }*/

        fields.put(id, value);
    }

    public Value getField(int id) {
        return fields.get(id);
    }

    public void removeField(int id) {
        fields.remove(id);
    }

    @Override
    public String toString(Runtime runtime, int indent) {
/*        StringJoiner sj = new StringJoiner(",\n" + Utils.indent(indent + 3));
        fields.forEach((path, value) -> sj.add("\"" + path + "\"" + ": " + value.toString(runtime, indent + 3)));
        return "\"object\":\n" +
                Utils.indent(indent + 1) + "{\n" +
                Utils.indent(indent + 2) + sj.toString() + "\n" +
                Utils.indent(indent + 1) + "}";*/

        return "object";
    }

    public static boolean isValid(String value) {
        return value.equals("class");
    }
}
