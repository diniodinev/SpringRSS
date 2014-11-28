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

import com.musala.db.SiteEntity;
import com.musala.repository.SiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.annotation.PostConstruct;
import javax.persistence.Entity;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Component
public class ExtractUrlsFromRssXml extends DefaultHandler {
    private List<URL> links = new ArrayList<URL>();

    @Autowired
    private SiteRepository siteRepository;

    private StringBuilder text;

    public void readData() throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        parser.parse(new InputSource(new URL(siteRepository.findOne("technews.bg").getRssLink()).openStream()), this);
    }

    @Override
    public void startDocument() {
    }

    @Override
    public void startElement(String uri, String localName,
                             String qName, Attributes attributes) {
        if (qName.equalsIgnoreCase(siteRepository.findOne("technews.bg").getRssTag())) {//feedburner:origLink
            text = new StringBuilder();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        text = null;
    }

    @Override
    public void characters(char ch[], int start, int length) {
        if (text != null) {
            text.append(ch, start, length);
            try {
                links.add(new URL(text.toString()));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<URL> getLinks() {
        try {
            readData();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return links;
    }

    //@PostConstruct
    public void populateSiteInformation() {
        final String[] technews = {"technews.bg","http://technews.bg/feed","link","h1","div.entry-content"};
        final String[] computerWorld = {"computerworld.bg","http://feeds.feedburner.com/computerworldbgnews?format=xml","feedburner:origLink","h1","div.article_text"};

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
        System.out.println(siteRepository.findOne("technews.bg"));
    }
}