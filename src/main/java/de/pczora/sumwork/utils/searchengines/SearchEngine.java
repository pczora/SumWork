package de.pczora.sumwork.utils.searchengines;

import de.pczora.sumwork.representations.QueryInteraction;
import de.pczora.sumwork.utils.ContentExtractor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pczora on 04/09/14.
 */
public abstract class SearchEngine {
    abstract public List<String> search(String query, int count);

    public List<de.pczora.sumwork.representations.Document> getResultDocs(String query, int count) {
        List<de.pczora.sumwork.representations.Document> docs = new ArrayList<>();
        List<String> urls = search(query, count);
        StringBuilder stringBuilder;
        String inputLine;

        for (String url : urls) {
            stringBuilder = new StringBuilder();
            try {
                URL u = new URL(url);
                BufferedReader in = new BufferedReader(new InputStreamReader(u.openStream()));
                while ((inputLine = in.readLine()) != null) {
                    stringBuilder.append(inputLine + "\n");
                }
//                while ((stringBuilder.append(in.readLine())) != null) {}
                de.pczora.sumwork.representations.Document document = new de.pczora.sumwork.representations.Document();
                document.setUrl(url);
                document.setContent(stringBuilder.toString());
                document.setQueryInteraction(new QueryInteraction(query, null, null));
                docs.add(document);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return docs;
    }

    public List<de.pczora.sumwork.representations.Document> getResultDocsAsPlaintext(String query, int count) {
        List<de.pczora.sumwork.representations.Document> htmlDocs = getResultDocs(query, count);
        List<de.pczora.sumwork.representations.Document> plaintextDocs = new ArrayList<>(htmlDocs);
        ContentExtractor contentExtractor = new ContentExtractor();
        for (de.pczora.sumwork.representations.Document htmlDoc : plaintextDocs) {
            htmlDoc.setContent(contentExtractor.extractContent(htmlDoc.getContent()));
//            htmlDoc.setContent(extractContent(htmlDoc.getContent()));
//            plaintextDocs.add(extractContent(html));
        }
        return plaintextDocs;
    }



    public String extractContent(String html) {
        String content = "";
        Document doc = Jsoup.parse(html);
        Elements elements = doc.select("p");
//        System.out.println(elements.text());
//        elements.forEach((element) -> System.out.println(element.text()));
        for (Element e : elements) {
            content += e.text() + "\n";
        }
        return content;
    }
}
