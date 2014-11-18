package com.dinio.core;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class TestRss   {
    public static URL URL_LINK;

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException, InterruptedException {


        URL_LINK = new URL("http://feeds.feedburner.com/computerworldbgnews?format=xml");
        RssGetLinks handler = new RssGetLinks();
        GetTestFromPages textFromPagesHandler = new GetTestFromPages();

        handler.readData(URL_LINK);
        List<Article> articles = textFromPagesHandler.readData(handler.getLinks());
        for(Article article:articles){
            System.out.println(article);
        }


    }
}
