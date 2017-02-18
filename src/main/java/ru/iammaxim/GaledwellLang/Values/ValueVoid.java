package ru.iammaxim.GaledwellLang.Values;

import ru.iammaxim.GaledwellLang.Operations.InvalidOperationException;
import ru.iammaxim.GaledwellLang.Runtime;

/**
 * Created by maxim on 2/18/17 at 10:30 PM.
 */
public class ValueVoid extends Value {
    @Override
    public String toString(Runtime runtime, int indent) {
        return "void";
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
        return "void";
    }

    @Override
    public Value operatorEquals(Value right) throws InvalidOperationException {
        throw new InvalidOperationException("Not implemented");
    }
}
