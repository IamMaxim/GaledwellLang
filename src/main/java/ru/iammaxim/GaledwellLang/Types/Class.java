package ru.iammaxim.GaledwellLang.Types;

import ru.iammaxim.GaledwellLang.Runtime;

import java.util.HashMap;

/**
 * Created by maxim on 2/12/17 at 10:18 AM.
 */
public class Class extends Type {
    private HashMap<String, Type> fields = new HashMap<>();

    public Class(String value) {
        super(value);
    }

    public Class() {}

    public void setField(String name, Type value) {
        if (name.contains(".")) {
            int divisionIndex = name.indexOf(".");
            String fieldName = name.substring(0, divisionIndex);
            String innerFieldName = name.substring(divisionIndex + 1, name.length());
            System.out.println("setting outer field: " + fieldName + " -> " + innerFieldName);
            ((Class) fields.get(fieldName)).setField(innerFieldName, value);
        } else
            fields.put(name, value);
    }

    public Type getField(String name) {
        if (name.contains(".")) {
            int divisionIndex = name.indexOf(".");
            String fieldName = name.substring(0, divisionIndex);
            String innerFieldName = name.substring(divisionIndex + 1, name.length());
            System.out.println("returning outer field: " + fieldName + " -> " + innerFieldName);
            return ((Class) fields.get(fieldName)).getField(innerFieldName);
        } else
            return fields.get(name);
    }

    @Override
    public String toString(Runtime runtime) {
        return "class";
    }
}
