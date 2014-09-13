package de.pczora.sumwork.representations;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by pczora on 03/09/14.
 */
public class PassageCollection {
    private Set<Passage> passages;

    public PassageCollection() {
        passages = new HashSet<>();
    }

    public void add(Passage p) {
        passages.add(p);
    }

    public void merge(PassageCollection passageCollection) {

        passages.addAll(passageCollection.passages);
    }

    public Set<Passage> getPassages() {
        return passages;
    }

    public int size() {
        return passages.size();
    }

    public void removePassage(Passage passage) {
        this.passages.remove(passage);
    }
}
