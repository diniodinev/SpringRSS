package com.musala.service;

import com.musala.db.Article;
import com.musala.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public ArticleRepository getArticleRepository() {
        return articleRepository;
    }

    public void setArticleRepository(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public Iterable<Article> findAll() {
        return articleRepository.findAll();
    }

    @Override
    public Article findByLink(String link) {

        return articleRepository.findByLink(link);
    }

    @Override
    public Article save(Article article) {
        return articleRepository.save(article);
    }

    @Override
    public void delete(Article article) {
        articleRepository.delete(article);
    }

    @Override
    public Article findOne(long id) {
        return articleRepository.findOne(id);
    }

    @Override
    public List<Article> findByKeyWord(String keyWord) {
        return articleRepository.findByKeyWord(keyWord);
    }

}
