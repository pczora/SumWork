package de.pczora.sumwork.representations;

import java.util.*;

/**
 * Created by pczora on 03/09/14.
 */
public class DocumentCollection {
    private Set<DocumentCollectionItem> documents;
    private Map<String, Long> wordInDocs;
    private BagOfWords bagOfWords;

    public DocumentCollection() {
        documents = new HashSet<>();
        wordInDocs = new HashMap<>();
        bagOfWords = new BagOfWords();
    }

    public void addItem(DocumentCollectionItem item) {
        documents.add(item);

        this.bagOfWords = this.bagOfWords.mergeBag(item.getBagOfWords());

        for (String word : item.getBagOfWords().wordSet()) {
            if (!wordInDocs.containsKey(word)) {
                wordInDocs.put(word, 1L);
            } else {
                wordInDocs.put(word, wordInDocs.get(word) + 1);
            }
        }
    }

    public void addListItems(Set<DocumentCollectionItem> itemSet) {
        for (DocumentCollectionItem item : itemSet) {
            addItem(item);
        }
    }

    private double idf(String word) {

        // What if the word does not appear in the collection?
        assert wordInDocs.containsKey(word);
        return Math.log((double) documents.size() / wordInDocs.get(word));
    }

    public Map<String, Double> tfIdf(BagOfWords bag) {
        Map<String, Double> tfIdfVector = new HashMap<>();
        for (Map.Entry<String, Long> entry : wordInDocs.entrySet()) {
            String word = entry.getKey();
            if (!bag.contains(word)) {
                tfIdfVector.put(word, 0D);
            }
            else {
                tfIdfVector.put(word, bag.wordFrequency(word) * idf(word));
            }
        }
        return tfIdfVector;
    }

    public Map<String, Double> getEmptyVector() {
        Map<String, Double> vec = new HashMap<>();
        for (String key : wordInDocs.keySet()) {
            vec.put(key, 0D);
        }
        return vec;
    }

    public BagOfWords getEmptyBagOfWords() {
        Map<String, Long> map = new HashMap<>();
        for (String key : wordInDocs.keySet()) {
            map.put(key, 0L);
        }
        return new BagOfWords(map);
    }

    public Set<DocumentCollectionItem> getDocuments() {
        return documents;
    }

    public BagOfWords getBagOfWords() {
        return bagOfWords;
    }

    public int size() {
        return documents.size();
    }
}
