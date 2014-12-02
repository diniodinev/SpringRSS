package com.musala.service; 
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

import com.musala.db.CategoryEntity;
import com.musala.db.SiteEntity;
import com.musala.repository.ArticleRepository;
import com.musala.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryEntity findOne(Long id) {
        return categoryRepository.findOne(id);
    }

    public CategoryEntity save(CategoryEntity category) {
        return categoryRepository.save(category);
    }

}
