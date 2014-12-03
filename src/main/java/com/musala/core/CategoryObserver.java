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
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class CategoryObserver extends SaxObserver {

    private Set<String> categories = new HashSet<String>();

    private CategoryService categoryService;

    public CategoryObserver(RssExtractorSubject subject, CategoryService categoryService) {
        this.subject = subject;
        subject.attach(this);
        this.categoryService = categoryService;
    }

    public void update(String category) {
        if (category != null && !category.isEmpty()) {
            categories.add(category);
        }
    }


    //Do it at the end
    @Override
    public void updateAll() {
        for (String category : categories) {
            categoryService.save(new Category(category));
        }
    }

    @Override
    public void updateAll(String category, TagContent tagContent) {
        if(tagContent==TagContent.CATEGORY){
            if (category != null && !category.isEmpty()) {
                categories.add(category);
            }
        }
    }

    public CategoryObserver() {
    }

    public CategoryService getCategoryService() {
        return categoryService;
    }

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
}
