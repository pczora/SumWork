package de.pczora.sumwork.representations;

import java.util.List;

/**
 * Created by pczora on 02/09/14.
 */
public class QueryInteraction {
    public enum intentType {SPECIALIZATION, GENERALIZATION, DRIFT, CLEANUP};

    private String query;
    private intentType intent;
    private List<Document> results;

    public String getQuery() {
        return query;
    }

    public QueryInteraction(String query, intentType intent, List<Document> results) {
        this.query = query;
        this.intent = intent;
        this.results = results;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public intentType getIntent() {
        return intent;
    }

    public void setIntent(intentType intent) {
        this.intent = intent;
    }

    public List<Document> getResults() {
        return results;
    }

    public void setResults(List<Document> results) {
        this.results = results;
    }
}
