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
import com.musala.service.SiteService;
import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.SQLException;

@Component
public class RssManager {
    private static final Logger logger = LoggerFactory.getLogger(RssManager.class);

    @Autowired
    SiteService siteService;

    @Autowired
    RssProcessor subject;

    @PostConstruct
    public void readArticles() {
        for (Site rssFeedSite : siteService.findAll()) {
            subject.processRss(rssFeedSite);
        }

        //TODO For testing purposes only, to be deleted
        Server server = null;
        try {
            server = Server.createTcpServer().start();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        logger.info("Server started and connection is open.");
        logger.info("URL: jdbc:h2:" + server.getURL() + "/mem:test");
    }
}
