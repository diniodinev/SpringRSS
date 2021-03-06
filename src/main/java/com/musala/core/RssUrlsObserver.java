package com.musala.core;
 /*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
  * Created by dinyo.dinev on 2014.
 */


import com.musala.db.Article;
import com.musala.db.Category;
import com.musala.service.ArticleService;
import com.musala.service.CategoryService;
import org.apache.commons.validator.routines.UrlValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
public class RssUrlsObserver implements ArticleObserver {
    private static final Logger logger = LoggerFactory.getLogger(RssUrlsObserver.class);

    //Current link which RSSReader finds from the rss. currentLink has to be followed by zero or more categories
    private String currentLink;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private GetTextFromPages getTextFromPages;

    private ArticleInfo articleInfo;

    public ArticleService getArticleService() {
        return articleService;
    }

    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }

    public CategoryService getCategoryService() {
        return categoryService;
    }

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public GetTextFromPages getGetTextFromPages() {
        return getTextFromPages;
    }

    public void setGetTextFromPages(GetTextFromPages getTextFromPages) {
        this.getTextFromPages = getTextFromPages;
    }

    @Override
    @Transactional
    public void update(ArticleInfo articleInfo) {

        if (articleInfo.getTagType() == TagType.CATEGORY) {
            if (articleInfo.getCategoryName() != null && !articleInfo.getCategoryName().isEmpty() && articleInfo.getCategoryName() != null) {
                Article article = articleService.findByLink(currentLink);
                Category category = categoryService.findByCategoryName(articleInfo.getCategoryName());
                category.getArticles().add(article);
                article.getCategories().add(category);
            }
        }
        if (articleInfo.getTagType() == TagType.LINK) {
            UrlValidator defaultValidator = new UrlValidator();
            if (defaultValidator.isValid(articleInfo.getCategoryName())) {//getCategoryName() have URL information

                //TODO rename CategoryName to categoryTag
                Article article = new Article(articleInfo.getCategoryName(), articleInfo.getSite());
                try {
                    getTextFromPages.readArticleText(article);
                } catch (IOException e) {
                    articleService.delete(article);
                    logger.warn("For the article %s following error occurs.", e);
                }
                //Check for null category
                checkForArticleWithoutCategories();

                currentLink = articleInfo.getCategoryName();

                //Search if there is article with the current link. This is done because
                //If there are link with invalid information, they will not be persisted
                if (articleService.findByLink(articleInfo.getCategoryName()) == null) {
                    currentLink = null;
                }
            } else {
                throw new RuntimeException(ErrorMessages.INVALID_URL_FROM_RSS + articleInfo.getCategoryName());
            }
        }
    }

    /**
     * Check if previous Article has no associated categories. If so add default category
     */
    private void checkForArticleWithoutCategories() {
        if (currentLink != null) {
            Article previousArticle = articleService.findByLink(currentLink);
            if (previousArticle.getCategories().size() == 0) {
                Category category = categoryService.findByCategoryName(Categories.NONE.toString());
                previousArticle.getCategories().add(category);
                category.getArticles().add(previousArticle);
            }
        }
    }
}
