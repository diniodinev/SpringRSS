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
import java.util.Calendar;

@Component
public class GetTextFromPages {
    private static final Logger logger = LoggerFactory.getLogger(GetTextFromPages.class);

    @Autowired
    private ArticleService articleService;

    public ArticleService getArticleService() {
        return articleService;
    }

    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }

    private Document doc;

    /**
     * Read text for the given {@link Article}.
     * This method works only if the {@link com.musala.db.Article#getArticleText()} return null otherwise
     * do nothing
     *
     * @param article
     * @throws IOException
     */
    public void readArticleText(Article article) throws IOException {
        if (article.getArticleText() == null) {
            extractArticleInformation(getDocument(article.getLink()), article);
        }
    }

    /**
     * Extract information for the given article
     *
     * @param doc {@link Document} with the information for the the given Web Page
     * @param article object associated with the given link
     * @throws IOException
     */
    protected void extractArticleInformation(final Document doc, final Article article) throws IOException {
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
     * @param link which has to be URL compatible
     * @return null if the URL is not valid
     * @throws IOException
     */
    private Document getDocument(final String link) throws IOException {
        if (!new UrlValidator().isValid(link)) {
            logger.warn("Link {} is not a valid URL", link);
            return null;
        }
        return Jsoup.connect(link).userAgent("Mozilla").get();
    }

}


