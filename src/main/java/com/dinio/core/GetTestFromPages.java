package com.dinio.core;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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


@Component
public class GetTestFromPages {
    @Autowired
    IArticle article;

    public List<IArticle> readData(List<URL> links) throws ParserConfigurationException, SAXException, IOException, InterruptedException {
        List<IArticle> articles = new ArrayList<IArticle>();

        for (URL link : links) {

            Document doc = Jsoup.connect(link.toString()).userAgent("Mozilla").get();
            System.out.println(doc.select("div.article_text").first().text());
            article.setText(doc.select("div.article_text").first().text());
            article.setTitle(doc.select("h1").first().text());
            article.setLink(link);

            articles.add(article);
            Thread.sleep(1000);
        }
        return articles;
    }
}


