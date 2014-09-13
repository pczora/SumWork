package de.pczora.sumwork.utils;

import java.util.Map;

/**
 * Created by pczora on 03/09/14.
 */
public class Similarity {
    public static double cosineSimilarity(Map<String, Double> vector1, Map<String, Double> vector2) {
        assert vector1.size() == vector2.size();
        return crossproduct(vector1, vector2) / (norm(vector1) * norm(vector2));
    }

    public static double norm(Map<String, Double> vector) {
        double norm = 0;
        for (Map.Entry<String, Double> entry : vector.entrySet()) {
            double value = entry.getValue();
            norm += value * value;
        }
        return Math.sqrt(norm);
    }

    public static double crossproduct(Map<String, Double> vector1, Map<String, Double> vector2) {
        double sum = 0;
        for (Map.Entry<String, Double> entry : vector1.entrySet()) {
            sum += entry.getValue() * vector2.get(entry.getKey());
        }
        return sum;
    }
}
