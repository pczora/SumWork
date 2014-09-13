package de.pczora.sumwork.representations;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by pczora on 07/09/14.
 */
public class PassageRanking extends PriorityQueue<Passage> {
    Comparator<Passage> comparator = (p1, p2) -> Double.compare(p1.getScore(), p2.getScore());

    public PassageRanking() {
        super(new Comparator<Passage>() {
            @Override
            public int compare(Passage o1, Passage o2) {
                //Invert the order of the passages - we want the highest scoring passage first
                return Double.compare(o2.getScore(), o1.getScore());
            }
        });

    }

}
