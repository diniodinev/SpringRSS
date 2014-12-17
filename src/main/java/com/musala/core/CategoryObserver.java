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

import com.musala.db.Category;
import com.musala.service.CategoryService;
import com.musala.service.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class CategoryObserver implements ArticleObserver {

    @Autowired
    private RssProcessor rssProcessor;

    @Autowired
    private CategoryService categoryService;

    public CategoryObserver() {
    }

    @Override
    public void update() {
        ArticleInfo articleInfo;
        if (rssProcessor != null) {
            articleInfo = rssProcessor.getUpdate();
            if (articleInfo.getTagType() == TagType.CATEGORY) {
                if (articleInfo.getCategoryName() != null && !articleInfo.getCategoryName().isEmpty()) {
                    categoryService.save(articleInfo.getCategoryName());
                }
            }
        } else {
            new RuntimeException(ErrorMessages.UPDATE_ON_NULL_OBSERVER_NOT_ALLOW);
        }
    }

    @PostConstruct
    private void addDefaultCategory() {
        categoryService.save(new Category("none"));
    }

    public RssProcessor getRssProcessor() {
        return rssProcessor;
    }

    public void setRssProcessor(RssProcessor rssProcessor) {
        this.rssProcessor = rssProcessor;
    }

    public CategoryService getCategoryService() {
        return categoryService;
    }

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
}
