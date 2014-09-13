package de.pczora.sumwork.representations;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by pczora on 03/09/14.
 */
public class BagOfWords {
    private Map<String, Long> bag;
    private long totalWords = 0;

    public BagOfWords() {
        bag = new HashMap<>();
    }
    public BagOfWords(Map<String, Long> vec) {
        bag = new HashMap<>(vec);
    }
    public BagOfWords(BagOfWords other) {
        bag = new HashMap<>(other.bag);
        totalWords = other.totalWords;
    }

    public void put(String word) {
        if (!bag.containsKey(word)) {
            bag.put(word, 1L);
        }
        else {
            bag.put(word, bag.get(word) + 1);
        }

        totalWords++;
    }

    public Long wordCount(String word) {
        if (bag.containsKey(word)) {
            return bag.get(word);
        }
        else {
            return 0L;
        }
    }

    public double wordFrequency(String word) {
        if (bag.containsKey(word))
            return (double) bag.get(word) / totalWords;
        return 0;
    }

    public Set<String> wordSet() {
        Set<String> wordSet = new HashSet<String>();
        for (Map.Entry<String, Long> entry : bag.entrySet()) {
            wordSet.add(entry.getKey());
        }
        return wordSet;
    }

    public boolean contains(String word) {
        return bag.containsKey(word);
    }

    public BagOfWords mergeBag(BagOfWords otherBag) {
        BagOfWords mergedBag = new BagOfWords(this);
        for (String s : otherBag.wordSet()) {
            if (mergedBag.bag.containsKey(s)) {
                mergedBag.bag.put(s, mergedBag.bag.get(s) + otherBag.bag.get(s));
            }
            else {
                mergedBag.bag.put(s, otherBag.bag.get(s));
            }
        }

        mergedBag.totalWords = (mergedBag.totalWords + otherBag.totalWords);
        return mergedBag;
    }

    public long getTotalWords() {
        return totalWords;
    }
}
