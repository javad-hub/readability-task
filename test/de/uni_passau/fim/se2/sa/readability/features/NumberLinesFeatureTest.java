// LLM-assisted implementation
package de.uni_passau.fim.se2.sa.readability.features;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NumberLinesFeatureTest {
    private final NumberLinesFeature feature = new NumberLinesFeature();

    @Test void testEmpty() { assertEquals(0.0, feature.computeMetric("")); }
    @Test void testSingleLine() { assertEquals(1.0, feature.computeMetric("int x=5;")); }
    @Test void testMultiLine() {
        String code = "void m() {\n  int x=1;\n  // comment\n\n  return x;\n}";
        assertEquals(6.0, feature.computeMetric(code), 0.01);
    }
}