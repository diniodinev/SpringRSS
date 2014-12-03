package com.musala.core;

import com.musala.db.Article;
import com.musala.db.Category;
import com.musala.db.Site;
import com.musala.repository.ArticleRepository;
import com.musala.repository.CategoryRepository;
import com.musala.repository.SiteRepository;
import com.musala.service.CategoryService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class GetTextFromPages {
    @Autowired
    private SiteRepository siteRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private String siteName;

    private Map<URL, Set<String>> articlesCategories;

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    @Transactional
    public void readData(Map<URL, Set<String>> articlesCategories) {

        this.articlesCategories = articlesCategories;

        for (URL link : articlesCategories.keySet()) {
            try {
                extractArticleText(link);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void extractArticleText(URL link) throws IOException {
        //article = articleRepository.save(new Article());

        Document doc = Jsoup.connect(link.toString()).userAgent("Mozilla").get();

        System.out.println(doc.select(siteRepository.findOne(siteName).getTextContentTag()).first().text());

        Site site = siteRepository.findOne(siteName);
        String articleText = doc.select(site.getTextContentTag()).first().text();
        String articleTitle = doc.select(site.getTitleTag()).first().text();

//        article.setSite(siteRepository.findOne(siteName));
//        article.setArticleText(doc.select(siteRepository.findOne(siteName).getTextContentTag()).first().text());
//        article.setTitle(doc.select(siteRepository.findOne(siteName).getTitleTag()).first().text());
//        article.setLink(link.toString());

        Article article = new Article(link.toString(), articleText, articleTitle, null, site);
        articleRepository.save(article);

        for (String categoryName : articlesCategories.get(link)) {
            Category category = categoryRepository.findByCategoryName(categoryName);
            if (categoryName == null) {
                category = categoryRepository.save(category);
            }
            category.getArticles().add(article);
            article.getCategories().add(category);
        }


        //TODO: Add categorytag to the article table
        //article.setCategories(categoryList);

        //TODO: Remove List return statement

    }

    public ArticleRepository getArticleRepository() {
        return articleRepository;
    }

}


