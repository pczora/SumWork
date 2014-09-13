package de.pczora.sumwork.representations;

import java.util.Set;

/**
 * Created by pczora on 03/09/14.
 */
public class SentenceCollection {
    Set<Sentence> sentenceSet;

    public Set<Sentence> getSentenceSet() {
        return sentenceSet;
    }

    public void setSentenceSet(Set<Sentence> sentenceSet) {
        this.sentenceSet = sentenceSet;
    }

    public void add(Sentence s) {
        sentenceSet.add(s);
    }

    public void merge(SentenceCollection sentenceCollection) {
        sentenceSet.addAll(sentenceCollection.sentenceSet);
    }
}
