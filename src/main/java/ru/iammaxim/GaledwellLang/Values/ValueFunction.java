package ru.iammaxim.GaledwellLang.Values;

import ru.iammaxim.GaledwellLang.Operations.InvalidOperationException;
import ru.iammaxim.GaledwellLang.Runtime;
import ru.iammaxim.GaledwellLang.Utils;

import java.util.Arrays;

/**
 * Created by maxim on 2/12/17 at 1:16 AM.
 */
public abstract class ValueFunction extends Value {
    public int id;
    public int[] args;

    public ValueFunction(int id, int... args) {
        this.id = id;
        this.args = args;
    }

    @Override
    public String toString(Runtime runtime, int indent) {
        return "{\n" +
                Utils.indent(indent + 1) + "\"function\":\n" +
                Utils.indent(indent + 2) + "{\n" +
                Utils.indent(indent + 3) + "\"path\": " + id + ",\n" +
                Utils.indent(indent + 3) + "\"args\": " + Arrays.toString(args) + "\n" +
                Utils.indent(indent + 2) + "}\n" +
                Utils.indent(indent + 1) + "}";
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

    public abstract void call(Runtime runtime, Value... args) throws InvalidOperationException;
}
