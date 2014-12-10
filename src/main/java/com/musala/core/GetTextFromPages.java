package com.musala.core;

import com.musala.db.Article;
import com.musala.db.Category;
import com.musala.db.Site;
import com.musala.repository.ArticleRepository;
import com.musala.repository.CategoryRepository;
import com.musala.repository.SiteRepository;
import com.musala.service.CategoryServiceImpl;
import org.apache.commons.validator.routines.UrlValidator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Set;

@Component
public class GetTextFromPages {
    @Autowired
    private SiteRepository siteRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CategoryServiceImpl categoryServiceImpl;

    private String siteName;

    private Map<String, Set<String>> articlesCategories;

    private Document doc;

    @Transactional
    public void readData(Map<String, Set<String>> articlesCategories) {

        this.articlesCategories = articlesCategories;

        for (String link : articlesCategories.keySet()) {
            try {
                addCategoriesToArticle(link, extractArticleText(link.toString()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    protected Article extractArticleText(String link) throws IOException {
        doc = getDocument(link);

        Article article = createArticleWithoutCategories(link);
        System.out.println(doc.select(siteRepository.findOne(siteName).getTextContentTag()).first().text());
        articleRepository.save(article);

        return article;
    }

    /**
     * For the given link from articlesCategories map get categories and check if they presents in the CATEGORIES
     * table. If not, the missing category will be added to the database.
     * All categories for the specified link will be added to <br>article<br/> object.
     *
     * @param link    from which categories will be searching
     * @param article object to whom categories will be added
     * @return input article object populated with corresponding categories
     * @throws MalformedURLException
     */

    protected Article addCategoriesToArticle(String link, Article article) throws MalformedURLException {
        for (String categoryName : articlesCategories.get(link)) {
            Category category = categoryServiceImpl.findByCategoryName(categoryName);
            if (categoryName == null) {
                category = categoryServiceImpl.save(new Category(categoryName));
            }
            category.getArticles().add(article);
            article.getCategories().add(category);
        }
        return article;
    }

    private Article createArticleWithoutCategories(String link) {
        System.out.println(siteRepository.findOne(siteName));
        Site site = siteRepository.findOne(siteName);
        System.out.println(site.getTextContentTag());
        String articleText = doc.select(site.getTextContentTag()).first().text();
        String articleTitle = doc.select(site.getTitleTag()).first().text();

        //Create article without categories
        return new Article(link, articleText, articleTitle, null, site);
    }

    /**
     * Return Document for the given link which has to be URL compatible, or
     * html String page which will be transformed to Document.
     *
     * @param link
     * @return
     * @throws IOException
     */
    private Document getDocument(String link) throws IOException {
        if (!new UrlValidator().isValid(link)) {
            return Jsoup.parse(link);
        }
        return Jsoup.connect(link).userAgent("Mozilla").get();
    }

    public ArticleRepository getArticleRepository() {
        return articleRepository;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }
}


