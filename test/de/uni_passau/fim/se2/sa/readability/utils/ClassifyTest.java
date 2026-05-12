// LLM-assisted implementation
package de.uni_passau.fim.se2.sa.readability.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import weka.core.Instances;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.*;

class ClassifyTest {
    @TempDir Path tempDir;

    @Test
    void testLoadAndTrain() throws Exception {
        // Create CSV with ≥10 rows (required for 10-fold cross-validation)
        String csvData = "File,NumberLines,TokenEntropy,HalsteadVolume,CyclomaticComplexity,Truth\n" +
            "1.jsnp,1.00,1.00,10.00,1.00,Y\n" +
            "2.jsnp,2.00,2.00,20.00,2.00,N\n" +
            "3.jsnp,3.00,3.00,30.00,3.00,Y\n" +
            "4.jsnp,4.00,4.00,40.00,4.00,N\n" +
            "5.jsnp,5.00,5.00,50.00,5.00,Y\n" +
            "6.jsnp,6.00,6.00,60.00,6.00,N\n" +
            "7.jsnp,7.00,7.00,70.00,7.00,Y\n" +
            "8.jsnp,8.00,8.00,80.00,8.00,N\n" +
            "9.jsnp,9.00,9.00,90.00,9.00,Y\n" +
            "10.jsnp,10.00,10.00,100.00,10.00,N\n";
        
        File csvFile = tempDir.resolve("data.csv").toFile();
        Files.writeString(csvFile.toPath(), csvData);

        // Test loadDataset
        Instances dataset = Classify.loadDataset(csvFile);
        assertNotNull(dataset);
        assertEquals(6, dataset.numAttributes()); // File + 4 features + Truth
        assertEquals("Truth", dataset.classAttribute().name());

        // Test trainAndEvaluate (should not throw)
        assertDoesNotThrow(() -> Classify.trainAndEvaluate(dataset));
    }
}