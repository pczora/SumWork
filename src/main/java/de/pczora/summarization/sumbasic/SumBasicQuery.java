package de.pczora.summarization.sumbasic;

import de.pczora.sumwork.representations.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pczora on 08/09/14.
 */
public class SumBasicQuery extends SumBasic {
    Map<String, Double> queryWordWeights;
    public SumBasicQuery(DocumentCollection documentCollection, PassageCollection passageCollection) {
        super(documentCollection, passageCollection);
        queryWordWeights = new HashMap<>();

    }

    public PassageRanking rank(BagOfWords query) {

        PassageRanking passageRanking = new PassageRanking();

        for (Passage passage : passageCollection.getPassages()) {
            passage.setScore(passageScore(passage, query, 0.5));
            passageRanking.add(passage);
        }
        return passageRanking;
    }

    public double passageScore(Passage passage, BagOfWords query, double gamma) {
        double zeta = 0;
        for (String word : documentCollection.getBagOfWords().wordSet()) {
            if (query.contains(word)) {
                queryWordWeights.put(word, documentCollection.getBagOfWords().wordCount(word).doubleValue() + gamma);
                zeta = zeta + documentCollection.getBagOfWords().wordCount(word) * gamma;
            }
            else {
                queryWordWeights.put(word, documentCollection.getBagOfWords().wordCount(word).doubleValue());
            }
        }

        for (String word : queryWordWeights.keySet()) {
            queryWordWeights.put(word, queryWordWeights.get(word) / (documentCollection.getBagOfWords().getTotalWords() + zeta));
        }

        double score = 0;
        for (String word : passage.getBagOfWords().wordSet()) {
            score += queryWordWeights.get(word);
        }
        score = score / passage.getBagOfWords().getTotalWords();
        return score;
    }

    @Override
    public void penalize(Passage passage) {
        for (String word : passage.getBagOfWords().wordSet()) {
            double weight = queryWordWeights.get(word);
            queryWordWeights.put(word, weight*weight);
        }
    }

    public Summary summarize(int length, BagOfWords query) {
        Summary summary = new Summary();
        PassageRanking passageRanking = this.rank(query);
        Passage passage;
        for (int i = 0; i < length; i++) {
            passage = passageRanking.poll();
            summary.getPassageList().add(i, passage);
            penalize(passage);
            passageCollection.removePassage(passage);
            passageRanking = this.rank();
        }

        return summary;
    }
}
