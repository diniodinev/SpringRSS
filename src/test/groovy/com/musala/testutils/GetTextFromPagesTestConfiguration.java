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

import com.musala.core.ArticleInfo;
import com.musala.core.ArticleObserver;
import com.musala.core.CategoryObserver;
import com.musala.core.GetTextFromPages;
import com.musala.core.LastVisitDateObserver;
import com.musala.core.RssProcessor;
import com.musala.core.RssProcessorImpl;
import com.musala.core.RssUrlsObserver;
import com.musala.repository.ArticleRepository;
import com.musala.repository.CategoryRepository;
import com.musala.repository.SiteRepository;
import com.musala.service.ArticleService;
import com.musala.service.ArticleServiceImpl;
import com.musala.service.CategoryService;
import com.musala.service.CategoryServiceImpl;
import com.musala.service.SiteService;
import com.musala.service.SiteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ComponentScan(basePackages = {"com.musala.core", "com.musala.repository"})
@EnableJpaRepositories
public class GetTextFromPagesTestConfiguration {
    //TODO not do it right must return direct Repository implementation but it is only interface
    //For next 3 methods
//    @Bean
//    public SiteRepository siteRepository() {
//        return new SiteServiceImpl().getSiteRepository();
//    }
//
//    @Bean
//    public CategoryRepository categoryRepository() {
//        return new CategoryServiceImpl().getCategoryRepository();
//    }
//
//    @Bean
//    public ArticleRepository articleRepository() {
//        return new ArticleServiceImpl().getArticleRepository();
//    }

    @Bean
    public ArticleService articleService() {
        return new ArticleServiceImpl();
    }

    @Bean
    CategoryService categoryService() {
        return new CategoryServiceImpl();
    }

    @Bean
    SiteService siteService() {
        return new SiteServiceImpl();
    }

    @Bean
    public ArticleInfo articleInfo() {
        return new ArticleInfo();
    }

    @Bean
    public SiteServiceImpl siteServiceImpl(){
        SiteServiceImpl siteServiceImpl = new SiteServiceImpl();
        //siteServiceImpl.setSiteRepository(siteRepository());
        //siteServiceImpl.setArticleRepository(articleRepository());
        return siteServiceImpl;
    }

    @Bean
    public CategoryServiceImpl categoryServiceImpl(){
        CategoryServiceImpl categoryServiceImpl = new CategoryServiceImpl();
        //categoryServiceImpl.setCategoryRepository(categoryRepository());
        return categoryServiceImpl;
    }

    @Bean
    public ArticleServiceImpl articleServiceImpl() {
        ArticleServiceImpl articleServiceImpl = new ArticleServiceImpl();
        //articleServiceImpl.setArticleRepository(articleRepository());
        return articleServiceImpl;
    }

    @Bean
    public GetTextFromPages getTextFromPages() {
        GetTextFromPages getTextFromPages = new GetTextFromPages();
        getTextFromPages.setArticleService(articleService());
        return getTextFromPages();
    }

    @Bean
    public RssProcessor rssProcessor() {
        List<ArticleObserver> observers = new ArrayList<>();
        observers.add(categoryObserver());
        observers.add(lastVisitDateObserver());
        observers.add(rssUrlsObserver());

        RssProcessorImpl rssProcessorImpl = new RssProcessorImpl();
        rssProcessorImpl.setArticleInfo(articleInfo());
        rssProcessorImpl.setArticleService(articleService());
        rssProcessorImpl.setObservers(observers);

        return rssProcessorImpl;
    }

    @Bean
    public CategoryObserver categoryObserver() {
        CategoryObserver categoryObserver = new CategoryObserver();
        categoryObserver.setCategoryService(categoryService());
        return categoryObserver;
    }

    @Bean
    public LastVisitDateObserver lastVisitDateObserver() {
        LastVisitDateObserver lastVisitDateObserver = new LastVisitDateObserver();
        lastVisitDateObserver.setSiteService(siteService());
        return lastVisitDateObserver;
    }

    @Bean
    public RssUrlsObserver rssUrlsObserver() {
        RssUrlsObserver rssUrlsObserver = new RssUrlsObserver();
        rssUrlsObserver.setArticleService(articleService());
        rssUrlsObserver.setCategoryService(categoryService());
        rssUrlsObserver.setGetTextFromPages(getTextFromPages());
        return rssUrlsObserver;
    }


}

