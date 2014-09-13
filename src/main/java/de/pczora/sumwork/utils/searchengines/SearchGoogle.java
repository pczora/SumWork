package de.pczora.sumwork.utils.searchengines;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pczora on 04/09/14.
 */
public class SearchGoogle extends SearchEngine {
    static final String google = "http://www.google.com/search?q=";
    static final String charset = "UTF-8";
    static final String userAgent = "SumBot 1.0 (+http://example.com/bot)"; // Change this to your company's name and bot homepage!

    public List<String> search(String query, int count) {
        List<String> results = new ArrayList<>();
        Elements links = null;
        try {
            links = Jsoup.connect(google + URLEncoder.encode(query, charset) + "&num=" + count).userAgent(userAgent).get().select("li.g>h3>a");

            for (Element link : links) {
                String url = link.absUrl("href"); // Google returns URLs in format "http://www.google.com/url?q=<url>&sa=U&ei=<someKey>".
                url = URLDecoder.decode(url.substring(url.indexOf('=') + 1, url.indexOf('&')), "UTF-8");

                if (!url.startsWith("http")) {
                    continue; // Ads/news/etc.
                }

//                System.out.println("URL: " + url);
                results.add(url);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return results;
    }
}
