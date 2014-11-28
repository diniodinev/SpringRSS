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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

@Component
@Qualifier("rssController")
public class RssController {

    @Autowired
    ExtractUrlsFromRssXml extractUrlsFromRssXml;

    @Autowired
    GetTextFromPages getTextFromPages;

    public void initiatePopulation() throws InterruptedException, ParserConfigurationException, SAXException, IOException {
        extractUrlsFromRssXml.setSiteNameKey("computerworld.bg");
        getTextFromPages.setSiteName("computerworld.bg");

        getTextFromPages.readData(extractUrlsFromRssXml.getLinks());

        extractUrlsFromRssXml.setSiteNameKey("technews.bg");
        getTextFromPages.setSiteName("technews.bg");

        getTextFromPages.readData(extractUrlsFromRssXml.getLinks());

        for (ArticleEntity e : getTextFromPages.getArticleRepository().findAll()) {
            System.out.println(e);
        }
    }
}
