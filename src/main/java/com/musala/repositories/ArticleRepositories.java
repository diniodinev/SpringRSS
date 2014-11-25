package com.musala.repositories; 
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


import com.musala.core.Article;
import org.h2.engine.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface ArticleRepositories extends JpaRepository<Article, Long> {
    @Override
    @Transactional(timeout = 10)
    public List<Article> findAll();
}
