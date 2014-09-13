package de.pczora.sumwork.utils;

import de.pczora.sumwork.representations.*;

/**
 * Created by pczora on 03/09/14.
 */
public class SumTokenizer {
    static OpenNLPWrapper openNLPWrapper;
    static Stopwords stopwords;
    public SumTokenizer() {
        openNLPWrapper = new OpenNLPWrapper();
        stopwords = new Stopwords();
    }

    /*
    Stopword removal only works when punctuation chars are removed also
     */
    public BagOfWords tokenizeToBagOfWords(String input, boolean removePunctuation, boolean removeStopwords) {
        String[] tokens = openNLPWrapper.tokenize(input);
        BagOfWords bagOfWords = new BagOfWords();
        for (String s : tokens) {
            s = s.toLowerCase();
            if (removePunctuation) {
                if (!s.matches(".*\\p{Punct}.*")) { // If the token contains a punctuation char, we ignore it
                    if (removeStopwords && !stopwords.isStopword(s)) {
                        bagOfWords.put(s);
                    }
                    else if (!removeStopwords) {
                        bagOfWords.put(s);
                    }
                }
            }
            else {
                bagOfWords.put(s.toLowerCase());
            }
        }
        return bagOfWords;
    }

    public SentenceCollection extractSentences(Document document) {
        String[] sentences = openNLPWrapper.extractSentences(document.getContent());
        SentenceCollection sentenceCollection = new SentenceCollection();
        for (String sentence : sentences) {
            Sentence s = new Sentence(document, sentence);
            sentenceCollection.add(s);
        }
        return sentenceCollection;
    }

    public PassageCollection extractPassages(Document document) {
        String[] sentences = openNLPWrapper.extractSentences(document.getContent());
        PassageCollection sentenceCollection = new PassageCollection();
        for (String sentence : sentences) {
            BagOfWords bagOfWords = tokenizeToBagOfWords(sentence,true,true);
            if (bagOfWords.wordSet().size() == 0)
                continue;
//            System.out.println(bagOfWords.getTotalWords());
            Passage p = new Passage(sentence, document, document.getQueryInteraction(), 0);
            p.setBagOfWords(bagOfWords);
            sentenceCollection.add(p);
        }
        return sentenceCollection;
    }

    public PassageCollection extractPassages(Document document, int passageLength) {
        String[] sentences = openNLPWrapper.extractSentences(document.getContent());
        PassageCollection passageCollection = new PassageCollection();
        int counter = 0;
//        String passage = new String();
        StringBuilder stringBuilder = new StringBuilder();

        for (String sentence : sentences) {
            if (counter < passageLength) {
                stringBuilder.append(" " + sentence);
                counter++;
            } else {
                String passage = stringBuilder.toString();
                BagOfWords bagOfWords = tokenizeToBagOfWords(passage, true, true);
                if (bagOfWords.wordSet().size() == 0)
                    continue;
                Passage p = new Passage(passage, document, document.getQueryInteraction(), 0);
                p.setBagOfWords(bagOfWords);
                passageCollection.add(p);
                counter = 0;
                stringBuilder = new StringBuilder();
            }
        }
        return passageCollection;
    }
}
