package com.musala.service;

import com.musala.db.Article;
import com.musala.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public Iterable<Article> findAll() {
        return articleRepository.findAll();
    }
}
