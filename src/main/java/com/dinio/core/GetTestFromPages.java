package com.dinio.core;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cannibal on 17.11.2014 г..
 */
public class GetTestFromPages extends DefaultHandler {

    List<Article> readData(List<URL> links) throws ParserConfigurationException, SAXException, IOException, InterruptedException {
        List<Article> articles = new ArrayList<Article>();

        for (URL link : links) {
            Article article = new Article();
            Document doc = Jsoup.connect(link.toString()).get();
            article.setText(doc.select("div.content").first().text());
            article.setTitle(doc.select("h1").first().text());
            article.setLink(link);
            articles.add(article);
            Thread.sleep(1000);
        }
        return  articles;
    }
}


