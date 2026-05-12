package de.uni_passau.fim.se2.sa.readability.utils;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.expr.ConditionalExpr;
import com.github.javaparser.ast.stmt.*;

public class CyclomaticComplexityVisitor extends VoidVisitorAdapter<Void> {
    /**
     * Counts the number of independent control flow paths (McCabe's Cyclomatic Complexity).
     */
    private int complexity = 1; // Base complexity is 1 for a single path

    public CyclomaticComplexityVisitor() {
        // Base complexity initialized to 1
    }

    public int getComplexity() {
        return complexity;
    }

    // LLM-assisted implementation
    @Override
    public void visit(com.github.javaparser.ast.stmt.IfStmt n, Void arg) {
        complexity++;
        super.visit(n, arg);
    }

    // LLM-assisted implementation
    @Override
    public void visit(com.github.javaparser.ast.stmt.WhileStmt n, Void arg) {
        complexity++;
        super.visit(n, arg);
    }

    // LLM-assisted implementation
    @Override
    public void visit(com.github.javaparser.ast.stmt.ForStmt n, Void arg) {
        complexity++;
        super.visit(n, arg);
    }

    // LLM-assisted implementation
    @Override
    public void visit(com.github.javaparser.ast.stmt.ForEachStmt n, Void arg) {
        complexity++;
        super.visit(n, arg);
    }

    // LLM-assisted implementation
    @Override
    public void visit(com.github.javaparser.ast.stmt.CatchClause n, Void arg) {
        complexity++;
        super.visit(n, arg);
    }

    // LLM-assisted implementation
    @Override
    public void visit(com.github.javaparser.ast.expr.ConditionalExpr n, Void arg) {
        complexity++;
        super.visit(n, arg);
    }

    // LLM-assisted implementation
    @Override
    public void visit(com.github.javaparser.ast.expr.BinaryExpr n, Void arg) {
        if (n.getOperator() == com.github.javaparser.ast.expr.BinaryExpr.Operator.AND ||
            n.getOperator() == com.github.javaparser.ast.expr.BinaryExpr.Operator.OR) {
            complexity++;
        }
        super.visit(n, arg);
    }
}