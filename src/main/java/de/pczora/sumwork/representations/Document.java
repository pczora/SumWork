package de.pczora.sumwork.representations;

/**
 * Created by pczora on 02/09/14.
 */
public class Document {
    private String url;
    private String content;
    private QueryInteraction queryInteraction;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public QueryInteraction getQueryInteraction() {
        return queryInteraction;
    }

    public void setQueryInteraction(QueryInteraction queryInteraction) {
        this.queryInteraction = queryInteraction;
    }
}
