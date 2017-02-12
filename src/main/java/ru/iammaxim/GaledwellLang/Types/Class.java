package ru.iammaxim.GaledwellLang.Types;

import ru.iammaxim.GaledwellLang.Runtime;
import ru.iammaxim.GaledwellLang.Utils;

import java.util.HashMap;
import java.util.StringJoiner;

/**
 * Created by maxim on 2/12/17 at 10:18 AM.
 */
public class Class extends Type {
    private HashMap<String, Type> fields = new HashMap<>();

    public Class(String value) {
    }

    public Class() {}

    public void setField(String name, Type value) {
        if (name.contains(".")) {
            int divisionIndex = name.indexOf(".");
            String fieldName = name.substring(0, divisionIndex);
            String innerFieldName = name.substring(divisionIndex + 1, name.length());
            ((Class) fields.get(fieldName)).setField(innerFieldName, value);
        } else {
            fields.put(name, value);
        }
    }

    public Type getField(String name) {
        if (name.contains(".")) {
            int divisionIndex = name.indexOf(".");
            String fieldName = name.substring(0, divisionIndex);
            String innerFieldName = name.substring(divisionIndex + 1, name.length());
            return ((Class) fields.get(fieldName)).getField(innerFieldName);
        } else {
            return fields.get(name);
        }
    }

    @Override
    public String toString(Runtime runtime, int indent) {
        StringJoiner sj = new StringJoiner(",\n" + Utils.indent(indent + 3));
        fields.forEach((name, value) -> sj.add("\"" + name + "\"" + ": " + value.toString(runtime, indent + 3)));
        return "\"class\":\n" +
                Utils.indent(indent + 1) + "{\n" +
                Utils.indent(indent + 2) + sj.toString() + "\n" +
                Utils.indent(indent + 1) + "}";
    }

    public static boolean isValid(String value) {
        return value.equals("class");
    }
}
