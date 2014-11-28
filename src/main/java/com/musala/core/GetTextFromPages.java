package com.musala.core;

import com.musala.db.ArticleEntity;
import com.musala.repository.SiteRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Component
public class GetTextFromPages {
    @Autowired
    private SiteRepository siteRepository;

    ArticleEntity article;
    List<ArticleEntity> articles = new ArrayList<ArticleEntity>();
//    private String tagForTextSelection;
//    private String tagForTitleSelection;

//    public void setTagForTextSelection(String tagForTextSelection) {
//        this.tagForTextSelection = tagForTextSelection;
//    }
//
//    public void setTagForTitleSelection(String tagForTitleSelection) {
//        this.tagForTitleSelection = tagForTitleSelection;
//    }

    public List<ArticleEntity> readData(List<URL> links) throws ParserConfigurationException, SAXException, IOException, InterruptedException {

        for (URL link : links) {

            extractArticleText(link);
        }
        return articles;
    }


    private void extractArticleText(URL link) throws IOException, InterruptedException {
        article = new ArticleEntity();
        Document doc = Jsoup.connect(link.toString()).userAgent("Mozilla").get();
        System.out.println(doc.select(siteRepository.findOne("technews.bg").getTitleTag()).first().text());
        System.out.println(doc.select(siteRepository.findOne("technews.bg").getTextContentTag()).first().text());
        article.setArticleText(doc.select(siteRepository.findOne("technews.bg").getTextContentTag()).first().text());
        article.setTitle(doc.select(siteRepository.findOne("technews.bg").getTitleTag()).first().text());
        article.setLink(link.toString());

    //siteRepository.save()
        articles.add(article);
    }

}


