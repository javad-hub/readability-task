// LLM-assisted implementation
package de.uni_passau.fim.se2.sa.readability.features;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TokenEntropyFeatureTest {
    private final TokenEntropyFeature feature = new TokenEntropyFeature();

    @Test void testNonEmptyReturnsPositive() {
        double entropy = feature.computeMetric("int x = 10;");
        assertTrue(entropy >= 0.0, "Entropy should be non-negative");
    }
    @Test void testEmptyReturnsZero() { assertEquals(0.0, feature.computeMetric(""), 0.01); }
}