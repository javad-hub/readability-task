package de.uni_passau.fim.se2.sa.readability.utils;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import java.util.HashMap;
import java.util.Map;
import com.github.javaparser.ast.expr.*;
import com.github.javaparser.ast.type.ClassOrInterfaceType;

public class OperandVisitor extends VoidVisitorAdapter<Void> {
    /**
     * Maps operand names/types to the number of their occurrences in the given code snippet.
     */
    private final Map<String, Integer> operandsPerMethod;

    public OperandVisitor() {
        operandsPerMethod = new HashMap<>();
    }

    public Map<String, Integer> getOperandsPerMethod() {
        return operandsPerMethod;
    }

    // LLM-assisted implementation
    @Override
    public void visit(com.github.javaparser.ast.expr.NameExpr n, Void arg) {
        operandsPerMethod.merge(n.getNameAsString(), 1, Integer::sum);
        super.visit(n, arg);
    }

    // LLM-assisted implementation
    @Override
    public void visit(com.github.javaparser.ast.expr.FieldAccessExpr n, Void arg) {
        operandsPerMethod.merge(n.getScope().toString(), 1, Integer::sum);
        operandsPerMethod.merge(n.getNameAsString(), 1, Integer::sum);
        super.visit(n, arg);
    }

    // LLM-assisted implementation
    @Override
    public void visit(com.github.javaparser.ast.expr.ArrayAccessExpr n, Void arg) {
        operandsPerMethod.merge(n.getName().toString(), 1, Integer::sum);
        super.visit(n, arg);
    }

    // LLM-assisted implementation
    @Override
    public void visit(com.github.javaparser.ast.expr.StringLiteralExpr n, Void arg) {
        operandsPerMethod.merge("STRING_LITERAL", 1, Integer::sum);
        super.visit(n, arg);
    }

    // LLM-assisted implementation
    @Override
    public void visit(com.github.javaparser.ast.expr.IntegerLiteralExpr n, Void arg) {
        operandsPerMethod.merge("INT_LITERAL", 1, Integer::sum);
        super.visit(n, arg);
    }

    // LLM-assisted implementation
    @Override
    public void visit(com.github.javaparser.ast.expr.DoubleLiteralExpr n, Void arg) {
        operandsPerMethod.merge("DOUBLE_LITERAL", 1, Integer::sum);
        super.visit(n, arg);
    }

    // LLM-assisted implementation
    @Override
    public void visit(com.github.javaparser.ast.expr.BooleanLiteralExpr n, Void arg) {
        operandsPerMethod.merge("BOOLEAN_LITERAL", 1, Integer::sum);
        super.visit(n, arg);
    }

    // LLM-assisted implementation
    @Override
    public void visit(com.github.javaparser.ast.expr.CharLiteralExpr n, Void arg) {
        operandsPerMethod.merge("CHAR_LITERAL", 1, Integer::sum);
        super.visit(n, arg);
    }

    // LLM-assisted implementation
    @Override
    public void visit(com.github.javaparser.ast.expr.NullLiteralExpr n, Void arg) {
        operandsPerMethod.merge("NULL_LITERAL", 1, Integer::sum);
        super.visit(n, arg);
    }

    // LLM-assisted implementation
    @Override
    public void visit(com.github.javaparser.ast.expr.VariableDeclarationExpr n, Void arg) {
        for (var var : n.getVariables()) {
            if (var.getInitializer().isPresent()) {
                operandsPerMethod.merge(var.getNameAsString(), 1, Integer::sum);
            }
        }
        super.visit(n, arg);
    }

    // LLM-assisted implementation
    @Override
    public void visit(com.github.javaparser.ast.type.ClassOrInterfaceType n, Void arg) {
        operandsPerMethod.merge(n.getNameAsString(), 1, Integer::sum);
        super.visit(n, arg);
    }
}