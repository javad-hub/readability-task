// LLM-assisted implementation
package de.uni_passau.fim.se2.sa.readability.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

class PreprocessTest {
    @TempDir Path tempDir;

    @Test
    void testCsvFormat() throws Exception {
        // Create mock .jsnp snippet
        Files.writeString(tempDir.resolve("1.jsnp"), "void m() { int x = 1; }");

        // Create truth CSV with ALL 9 rater columns (required by code)
        File truth = tempDir.resolve("truth.csv").toFile();
        Files.writeString(truth.toPath(), 
            "file,rater1,rater2,rater3,rater4,rater5,rater6,rater7,rater8,rater9\n" +
            "1.jsnp,4.0,4.0,4.0,4.0,4.0,4.0,4.0,4.0,4.0\n");

        StringBuilder csv = new StringBuilder();
        Preprocess.collectCSVBody(tempDir, truth, csv, Arrays.asList(
            new de.uni_passau.fim.se2.sa.readability.features.NumberLinesFeature()
        ));

        String output = csv.toString();
        
        // Verify CSV header
        assertTrue(output.contains("File,NumberLines,TokenEntropy,HalsteadVolume,CyclomaticComplexity,Truth"));
        
        // Verify file entry exists
        assertTrue(output.contains("1.jsnp"));
        
        // Verify truth label (avg=4.0 >= 3.6 → Y)
        assertTrue(output.contains("Y"));
        
        // Verify numeric formatting (2 decimal places)
        assertTrue(output.matches(".*1\\.jsnp,\\d+\\.\\d{2},.*"));
    }
}