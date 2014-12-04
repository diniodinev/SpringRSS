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


import com.musala.repository.CategoryRepository;
import com.musala.service.SiteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Component
public class RssExtractorSubject extends DefaultHandler {

    private List<SaxObserver> observers = new ArrayList<SaxObserver>();

    @Autowired
    SiteServiceImpl siteServiceImpl;

    private StringBuilder text = new StringBuilder();

    private String siteNameKey;

    private TagContent CURRECT_TAG;

    public StringBuilder getText() {
        return text;
    }

    public void setText(StringBuilder text) {
        this.text = text;
    }

    public String getSiteNameKey() {
        return siteNameKey;
    }

    public void setSiteNameKey(String siteNameKey) {
        this.siteNameKey = siteNameKey;
    }

    /**
     * Attach oserver to this subject
     *
     * @param observer
     */
    public void attach(SaxObserver observer) {
        observers.add(observer);
    }

    public void notifyAllObservers() {
        for (SaxObserver observer : observers) {
            observer.updateAll();
        }
    }

    public void notifyAllObservers(String tagElement, TagContent tagContent) {
        for (SaxObserver observer : observers) {
            observer.update(tagElement, tagContent);
        }
    }

    public void addItemsToObservers() {
        readData();

    }

    @Override
    public void startDocument() {
    }

    @Override
    public void endDocument() {
        notifyAllObservers();
    }

    @Override
    public void startElement(String uri, String localName,
                             String qName, Attributes attributes) {
        text.setLength(0);
        //TODO rename RssTag ot RssLinkTag
        if (qName.equalsIgnoreCase(siteServiceImpl.findOne(siteNameKey).getRssTag())) {
            CURRECT_TAG = TagContent.LINK;

        }
        //TODO add category property in the site table

        if (qName.equalsIgnoreCase(siteServiceImpl.findOne(siteNameKey).getCategoryTag())) {
            CURRECT_TAG = TagContent.CATEGORY;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (CURRECT_TAG != null) {
            notifyAllObservers(text.toString(), CURRECT_TAG);
        }
        CURRECT_TAG = null;
    }

    @Override
    public void characters(char ch[], int start, int length) {
        if (text != null) {
            text.append(ch, start, length);
        }
    }

    private void readData() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = null;

        try {
            parser = factory.newSAXParser();
            parser.parse(new InputSource(new URL(siteServiceImpl.findOne(siteNameKey).getRssLink()).openStream()), this);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
