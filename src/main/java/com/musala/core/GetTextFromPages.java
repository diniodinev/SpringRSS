package com.musala.core;

import com.musala.db.Article;
import com.musala.service.ArticleService;
import org.apache.commons.validator.routines.UrlValidator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;

@Component
public class GetTextFromPages {
    private static final Logger logger = LoggerFactory.getLogger(GetTextFromPages.class);

    @Autowired
    private ArticleService articleService;

    private Document doc;

    public void readArticleText(Article article) throws IOException {
        if (article.getArticleText() == null) {
            extractArticleInformation(article.getLink(), article);
        }
    }

    /**
     * Extract information for the given article
     *
     * @param link    from where to construct Document object for the given Web Page
     * @param article object associated with the given link
     * @throws IOException
     */
    protected void extractArticleInformation(final String link, final Article article) throws IOException {
        try {
            doc = getDocument(link);
        } catch (IOException e) {
            throw e;
        }

        if (doc.select(article.getSite().getTextContentTag()).first() != null) {
            //System.out.println(doc.select(article.getSite().getTextContentTag()).first().text());
            article.setArticleText(doc.select(article.getSite().getTextContentTag()).first().text());
            article.setTitle(doc.select(article.getSite().getTitleTag()).first().text());
            article.setPublicationDate(Calendar.getInstance().getTime());
            articleService.save(article);
        } else {
            logger.warn("For the article with URL %s there is no matched text in tag %s", article.getLink(), article.getSite().getTextContentTag());
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


