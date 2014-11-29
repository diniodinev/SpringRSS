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


import com.musala.db.ArticleEntity;
import com.musala.db.SiteEntity;
import com.musala.repository.SiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;

@Component
@Qualifier("rssController")
public class RssController {

    @Autowired
    SiteRepository siteRepository;

    @Autowired
    ExtractUrlsFromRssXml extractUrlsFromRssXml;

    @Autowired
    GetTextFromPages getTextFromPages;

    public void initiatePopulation() throws InterruptedException, ParserConfigurationException, SAXException, IOException {
//        extractUrlsFromRssXml.setSiteNameKey("computerworld.bg");
//        getTextFromPages.setSiteName("computerworld.bg");
//        getTextFromPages.readData(extractUrlsFromRssXml.getLinks());
//
//        extractUrlsFromRssXml.setSiteNameKey("technews.bg");
//        getTextFromPages.setSiteName("technews.bg");
//        getTextFromPages.readData(extractUrlsFromRssXml.getLinks());

//        extractUrlsFromRssXml.setSiteNameKey("lentata.com");
//        getTextFromPages.setSiteName("lentata.com");
//
//        getTextFromPages.readData(extractUrlsFromRssXml.getLinks());

        for (SiteEntity rssFeedSite : siteRepository.findAll()) {
            extractUrlsFromRssXml.setSiteNameKey(rssFeedSite.getSiteName());
            getTextFromPages.setSiteName(rssFeedSite.getSiteName());
            getTextFromPages.readData(extractUrlsFromRssXml.getLinks());
        }
    }
}
