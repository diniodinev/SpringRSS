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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
public class RssUrlsObserver implements ArticleObserver {

    //Current link which RSSReader finds from the rss. currentLink has to be followed by zero or more categories
    private String currentLink;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private GetTextFromPages getTextFromPages;

    private ArticleInfo articleInfo;

    @Autowired
    private RssProcessor rssProcessor;

    @Override
    @Transactional
    public void update() {
        articleInfo = rssProcessor.getUpdate();

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
            if (defaultValidator.isValid(articleInfo.getCategoryName())) {
                //TODO rename CategoryName to categoryTag
                Article article = new Article(articleInfo.getCategoryName(), articleInfo.getSite());
                getTextFromPages.readArticleText(article);
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

    public RssProcessor getRssProcessor() {
        return rssProcessor;
    }

    public void setRssProcessor(RssProcessor rssProcessor) {
        this.rssProcessor = rssProcessor;
    }

}
