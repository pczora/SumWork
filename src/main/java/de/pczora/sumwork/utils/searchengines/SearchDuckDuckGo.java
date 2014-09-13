package de.pczora.sumwork.utils.searchengines;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by pczora on 13/09/14.
 */
public class SearchDuckDuckGo extends SearchEngine {
    static final String ddg = "https://duckduckgo.com/html/?q=";
    static final String charset = "UTF-8";
    static final String userAgent = "SumBot 1.0 (+http://example.com/bot)"; // Change this to your company's name and bot homepage!

    public List<String> search(String query, int count) {
        List<String> results = new ArrayList<>();
        Elements links = null;
        try {
            links = Jsoup.connect(ddg + URLEncoder.encode(query, charset) + "&k1=-1").userAgent(userAgent).get().select("div.url");
            results.addAll(links.stream().map(link -> "http://" + link.text()).limit(count).collect(Collectors.toList()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return results;
    }

}
