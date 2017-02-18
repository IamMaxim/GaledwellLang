package ru.iammaxim.GaledwellLang.Values;

import ru.iammaxim.GaledwellLang.Operations.InvalidOperationException;
import ru.iammaxim.GaledwellLang.Runtime;
import ru.iammaxim.GaledwellLang.Utils;

import java.util.HashMap;
import java.util.StringJoiner;

/**
 * Created by maxim on 2/12/17 at 10:18 AM.
 */
public class ValueObject extends Value {
    protected HashMap<String, Value> fields = new HashMap<>();

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

    public void setField(String name, Value value) {
        /*if (name.contains(".")) {
            int divisionIndex = name.indexOf(".");
            String fieldName = name.substring(0, divisionIndex);
            String innerFieldName = name.substring(divisionIndex + 1, name.length());
            ((ValueObject) fields.get(fieldName)).setField(innerFieldName, value);
        } else {
            fields.put(name, value);
        }*/

        fields.put(name, value);
    }

    public Value getField(String name) {
        /*if (name.contains(".")) {
            int divisionIndex = name.indexOf(".");
            String fieldName = name.substring(0, divisionIndex);
            String innerFieldName = name.substring(divisionIndex + 1, name.length());
            return ((ValueObject) fields.get(fieldName)).getField(innerFieldName);
        } else {
            return fields.get(name);
        }*/
        return fields.get(name);
    }

    public void removeField(String name) {
        fields.remove(name);
    }

    @Override
    public String toString(Runtime runtime, int indent) {
        StringJoiner sj = new StringJoiner(",\n" + Utils.indent(indent + 3));
        fields.forEach((name, value) -> sj.add("\"" + name + "\"" + ": " + value.toString(runtime, indent + 3)));
        return "\"class\":\n" +
                Utils.indent(indent + 1) + "{\n" +
                Utils.indent(indent + 2) + sj.toString() + "\n" +
                Utils.indent(indent + 1) + "}";
//        return "object: " + fields.toString();
    }

    public static boolean isValid(String value) {
        return value.equals("class");
    }
}
