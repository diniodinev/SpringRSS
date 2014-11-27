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
import com.musala.core.GetTestFromPages;
import com.musala.db.ArticleEntity;
import com.musala.db.SiteEntity;
import com.musala.repository.ArticleRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import javax.persistence.Entity;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

@Component
@Qualifier("rssServiceImpl")
public class RssServiceImpl {

    @Autowired
    private ArticleRepositories articleRepositories;

// @Autowired
// GetTestFromPages getTestFromPages;
//
// @Autowired
// ExtractUrlsFromRssXml extractUrlsFromRssXml;

    public void populateSiteInfo() {
        SiteEntity siteEntity = new SiteEntity();
        siteEntity.setRssLink("http://technews.bg/feed");
        siteEntity.setRssTag("link");
        siteEntity.setTitleTag("h1");
        siteEntity.setTextContentTag("div.entry-content");
        siteEntity.setSiteName("technews.bg");

        articleRepositories.save(siteEntity);
        articleRepositories.save(siteEntity);
        articleRepositories.save(siteEntity);
        articleRepositories.save(siteEntity);

        System.out.println(articleRepositories.count());
    }

// public void populateArticles() throws InterruptedException, ParserConfigurationException, SAXException, IOException {
//
//  for(ArticleEntity e:getTestFromPages.readData(extractUrlsFromRssXml.getLinks())){
//   System.out.println(e);
//  };
// }
}
