package de.pczora;

import de.pczora.summarization.sumbasic.SumBasicQuery;
import de.pczora.sumwork.representations.*;
import de.pczora.sumwork.utils.SumTokenizer;
import de.pczora.sumwork.utils.searchengines.SearchDuckDuckGo;
import de.pczora.sumwork.utils.searchengines.SearchEngine;
import de.pczora.sumwork.utils.searchengines.SearchGoogle;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main(String[] args)
    {
        SumTokenizer tokenizer = new SumTokenizer();
        SearchEngine searchEngine = new SearchGoogle();
        DocumentCollection documentCollection = new DocumentCollection();
        PassageCollection passageCollection = new PassageCollection();
        List<Document> results = searchEngine.getResultDocsAsPlaintext("wombat diet", 5);
        for (Document d : results) {
            DocumentCollectionItem item = new DocumentCollectionItem();
            item.setDocument(d);
            item.setBagOfWords(tokenizer.tokenizeToBagOfWords(d.getContent(), true, true));
            documentCollection.addItem(item);
            passageCollection.merge(tokenizer.extractPassages(d, 5));
        }
//        System.out.println("Size of document collection: " + documentCollection.size());
//        System.out.println("Size of passage collection: " + passageCollection.size());
        SumBasicQuery sumBasic = new SumBasicQuery(documentCollection, passageCollection);
        BagOfWords query = new BagOfWords();
        query.put("wombat");
        query.put("wombat diet");
//        for (int i = 0; i < 10; i++) {
//            ranking = sumBasic.rank(query);
//            Passage p = ranking.poll();
////            System.out.println(p.getDoc().getUrl());
//            System.out.println(p.getContent());
////            System.out.println(p.getScore());
//            sumBasic.penalize(p);
//            sumBasic.removePassage(p);
//        }
//        Summary summary = sumBasic.summarize(3, query);
//        System.out.println(summary.toString());
//
//        List<Document> results2 = searchEngine.getResultDocsAsPlaintext("wombat eat", 5);
//        for (Document d : results2) {
//            DocumentCollectionItem item = new DocumentCollectionItem();
//            item.setDocument(d);
//            item.setBagOfWords(tokenizer.tokenizeToBagOfWords(d.getContent(), true, true));
//            documentCollection.addItem(item);
//            passageCollection.merge(tokenizer.extractPassages(d, 5));
//        }
//        SumBasicQuery sumBasic2 = new SumBasicQuery(documentCollection, passageCollection);
//        BagOfWords query2 = new BagOfWords();
//        query2.put("wombat");
//        query2.put("eat");
//
//        System.out.println("\n\n");
//        Summary summary1 = sumBasic2.summarize(3, query2);
//        System.out.println(summary1.toString());
//
//        MMRreplace mmRreplace = new MMRreplace(documentCollection, summary, new PassageCollection());
//        Passage maxPassage = new Passage();
//        System.out.println("DINGDING");
//        System.out.println(summary1.getPassageList().get(0).getDoc().getUrl());
//        System.out.println(summary1.getPassageList().get(0).getContent());
//        mmRreplace.scorePassage(summary1.getPassageList().get(0), "", maxPassage);

        SearchEngine ddg = new SearchDuckDuckGo();
        List<String> res = ddg.search("test", 1);
        System.out.println(res.size());
        System.out.println(res.get(0));
    }

}
