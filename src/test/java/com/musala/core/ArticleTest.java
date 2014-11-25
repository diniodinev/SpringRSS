package com.musala.core;


import com.musala.db.ArticleEntity;
import com.musala.repositories.ArticleRepositories;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.net.MalformedURLException;
import java.net.URL;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/RSSBean.xml")
public class ArticleTest {
    @Autowired
    ArticleRepositories repository;

    @Test
    public void test() throws MalformedURLException {
        ArticleEntity article = new ArticleEntity();
        article.setText("some text");
        article.setTitle("some title");
        article.setLink(new URL("http://www.google.bg"));

        repository.save((Iterable<Article>) article);

        Article getArticle = repository.findOne(article.getId());
        System.out.println(getArticle.getTitle());


    }
}

