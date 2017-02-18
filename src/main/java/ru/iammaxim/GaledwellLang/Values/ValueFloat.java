package ru.iammaxim.GaledwellLang.Values;

import ru.iammaxim.GaledwellLang.Runtime;

/**
 * Created by maxim on 2/12/17 at 12:59 PM.
 */
public class ValueFloat extends Value {
    public float value;

    public ValueFloat(float value) {
        this.value = value;
    }

    @Override
    public String toString(Runtime runtime, int indent) {
        return String.valueOf(value);
    }

    @Override
    public Value operatorPlus(Value right) {
        return new ValueFloat(value + ((ValueFloat)right).value);
    }

    @Override
    public Value operatorMinus(Value right) {
        return new ValueFloat(value + ((ValueFloat)right).value);
    }

    @Override
    public String toString() {
        return "float: " + value;
    }

    @Override
    public Value operatorMultiply(Value right) {
        return new ValueFloat(value + ((ValueFloat)right).value);
    }

    @Override
    public Value operatorDivide(Value right) {
        return new ValueFloat(value + ((ValueFloat)right).value);
    }

    @Override
    public Value operatorEquals(Value right) {
        return new ValueBoolean(value == ((ValueFloat)right).value);
    }

    public static boolean isValid(String value) {
        return value.endsWith("f") && value.matches("[0-9]*.[0-9]*f");
    }
}
