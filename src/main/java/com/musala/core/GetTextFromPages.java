package com.musala.core;

import com.musala.db.Article;
import com.musala.db.Category;
import com.musala.db.Site;
import com.musala.repository.ArticleRepository;
import com.musala.repository.CategoryRepository;
import com.musala.repository.SiteRepository;
import com.musala.service.CategoryServiceImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
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

        System.out.println("------------------------------>All urls");

        for (URL link : articlesCategories.keySet()) {
            System.out.println("Links " + link);
        }
        for (URL link : articlesCategories.keySet()) {
            try {
                extractArticleText(link);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        articlesCategories.clear();
    }

    private void extractArticleText(URL link) throws IOException {
        Document doc = Jsoup.connect(link.toString()).userAgent("Mozilla").get();

        System.out.println("URL:" + link.toString());
        System.out.println("Site name--->" + siteName);
        System.out.println("siteRepository.findOne(siteName)--->" + siteRepository.findOne(siteName));
        System.out.println("siteRepository.findOne(siteName).getTextContentTag()--->" + siteRepository.findOne(siteName).getTextContentTag());
        System.out.println(doc.select(siteRepository.findOne(siteName).getTextContentTag()).first().text());

        Site site = siteRepository.findOne(siteName);
        String articleText = doc.select(site.getTextContentTag()).first().text();
        String articleTitle = doc.select(site.getTitleTag()).first().text();

        //Create article without categories
        Article article = new Article(link.toString(), articleText, articleTitle, null, site);
        articleRepository.save(article);

        for (String categoryName : articlesCategories.get(link)) {
            Category category = categoryServiceImpl.findByCategoryName(categoryName);
            if (categoryName == null) {
                category = categoryServiceImpl.save(new Category(categoryName));
            }
            category.getArticles().add(article);
            article.getCategories().add(category);
        }
        //TODO: Add categorytag to the article table
        //article.setCategories(categoryList);
    }



    public ArticleRepository getArticleRepository() {
        return articleRepository;
    }

}


