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


import com.musala.db.Site;
import com.musala.service.ArticleService;
import com.musala.service.CategoryService;
import com.musala.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

//TODO Change name to manager
@Component
@Qualifier("rssManager")
public class RssManager {

    @Autowired
    SiteService siteService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ArticleService articleService;

    @Autowired
    RssProcessorImpl subject;

    @Autowired
    GetTextFromPages getTextFromPages;

    @PostConstruct
    public void readArticles() {
        for (Site rssFeedSite : siteService.findAll()) {
            System.out.println("From rssFeed" + rssFeedSite.getSiteName());
//            getTextFromPages.setSiteName(rssFeedSite.getSiteName());
//
//            subject.setSiteNameKey(rssFeedSite.getSiteName());
//            //TODO: remove
//            new CategoryObserver(subject, categoryService);
//            new RssUrlsObserver(subject, getTextFromPages);
//            subject.addItemsToObservers();

            subject.processRss(rssFeedSite);

        }
    }
}
