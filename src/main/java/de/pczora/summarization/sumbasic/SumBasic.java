package de.pczora.summarization.sumbasic;

import de.pczora.sumwork.representations.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pczora on 18/08/14.
 */
public class SumBasic {
    protected DocumentCollection documentCollection;
    protected PassageCollection passageCollection;
    protected Map<String, Double> wordWeights;

    public SumBasic() {

    }

    public SumBasic(DocumentCollection documentCollection, PassageCollection passageCollection) {
        this.documentCollection = documentCollection;
        this.passageCollection = passageCollection;
        this.wordWeights = new HashMap<>();

        for (String word : documentCollection.getBagOfWords().wordSet()) {
            wordWeights.put(word, documentCollection.getBagOfWords().wordFrequency(word));
        }
    }

    public double passageScore(Passage passage) {
        double score = 0;
        for (String word : passage.getBagOfWords().wordSet()) {
            score += wordWeights.get(word);
        }
        score = score / passage.getBagOfWords().getTotalWords();
        return score;
    }

    public PassageRanking rank() {
        PassageRanking passageRanking = new PassageRanking();

        for (Passage passage : passageCollection.getPassages()) {
            passage.setScore(passageScore(passage));
            passageRanking.add(passage);
        }
        return passageRanking;
    }

    public void penalize(Passage passage) {
//        System.out.println("Penalize!");
        for (String word : passage.getBagOfWords().wordSet()) {
            double weight = wordWeights.get(word);
//            System.out.println("Word: " + word + " old weight: " + weight + " new weight " + weight*weight);
            wordWeights.put(word, weight * weight);
        }
    }

    public Summary summarize(int length) {
        Summary summary = new Summary();
        PassageRanking passageRanking = this.rank();
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

    public void removePassage(Passage passage) {
        passageCollection.removePassage(passage);
    }
}
