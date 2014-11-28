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


import com.musala.core.ExtractUrlsFromRssXml;
import com.musala.core.GetTextFromPages;
import com.musala.db.ArticleEntity;
import com.musala.db.SiteEntity;
import com.musala.repository.SiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

@Component
@Qualifier("populateData")
public class PopulateData {

    @Autowired
    private SiteRepository siteRepository;

    @PostConstruct
    public void populateSiteInformation() {
        final String[] technews = {"technews.bg", "http://technews.bg/feed", "link", "h1", "div.entry-content"};
        final String[] computerWorld = {"computerworld.bg", "http://feeds.feedburner.com/computerworldbgnews?format=xml", "feedburner:origLink", "h1", "div.article_text"};

        populateSiteInfo(technews);
        populateSiteInfo(computerWorld);
    }

    public void populateSiteInfo(String[] siteInformation) {
        SiteEntity siteEntity = new SiteEntity();
        siteEntity.setSiteName(siteInformation[0]);
        siteEntity.setRssLink(siteInformation[1]);
        siteEntity.setRssTag(siteInformation[2]);
        siteEntity.setTitleTag(siteInformation[3]);
        siteEntity.setTextContentTag(siteInformation[4]);

        siteRepository.save(siteEntity);

        System.out.println(siteRepository.count());
    }
}
