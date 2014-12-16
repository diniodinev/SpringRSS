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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
public class RssUrlsObserver implements ArticleObserver {

    //Current link which RSSReader finds from the rss. currentLink has to be followed be zero or more categories
    private String currentLink;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryService categoryService;

    private Map<String, Set<String>> articles = articles = new HashMap<>();

    private ArticleInfo articleInfo;

    private RssProcessor rssProcessor;

    @Override
    public void update() {
        articleInfo = rssProcessor.getUpdate();

        if (articleInfo.getTagType() == TagType.CATEGORY) {
            if (articleInfo.getCategoryName() != null && !articleInfo.getCategoryName().isEmpty() && articleInfo.getCategoryName() != null) {
                Article article = articleService.findByLink(currentLink);
                if (article != null) {
                    //TODO be sure that hte category is not null
                    Category category = categoryService.findByCategoryName(articleInfo.getCategoryName());
                    category.getArticles().add(article);
                    article.getCategories().add(category);
                } else {
                    throw new RuntimeException("Article is nullllll");
                }
            }
        }
        if (articleInfo.getTagType() == TagType.LINK) {
            UrlValidator defaultValidator = new UrlValidator();
            if (defaultValidator.isValid(articleInfo.getCategoryName())) {
                //TODO rename CategoryName to categoryTag
                articleService.save(new Article(articleInfo.getCategoryName(), articleInfo.getSite()));
                currentLink = articleInfo.getCategoryName();
            } else {
                throw new RuntimeException(ErrorMessages.INVALID_URL_FROM_RSS + articleInfo.getCategoryName());
            }
        }
    }

    @Override
    public void setSubject(RssProcessor rssProcessor) {
        this.rssProcessor = rssProcessor;

    }

//GetTextFromPages getTextFromPages;

//    public RssUrlsObserver(RssProcessorImpl subject, GetTextFromPages getTextFromPages) {
//        this.subject = subject;
//        subject.attach(this);
//        this.getTextFromPages = getTextFromPages;
//    }

//    @Override
//    public void update(ArticleInfo articleInfo) {
//        if (articleInfo.getTagType() == TagType.CATEGORY) {
//            if (articleInfo.getCategoryName() != null && !articleInfo.getCategoryName().isEmpty() && currentLink != null) {
//                Set<String> currentCategories = new HashSet<String>(articles.get(currentLink));
//                currentCategories.add(articleInfo.getCategoryName());
//                articles.put(currentLink, currentCategories);
//            }
//        }
//        if (articleInfo.getTagType() == TagType.LINK) {
//            UrlValidator defaultValidator = new UrlValidator();
//            if (defaultValidator.isValid(articleInfo.getCategoryName())) {
//                currentLink = articleInfo.getCategoryName();
//                articles.put(currentLink, new HashSet<String>());
//            }
//        }
//    }
//
//    @Override
//    public void updateAll() {
//        System.out.println("URL observer write to db:");
//        //Fill article's data
//        getTextFromPages.readData(articles);
//        articles.clear();
//    }
}
