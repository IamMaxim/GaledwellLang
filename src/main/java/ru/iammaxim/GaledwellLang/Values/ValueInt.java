package ru.iammaxim.GaledwellLang.Values;

import ru.iammaxim.GaledwellLang.Operations.InvalidOperationException;
import ru.iammaxim.GaledwellLang.Runtime;

/**
 * Created by maxim on 2/12/17 at 12:59 PM.
 */
public class ValueInt extends Value {
    public int value;

    public ValueInt(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "int: " + value;
    }

    @Override
    public String toString(Runtime runtime, int indent) {
        return String.valueOf(value);
    }

    @Override
    public Value operatorPlus(Value right) throws InvalidOperationException {
        return new ValueInt(value + ((ValueInt)right).value);
    }

    @Override
    public Value operatorMinus(Value right) throws InvalidOperationException {
        return new ValueInt(value - ((ValueInt)right).value);
    }

    @Override
    public Value operatorMultiply(Value right) throws InvalidOperationException {
        return new ValueInt(value * ((ValueInt)right).value);
    }

    @Override
    public Value operatorDivide(Value right) throws InvalidOperationException {
        return new ValueInt(value / ((ValueInt)right).value);
    }

    @Override
    public Value operatorEquals(Value right) throws InvalidOperationException {
        return new ValueBoolean(value == ((ValueInt)right).value);
    }

    public static boolean isValid(String value) {
        return value.matches("[0-9]*");
    }
}
