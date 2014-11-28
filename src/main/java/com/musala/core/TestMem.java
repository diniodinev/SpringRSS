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


import org.h2.tools.Server;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMem {


    public static void main(String... args) throws Exception {

        ApplicationContext context = new ClassPathXmlApplicationContext("RSSBean.xml");
        //RssServiceImpl rssService = context.getBean("rssServiceImpl",RssServiceImpl.class);
        ExtractUrlsFromRssXml extractUrlsFromRssXml = context.getBean("extractUrlsFromRssXml",ExtractUrlsFromRssXml.class);
        System.out.println(extractUrlsFromRssXml.getLinks());
        GetTextFromPages getTextFromPages = context.getBean("getTextFromPages",GetTextFromPages.class);
        getTextFromPages.readData(extractUrlsFromRssXml.getLinks());



        Server server = Server.createTcpServer().start();



        // now start the H2 Console here or in another process using
        // java org.h2.tools.Console -web -browser

        System.out.println("URL: jdbc:h2:" + server.getURL() + "/mem:test");

        System.out.println("Press [Enter] to stop.");
        System.in.read();

        System.out.println("Stopping server and closing the connection");
        server.stop();
    }
}