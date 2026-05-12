package de.uni_passau.fim.se2.sa.readability.utils;

import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.HashMap;
import java.util.Map;

import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.expr.ConditionalExpr;
import com.github.javaparser.ast.expr.InstanceOfExpr;
import com.github.javaparser.ast.expr.UnaryExpr;

public class OperatorVisitor extends VoidVisitorAdapter<Void> {

    public enum OperatorType {
        ASSIGNMENT,         // x=y
        BINARY,             // x+y
        UNARY,              // -x, ++x
        CONDITIONAL,        // ?
        TYPE_COMPARISON,    // instanceof
    }

    /**
     * Maps operator types to the number of their occurrences in the given code snippet.
     */
    private final Map<OperatorType, Integer> operatorsPerMethod;

    public OperatorVisitor() {
        operatorsPerMethod = new HashMap<>();
    }

    public Map<OperatorType, Integer> getOperatorsPerMethod() {
        return operatorsPerMethod;
    }
    // LLM-assisted implementation - CORRECT SIGNATURE: Void arg, NOT Map
// State is stored in instance variable, not passed as argument

@Override
public void visit(com.github.javaparser.ast.expr.AssignExpr n, Void arg) {
operatorsPerMethod.merge(OperatorType.ASSIGNMENT, 1, Integer::sum);
    super.visit(n, arg);
}

@Override
public void visit(com.github.javaparser.ast.expr.BinaryExpr n, Void arg) {
    operatorsPerMethod.merge(OperatorType.BINARY, 1, Integer::sum);
    super.visit(n, arg);
}

@Override
public void visit(com.github.javaparser.ast.expr.UnaryExpr n, Void arg) {
    operatorsPerMethod.merge(OperatorType.UNARY, 1, Integer::sum);
    super.visit(n, arg);
}

@Override
public void visit(com.github.javaparser.ast.expr.ConditionalExpr n, Void arg) {
    operatorsPerMethod.merge(OperatorType.CONDITIONAL, 1, Integer::sum);
    super.visit(n, arg);
}

@Override
public void visit(com.github.javaparser.ast.expr.InstanceOfExpr n, Void arg) {
    operatorsPerMethod.merge(OperatorType.TYPE_COMPARISON, 1, Integer::sum);
    super.visit(n, arg);
}
}
