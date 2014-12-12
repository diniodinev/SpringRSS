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

@Component
@Qualifier("rssController")
public class RssController {

    @Autowired
    SiteService siteService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ArticleService articleService;

    @Autowired
    RssExtractorSubject subject;

    @Autowired
    GetTextFromPages getTextFromPages;


    public void initiatePopulation() {
        for (Site rssFeedSite : siteService.findAll()) {
            getTextFromPages.setSiteName(rssFeedSite.getSiteName());

            subject.setSiteNameKey(rssFeedSite.getSiteName());
            new CategoryObserver(subject, categoryService);
            new RssUrlsObserver(subject, getTextFromPages);
            subject.addItemsToObservers();
        }
    }
}
