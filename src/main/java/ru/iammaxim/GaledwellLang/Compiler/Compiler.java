package ru.iammaxim.GaledwellLang.Compiler;

import ru.iammaxim.GaledwellLang.Main;
import ru.iammaxim.GaledwellLang.Operations.*;
import ru.iammaxim.GaledwellLang.Parser.Expression.*;
import ru.iammaxim.GaledwellLang.Parser.TokenType;
import ru.iammaxim.GaledwellLang.Values.ValueReference;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by maxim on 2/17/17 at 7:24 PM.
 */
public class Compiler {
    private ArrayList<Expression> exps;
    private ArrayList<Operation> operations = new ArrayList<>();

    private Compiler(ArrayList<Expression> exps) {
        this.exps = exps;
    }

    public static ArrayList<Operation> compileFunction(ArrayList<Expression> tokens) {
        Compiler compiler = new Compiler(tokens);
        compiler._compileFunction();
        return compiler.operations;
    }

    private void _compileFunction() {
        for (int i = 0; i < exps.size(); i++) {
            Expression exp = exps.get(i);

            if (exp instanceof ExpressionFunctionCall) {
                ExpressionFunctionCall call = (ExpressionFunctionCall) exp;

                for (int j = call.args.size() - 1; j >= 0; j--) {
                    Expression arg = call.args.get(j);
                    compileExpression(arg);
                }

                compilePathToVar(call.functionName);
                operations.add(new OperationCall(call.args.size()));
            } else if (exp instanceof ExpressionReturn) {
                compileExpression(((ExpressionReturn) exp).returnExp);
//                operations.add(new OperationReturn());
            } else {
                compileExpression(exp);
            }
        }
    }

    private void compileExpression(Expression exp) {
        try {
            Main.fos3.write(("compiling: " + exp.toString() + "\n").getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (exp instanceof ExpressionValue) {
            ExpressionValue val = (ExpressionValue) exp;
            if (val.value instanceof ValueReference) {
                compilePathToVar(((ValueReference) val.value).name);
            } else
                operations.add(new OperationPush(val.value));
        }
        else if (exp instanceof ExpressionTree) {
            ExpressionTree tree = ((ExpressionTree) exp);

            if (tree.operator.type == TokenType.OPERATOR) {
                compileExpression(tree.right);
                compileExpression(tree.left);

                switch (tree.operator.token) {
                    case "+":
                        operations.add(new OperationAdd());
                        break;
                    case "-":
                        operations.add(new OperationSub());
                        break;
                    case "*":
                        operations.add(new OperationMul());
                        break;
                    case "/":
                        operations.add(new OperationDiv());
                        break;
                    case "=":
                        operations.add(new OperationAssign());
                }

            }
        }
    }

    private void compilePathToVar(String path) {
        operations.add(new OperationPushVariableStorage());
        String[] tokens = path.split("\\.");
        for (int i = 0; i < tokens.length; i++) {
            if (i == tokens.length - 1) {
                operations.add(new OperationPush(new ValueReference(tokens[i])));
            } else {
                operations.add(new OperationGetAndPush(tokens[i]));
            }
        }
    }
}
