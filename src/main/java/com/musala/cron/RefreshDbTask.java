package com.musala.cron; 
 /*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
  * Created by dinyo.dinev on 2015.
 */

import com.musala.core.RssManager;
import com.musala.core.RssProcessor;
import com.musala.db.Site;
import com.musala.service.SiteService;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.XPath;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.util.List;

@Component
@Qualifier("refreshDbTask")
public class RefreshDbTask {
    private static final Logger logger = LoggerFactory.getLogger(RefreshDbTask.class);

    @Autowired
    RssManager rssManager;

    @Autowired
    SiteService siteService;

    @Autowired
    RssProcessor subject;

    public RefreshDbTask() {
    }

    @Scheduled(fixedRate = 100000)
    public void printMe() throws ParserConfigurationException {
        logger.info("Cron task is started");
        //rssManager.readArticles();
        for (Site rssFeedSite : siteService.findAll()) {
            SAXReader reader = new SAXReader();
            Document document;
            try {
                document = reader.read(rssFeedSite.getRssLink());

                XPath expression = new Dom4jXPath("/rss/channel/item/description");
                List results = expression.selectNodes(document)
            } catch (DocumentException e) {
                logger.warn("Error processing of a DOM4J document", e);
            }


            //    subject.processRss(rssFeedSite);
        }


       );


    }

}
