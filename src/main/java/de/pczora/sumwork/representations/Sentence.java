package de.pczora.sumwork.representations;

/**
 * Created by pczora on 03/09/14.
 */
public class Sentence {
    private Document document;
    private String content;

    public Sentence(Document document, String sentence) {
        this.document = document;
        this.content = sentence;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
