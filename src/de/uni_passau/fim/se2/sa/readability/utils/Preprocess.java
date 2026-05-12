package de.uni_passau.fim.se2.sa.readability.utils;

import de.uni_passau.fim.se2.sa.readability.features.FeatureMetric;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class Preprocess {

    /**
     * A value of 3.6 splits the Scalabrino Dataset into almost evenly balanced binary classes.
     */
    private static final double TRUTH_THRESHOLD = 3.6;

    /**
     * Traverses through each java snippet in the specified source directory and computes the specified list of feature metrics.
     * Each snippet is then saved together with its extracted feature values and the truth score as one row in the csv.
     * <p>
     * The File column value corresponds to the respective file name.
     * All feature values are rounded to two decimal places.
     * The truth value corresponds to a String that is set to the value "Y" if the mean rater score of a given snippet is greater or equal
     * than the TRUTH_THRESHOLD. Otherwise, if the mean score is lower than the TRUTH_THRESHOLD the truth value String is set to "N".
     *
     * @param sourceDir      the directory containing java snippet (.jsnp) files.
     * @param truth          the ground truth csv file containing the human readability ratings of the code snippets.                       `
     * @param csv            the builder for the csv.
     * @param featureMetrics the list of specified features via the cli.
     * @throws IOException if the source directory or the truth file does not exist.
     */
    public static void collectCSVBody(Path sourceDir, File truth, StringBuilder csv, List<FeatureMetric> featureMetrics) throws IOException {
        // LLM-assisted implementation
java.util.Map<String, Double> truthMap = new java.util.HashMap<>();
try (java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader(truth))) {
    String line; boolean first = true;
    while ((line = br.readLine()) != null) {
        if (first) { first = false; continue; }
        String[] parts = line.split(","); String filename = parts[0];
        double sum = 0; int count = 0;
        for (int i = 1; i <= 9 && i < parts.length; i++) {
            if (!parts[i].isEmpty()) { sum += Double.parseDouble(parts[i]); count++; }
        }
        if (count > 0) truthMap.put(filename, sum / count);
    }
}
java.util.List<java.nio.file.Path> files = java.nio.file.Files.list(sourceDir)
    .filter(p -> p.toString().endsWith(".jsnp"))
    .sorted(java.util.Comparator.comparingInt(p -> Integer.parseInt(p.getFileName().toString().replace(".jsnp", ""))))
    .collect(java.util.stream.Collectors.toList());
if (csv.length() == 0) csv.append("File,NumberLines,TokenEntropy,HalsteadVolume,CyclomaticComplexity,Truth\n");
for (var snippet : files) {
    String code = java.nio.file.Files.readString(snippet);
    String filename = snippet.getFileName().toString();
    var values = featureMetrics.stream().map(fm -> fm.computeMetric(code)).collect(java.util.stream.Collectors.toList());
    double avg = truthMap.getOrDefault(filename, 0.0);
    String label = (avg >= TRUTH_THRESHOLD) ? "Y" : "N";
    csv.append(String.format("%s,%.2f,%.2f,%.2f,%.2f,%s\n", filename, values.get(0), values.get(1), values.get(2), values.get(3), label));
}
    }
}
