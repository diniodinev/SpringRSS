package com.musala.core;

import com.musala.db.ArticleEntity;
import com.musala.repository.ArticleRepository;
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

    @Autowired
    private ArticleRepository articleRepository;

    private String siteName;

    ArticleEntity article;
    List<ArticleEntity> articles = new ArrayList<ArticleEntity>();

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public List<ArticleEntity> readData(List<URL> links) throws ParserConfigurationException, SAXException, IOException, InterruptedException {
        for (URL link : links) {
                extractArticleText(link);
        }
        return articles;
    }


    private void extractArticleText(URL link) throws IOException, InterruptedException {
        article = new ArticleEntity();
        Document doc = Jsoup.connect(link.toString()).userAgent("Mozilla").get();
        System.out.println(doc.select(siteRepository.findOne(siteName).getTextContentTag()).first().text());
        article.setSite(siteRepository.findOne(siteName));
        article.setArticleText(doc.select(siteRepository.findOne(siteName).getTextContentTag()).first().text());
        article.setTitle(doc.select(siteRepository.findOne(siteName).getTitleTag()).first().text());
        article.setLink(link.toString());

        //TODO: Remove List return statement
        articleRepository.save(article);
        System.out.println();
        articles.add(article);
    }

    public ArticleRepository getArticleRepository() {
        return articleRepository;
    }

}


