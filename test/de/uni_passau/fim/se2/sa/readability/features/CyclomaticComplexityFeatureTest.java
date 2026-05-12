// LLM-assisted implementation
package de.uni_passau.fim.se2.sa.readability.features;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CyclomaticComplexityFeatureTest {
    private final CyclomaticComplexityFeature feature = new CyclomaticComplexityFeature();

    @Test void testBaseComplexityIsOne() {
        assertEquals(1.0, feature.computeMetric("void m() { int x=1; }"), 0.01);
    }
    @Test void testIfAddsOne() {
        // Base 1 + 1 for if = 2
        assertTrue(feature.computeMetric("void m() { if (x>0) {} }") >= 2.0, "if should increase complexity");
    }
}