package de.pczora.sumwork.representations;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pczora on 02/09/14.
 */
public class Summary {

    private List<Passage> passageList;

    public Summary() {
        passageList = new ArrayList<>();
    }

    public Summary(List<Passage> passageList) {
        this.passageList = passageList;
    }


    public List<Passage> getPassageList() {
        return passageList;
    }

    public void setPassageList(List<Passage> passageList) {
        this.passageList = passageList;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Passage passage : passageList) {
            stringBuilder.append(passage.getContent() + "\n");
        }
        return stringBuilder.toString();
    }
}
