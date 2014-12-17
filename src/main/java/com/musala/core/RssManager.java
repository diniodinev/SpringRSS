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
import com.musala.db.Site;
import com.musala.service.ArticleService;
import com.musala.service.CategoryService;
import com.musala.service.SiteService;
import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.SQLException;
import java.util.List;

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
    RssProcessor subject;

    @Autowired
    GetTextFromPages getTextFromPages;

    //    @Autowired
//    CategoryObserver categoryObserver;
//
//    @Autowired
//    RssUrlsObserver rssUrlsObserver;
    @Autowired
    List<ArticleObserver> observers;

    @PostConstruct
    public void readArticles() {
        for (Site rssFeedSite : siteService.findAll()) {
            System.out.println("From rssFeed " + rssFeedSite.getRssLink());
//            getTextFromPages.setSiteName(rssFeedSite.getSiteName());
//
//            subject.setSiteNameKey(rssFeedSite.getSiteName());
//            //TODO: remove
//            new CategoryObserver(subject, categoryService);
//            new RssUrlsObserver(subject, getTextFromPages);
//            subject.addItemsToObservers();
            for (ArticleObserver articleObserver : observers) {
                subject.register(articleObserver);
                articleObserver.setSubject(subject);
                //rssUrlsObserver.setSubject(subject);
                subject.processRss(rssFeedSite);
            }
            //TODO For testing purposes only, to be deletes
            Server server = null;
            try {
                server = Server.createTcpServer().start();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("Server started and connection is open.");
            System.out.println("URL: jdbc:h2:" + server.getURL() + "/mem:test");

        }
    }
}
