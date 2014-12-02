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
import com.musala.repository.ArticleRepository;
import com.musala.service.CategoryService;
import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMem {
    public static void main(String... args) throws Exception {


        ApplicationContext context = new ClassPathXmlApplicationContext("RSSBean.xml");

        RssController rssController = context.getBean("rssController",RssController.class);
        rssController.initiatePopulation();



        Server server = Server.createTcpServer().start();
        System.out.println("Server started and connection is open.");
        System.out.println("URL: jdbc:h2:" + server.getURL() + "/mem:test");

//        ExtractUrlsFromRssXml extractUrlsFromRssXml = context.getBean("extractUrlsFromRssXml", ExtractUrlsFromRssXml.class);
//        GetTextFromPages getTextFromPages = context.getBean("getTextFromPages", GetTextFromPages.class);
//
//        extractUrlsFromRssXml.setSiteNameKey("computerworld.bg");
//        getTextFromPages.setSiteName("computerworld.bg");
//
//
//        getTextFromPages.readData(extractUrlsFromRssXml.getLinks());
//
//
//        for (ArticleEntity e : getTextFromPages.getArticleRepository().findAll()) {
//            System.out.println(e);
//        }


    }
}