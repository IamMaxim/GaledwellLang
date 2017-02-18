package ru.iammaxim.GaledwellLang.Values;

import ru.iammaxim.GaledwellLang.Operations.InvalidOperationException;
import ru.iammaxim.GaledwellLang.Runtime;

/**
 * Created by maxim on 2/17/17 at 10:16 PM.
 */
public class ValueBoolean extends Value {
    public boolean value;

    public ValueBoolean(boolean value) {
        this.value = value;
    }

    @Override
    public String toString(Runtime runtime, int indent) {
        return "bool: " + value;
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
    public String toString() {
        return "bool: " + value;
    }

    @Override
    public Value operatorEquals(Value right) throws InvalidOperationException {
        return new ValueBoolean(value == ((ValueBoolean)right).value);
    }
}
