package com.musala.service;

import com.musala.db.ArticleEntity;
import com.musala.db.SiteEntity;
import com.musala.repository.ArticleRepository;
import com.musala.repository.SiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public Iterable<ArticleEntity> findAll() {
        return articleRepository.findAll();
    }
}
