package de.uni_passau.fim.se2.sa.readability.features;


public class CyclomaticComplexityFeature extends FeatureMetric {

    /**
     * Computes the cyclomatic complexity metric based on the given code snippet.
     *
     * @return Cyclomatic complexity of the given code snippet.
     */
    @Override
    public double computeMetric(String codeSnippet) {
        // Implement the CyclomaticComplexityFeature using the CyclomaticComplexityVisitor
        // LLM-assisted implementation
try {
    var decl = de.uni_passau.fim.se2.sa.readability.utils.Parser.parseJavaSnippet(codeSnippet);
    var visitor = new de.uni_passau.fim.se2.sa.readability.utils.CyclomaticComplexityVisitor();
    decl.accept(visitor, null);
    return visitor.getComplexity();
} catch (Exception e) { return 1.0; }
    }

    @Override
    public String getIdentifier() {
        return "CyclomaticComplexity";
    }
}