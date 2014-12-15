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

public interface CategoryService {
    /**
     * Save <br>category</br> in the DB.
     * If category exists, nothing is added to the DB and the corresponding Category object
     * will be returned.
     * If category do no exist, it will be added to the DB and the added Category object will
     * be returned.
     * @param categoryName which will be saved to the DB
     * @return object corresponding to the input categoryName
     */
    public Category save(String categoryName);

    public Category save(Category category);

    public Category findByCategoryName(String categoryName);
}
