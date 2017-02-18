package ru.iammaxim.GaledwellLang;

import ru.iammaxim.GaledwellLang.Values.Value;

import java.util.Arrays;

/**
 * Created by maxim on 2/17/17 at 7:03 PM.
 */
public class Stack {
    private Value[] stack;
    private int cursor = -1;

    public Stack(int size) {
        stack = new Value[size];
    }

    public void push(Value o) {
//        System.out.println("pushing " + o.toString(null, 0).replace("\n", " "));
        stack[++cursor] = o;
    }

    public Value pop() {
//        System.out.println("popping");
        Value v = stack[cursor];
        stack[cursor] = null;
        cursor--;
        return v;
    }

    public Value get() {
        return stack[cursor];
    }

    @Override
    public String toString() {
        return Arrays.toString(stack).replace("\n", " ");
    }
}
