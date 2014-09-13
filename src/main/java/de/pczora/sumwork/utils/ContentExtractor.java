package de.pczora.sumwork.utils;

import de.l3s.boilerpipe.BoilerpipeProcessingException;
import de.l3s.boilerpipe.extractors.ArticleExtractor;

/**
 * Created by pczora on 08/09/14.
 */
public class ContentExtractor {
    public String extractContent(String input) {
        try {
            String content = ArticleExtractor.INSTANCE.getText(input);
            // Replace multiple whitespaces with a single one:
            content = content.replaceAll("[ ]{2,}", " ");
            return  content;
        } catch (BoilerpipeProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
