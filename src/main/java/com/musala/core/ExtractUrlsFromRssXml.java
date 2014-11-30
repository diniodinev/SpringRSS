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
import javax.annotation.PreDestroy;
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
    //tag level is for removing root page from the links results
    private int tagLevel;

    @Autowired
    private SiteRepository siteRepository;

    private StringBuilder text;

    private String siteNameKey;

    public String getSiteNameKey() {
        return siteNameKey;
    }

    public void setSiteNameKey(String siteNameKey) {
        this.siteNameKey = siteNameKey;
    }

    public void readData() throws ParserConfigurationException, SAXException, IOException {
        links.clear();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        parser.parse(new InputSource(new URL(siteRepository.findOne(siteNameKey).getRssLink()).openStream()), this);
    }

    @Override
    public void startDocument() {
    }

    @Override
    public void endDocument() {
        tagLevel = 0;
    }

    @Override
    public void startElement(String uri, String localName,
                             String qName, Attributes attributes) {
        ++tagLevel;
        if (qName.equalsIgnoreCase(siteRepository.findOne(siteNameKey).getRssTag())) {
            text = new StringBuilder();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        text = null;
    }

    @Override
    public void characters(char ch[], int start, int length) {
        if (text != null && tagLevel >= 8) {
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
}