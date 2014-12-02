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

import com.musala.db.Category;
import com.musala.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category findOne(Long id) {
        return categoryRepository.findOne(id);
    }

    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    /**
     * Searches for presence of <b>categoryName</b> in CategoryRepository. If it is  not part
     * of the repository, new Category entity is created and saved to CategoryRepository.
     * Otherwise already existing Category entity object will be returned.
     *
     * @param categoryName
     * @return
     */
    public Category finadCategory(String categoryName) {
        for (Category category : categoryRepository.findAll()) {
            if (category.getCategoryName().equals(categoryName)) {
                return category;
            }
        }
        return categoryRepository.save(new Category(categoryName));
    }
}
