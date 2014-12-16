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

//TODO: use autowire
@Component
public class CategoryObserver implements ArticleObserver {

    private RssProcessor rssProcessor;

    //private Set<String> categories = new HashSet<String>();

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

    @Override
    public void setSubject(RssProcessor rssProcessor) {
        this.rssProcessor = rssProcessor;
    }

    //TODO checi if it is added once
    @PostConstruct
    private void addDefaultCategory() {
        categoryService.save(new Category("none"));
    }
    //    public CategoryObserver(final RssProcessorImpl subject, final CategoryService categoryService) {
//        this.subject = subject;
//        subject.attach(this);
//        this.categoryService = categoryService;
//        categories.add("none");
//    }

//    @Override
//    public void updateAll() {
//        for (String category : categories) {
//            categoryService.save(new Category(category));//TODO check if catefory exists
//        }
//    }
//
//    @Override
//    public void update(final ArticleInfo articleInfo) {
//        if (articleInfo.getTagType() == TagType.CATEGORY) {
//            if (articleInfo.getCategoryName() != null && !articleInfo.getCategoryName().isEmpty()) {
//                categories.add(articleInfo.getCategoryName());
//            }
//        }
//    }


    public CategoryService getCategoryService() {
        return categoryService;
    }

    public void setCategoryServiceImpl(final CategoryServiceImpl categoryService) {
        this.categoryService = categoryService;
    }
}
