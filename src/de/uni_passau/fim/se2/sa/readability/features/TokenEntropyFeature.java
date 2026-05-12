package de.uni_passau.fim.se2.sa.readability.features;

public class TokenEntropyFeature extends FeatureMetric {

    /**
     * Computes the entropy metric based on the tokens of the given code snippet.
     * Since we are interested in the readability of code as perceived by a human, tokens also include whitespaces and the like.
     *
     * @return token entropy of the given code snippet.
     */
    @Override
    public double computeMetric(String codeSnippet) {
        // LLM-assisted implementation
try {
    var decl = de.uni_passau.fim.se2.sa.readability.utils.Parser.parseJavaSnippet(codeSnippet);
    var tokens = decl.getTokenRange().orElse(null);
    if (tokens == null) return 0.0;
    java.util.Map<String, Integer> freq = new java.util.HashMap<>();
    int total = 0;
    for (var token : tokens) { String t = token.getText(); freq.put(t, freq.getOrDefault(t, 0) + 1); total++; }
    if (total == 0) return 0.0;
    double entropy = 0.0;
    for (int c : freq.values()) { double p = (double)c/total; entropy -= p * (Math.log(p)/Math.log(2)); }
    return entropy;
} catch (Exception e) { return 0.0; }
    }

    @Override
    public String getIdentifier() {
        return "TokenEntropy";
    }
}
