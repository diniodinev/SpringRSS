package com.musala.core;

import com.musala.db.Article;
import com.musala.service.ArticleService;
import org.apache.commons.validator.routines.UrlValidator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GetTextFromPages {
    @Autowired
    private ArticleService articleService;

    private Document doc;

    public void readArticleText(Article article) {
        if (article.getArticleText() == null) {
            extractArticleInformation(article.getLink(), article);
        }
    }

    protected void extractArticleInformation(final String link, final Article article) {
        try {
            doc = getDocument(link);
        } catch (IOException e) {
            //TODO add runtime error
            e.printStackTrace();
        }

        if (doc.select(article.getSite().getTextContentTag()).first() != null) {
            //System.out.println(doc.select(article.getSite().getTextContentTag()).first().text());
            article.setArticleText(doc.select(article.getSite().getTextContentTag()).first().text());
            article.setTitle(doc.select(article.getSite().getTitleTag()).first().text());
            articleService.save(article);
        } else {
            // TODO add some logging message that the current article has incorrect information
        }
    }

    /**
     * Return Document for the given link which has to be URL compatible, or
     * html String page which will be transformed to Document.
     *
     * @param link
     * @return
     * @throws IOException
     */
    private Document getDocument(final String link) throws IOException {
        if (!new UrlValidator().isValid(link)) {
            return Jsoup.parse(link);
        }
        return Jsoup.connect(link).userAgent("Mozilla").get();
    }
}


