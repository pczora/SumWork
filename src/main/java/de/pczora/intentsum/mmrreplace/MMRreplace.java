package de.pczora.intentsum.mmrreplace;

import de.pczora.sumwork.representations.*;
import de.pczora.sumwork.utils.Similarity;
import de.pczora.sumwork.utils.SumTokenizer;

import java.util.Map;

/**
 * Created by pczora on 03/09/14.
 */
public class MMRreplace {
    private DocumentCollection documentCollection;
    private Summary summary;
    private PassageCollection unselected;
    private SumTokenizer sumTokenizer;

    public MMRreplace(DocumentCollection collection, Summary sum, PassageCollection unselected) {
        this.documentCollection = collection;
        this.summary = sum;
        this.unselected = unselected;
        this.sumTokenizer = new SumTokenizer();
    }

    public double scorePassage(Passage passage, String query, Passage maxPassage) {
        double max = 0;
        BagOfWords unselectedPassageBag = sumTokenizer.tokenizeToBagOfWords(passage.getContent(), true, true);
        Map<String, Double> unselectedPassageTfIdf = documentCollection.tfIdf(unselectedPassageBag);
        for (Passage selectedPassage : summary.getPassageList()) {
            BagOfWords selectedPassageBag = sumTokenizer.tokenizeToBagOfWords(selectedPassage.getContent(), true, true);
            Map<String, Double> selectedPassageTfIdf = documentCollection.tfIdf(selectedPassageBag);
            double sim = Similarity.cosineSimilarity(unselectedPassageTfIdf, selectedPassageTfIdf);
            if (sim > max) {
                max = sim;
                maxPassage = selectedPassage;
            }
        }
        System.out.println("MAX PASSAGE");
        System.out.println("SCORE: " + max);
        System.out.println(maxPassage.getDoc().getUrl());
        System.out.println(maxPassage.getContent());
        return max;
    }
}
