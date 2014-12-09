package com.musala.service;

import com.musala.db.Article;
import com.musala.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl implements ArticleService{
    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public Iterable<Article> findAll() {
        return articleRepository.findAll();
    }

    public ArticleRepository getArticleRepository() {
        return articleRepository;
    }

    public void setArticleRepository(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }
}
