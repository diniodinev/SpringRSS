package com.musala.core;

import com.musala.db.Article;
import com.musala.db.Category;
import com.musala.repository.ArticleRepository;
import com.musala.repository.SiteRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
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

    private String siteName;

    Article article;
    List<Article> articles = new ArrayList<Article>();



    private Map<URL, Set<String>> articlesCategories;

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public List<Article> readData(Map<URL, Set<String>> articlesCategories) {

        this.articlesCategories = articlesCategories;

        for (URL link : articlesCategories.keySet()) {
            try {
                extractArticleText(link);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return articles;
    }


    private void extractArticleText(URL link) throws IOException, InterruptedException {
        article = new Article();
        Document doc = Jsoup.connect(link.toString()).userAgent("Mozilla").get();
        System.out.println(doc.select(siteRepository.findOne(siteName).getTextContentTag()).first().text());
        article.setSite(siteRepository.findOne(siteName));
        article.setArticleText(doc.select(siteRepository.findOne(siteName).getTextContentTag()).first().text());
        article.setTitle(doc.select(siteRepository.findOne(siteName).getTitleTag()).first().text());
        article.setLink(link.toString());

        List<Category> cat = new ArrayList<Category>();
        for (String category : articlesCategories.get(link)) {
            cat.add(new Category(category));
        }
        article.setCategories(cat);
        //TODO: Add category to the article
        //article.setCategories(categoryList);

        //TODO: Remove List return statement
        articleRepository.save(article);
        System.out.println();
        articles.add(article);
    }

    public ArticleRepository getArticleRepository() {
        return articleRepository;
    }

}


