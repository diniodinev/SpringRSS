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
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.filter.ElementFilter;
import org.jdom2.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;
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

    @Scheduled(fixedRate = 300000)
    @Transactional
    public void printMe() {
        logger.info("Cron task is started");

        for (Site rssFeedSite : siteService.findAll()) {
            logger.info("--------------> Reading information for site: " + rssFeedSite.getRssLink());
            SAXBuilder builder = new SAXBuilder();
            Document doc = null;

            try {
                doc = builder.build(new URL(rssFeedSite.getRssLink()));
                if (rssFeedSite.getLastVisitDateTag() != null) {
                    Element root = doc.getRootElement();
                    ElementFilter filter = new ElementFilter(rssFeedSite.getLastVisitDateTag());

                    String currentLastVisitedDate = null;

                    for (Element c : root.getDescendants(filter)) {
                        currentLastVisitedDate = c.getTextNormalize();
                    }

                    if (currentLastVisitedDate != null && currentLastVisitedDate.equals(rssFeedSite.getLastVisitDate())) {
                        logger.info("--------------> Rss {} is not changed", rssFeedSite.getRssLink());
                    } else {
                        logger.info("--------------> Rss {} is changed", rssFeedSite.getRssLink());
                        rssFeedSite.setLastVisitDate(currentLastVisitedDate);
                        subject.processRss(rssFeedSite);
                    }
                }
            } catch (JDOMException | IOException e) {
                logger.warn("Error occurred during reading of rss", e);
            }
        }
    }
}

