package com.musala.testutils; 
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

import com.musala.core.GetTextFromPages;
import com.musala.repository.ArticleRepository;
import com.musala.repository.CategoryRepository;
import com.musala.repository.SiteRepository;
import com.musala.service.ArticleService;
import com.musala.service.ArticleServiceImpl;
import com.musala.service.CategoryServiceImpl;
import com.musala.service.SiteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.musala.core", "com.musala.repository"})
public class GetTextFromPagesTestConfiguration {
    @Autowired
    GetTextFromPages getTextFromPages;

    @Bean
    public SiteRepository siteRepository() {
        return new SiteServiceImpl().getSiteRepository();
    }

    @Bean
    public CategoryRepository categoryService() {
        return new CategoryServiceImpl().getCategoryRepository();
    }

    @Bean
    public CategoryServiceImpl categoryServiceImpl() {
        return new CategoryServiceImpl();
    }

    @Bean
    public SiteServiceImpl siteServiceImpl() {
        return new SiteServiceImpl();
    }

    @Bean
    public ArticleRepository articleRepository() {
        return new ArticleServiceImpl().getArticleRepository();
    }

    @Bean
    public ArticleService articleService() {
        return new ArticleServiceImpl();
    }
}

