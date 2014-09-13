package de.pczora.sumwork.representations;

/**
 * Created by pczora on 03/09/14.
 */
public class DocumentCollectionItem {
    private Document document;
    private BagOfWords bagOfWords;

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public BagOfWords getBagOfWords() {
        return bagOfWords;
    }

    public void setBagOfWords(BagOfWords bagOfWords) {
        this.bagOfWords = bagOfWords;
    }
}
