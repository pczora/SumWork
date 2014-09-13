package de.pczora.sumwork.representations;

/**
 * Created by pczora on 02/09/14.
 */
public class Passage {
    private String content;
    private Document doc;
    private QueryInteraction queryInteraction;
    private double score;
    private BagOfWords bagOfWords;

    public Passage() {

    }

    public Passage(String content, Document doc) {
        this.content = content;
        this.doc = doc;
    }
    public Passage(String content, Document doc, QueryInteraction queryInteraction, double score) {
        this.content = content;
        this.doc = doc;
        this.queryInteraction = queryInteraction;
        this.score = score;
    }



    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Document getDoc() {
        return doc;
    }

    public void setDoc(Document doc) {
        this.doc = doc;
    }

    public QueryInteraction getQueryInteraction() {
        return queryInteraction;
    }

    public void setQueryInteraction(QueryInteraction queryInteraction) {
        this.queryInteraction = queryInteraction;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
    public BagOfWords getBagOfWords() {
        return bagOfWords;
    }

    public void setBagOfWords(BagOfWords bagOfWords) {
        this.bagOfWords = bagOfWords;
    }
}
