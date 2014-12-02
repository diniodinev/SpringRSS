package com.musala.core;


import com.musala.db.Article;
import com.musala.repository.SiteRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.net.MalformedURLException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/RSSBean.xml")
@EnableAutoConfiguration
public class ArticleTest {
    @Autowired
    SiteRepository repository;

    @Test
    public void test() throws MalformedURLException {
        Article article = new Article();
        article.setArticleText("some text");
        article.setTitle("some title");
        article.setLink("http://www.google.bg");

//        repository.save(article);
//
//        ArticleEntity getArticle = repository.findOne(article.getId());
//        System.out.println(getArticle.getTitle());


    }
}

