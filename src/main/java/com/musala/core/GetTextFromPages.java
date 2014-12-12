package com.musala.core;

import com.musala.db.Article;
import com.musala.db.Category;
import com.musala.db.Site;
import com.musala.repository.ArticleRepository;
import com.musala.repository.SiteRepository;
import com.musala.service.CategoryService;
import org.apache.commons.validator.routines.UrlValidator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;
import java.util.Set;

@Component
public class GetTextFromPages {
    @Autowired
    private SiteRepository siteRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CategoryService categoryService;

    private String siteName;

    private Map<String, Set<String>> articlesCategories;

    private Document doc;

    @Transactional
    public void readData(final Map<String, Set<String>> articlesCategories) {

        this.articlesCategories = articlesCategories;

        for (String link : articlesCategories.keySet()) {
            try {
                addCategoriesToArticle(link, extractArticleText(link.toString()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    protected Article extractArticleText(final String link) throws IOException {
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

    protected Article addCategoriesToArticle(final String link, final Article article) throws MalformedURLException {
        if (articlesCategories.get(link).size() == 0) {
            articlesCategories.get(link).add("none");
        }
        for (String categoryName : articlesCategories.get(link)) {
            Category category = categoryService.findByCategoryName(categoryName);
            //TODO fix bug with sites without categories items

            category.getArticles().add(article);
            article.getCategories().add(category);
        }
        return article;
    }

    private Article createArticleWithoutCategories(final String link) {
        Site site = siteRepository.findOne(siteName);
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
    private Document getDocument(final String link) throws IOException {
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

    public void setSiteName(final String siteName) {
        this.siteName = siteName;
    }
}


