package com.musala.core;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class GetTestFromPages {
    @Autowired
    IArticle article;
    List<IArticle> articles = new ArrayList<IArticle>();
    private String tagForTextSelection;
    private String tagForTitleSelection;

    public void setTagForTextSelection(String tagForTextSelection) {
        this.tagForTextSelection = tagForTextSelection;
    }

    public void setTagForTitleSelection(String tagForTitleSelection) {
        this.tagForTitleSelection = tagForTitleSelection;
    }

    public List<IArticle> readData(List<URL> links) throws ParserConfigurationException, SAXException, IOException, InterruptedException {

        for (URL link : links) {

            extractArticleText(link);
        }
        return articles;
    }


    private void extractArticleText(URL link) throws IOException, InterruptedException {
        Document doc = Jsoup.connect(link.toString()).userAgent("Mozilla").get();
        System.out.println(doc.select(tagForTitleSelection).first().text());
        System.out.println(doc.select(tagForTextSelection).first().text());
        article.setText(doc.select(tagForTextSelection).first().text());
        article.setTitle(doc.select(tagForTitleSelection).first().text());
        article.setLink(link.toString());

        articles.add(article);
    }

}


