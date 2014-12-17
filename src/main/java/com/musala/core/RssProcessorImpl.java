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
import java.util.List;

@Component
public class RssProcessorImpl extends DefaultHandler implements RssProcessor {

    @Autowired
    List<ArticleObserver> observers;

    private Site site;

    @Autowired
    private ArticleInfo articleInfo;

    private StringBuilder text = new StringBuilder();

    private String siteNameKey;

    private TagType CURRENT_TAG;

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

    @Override
    public void startDocument() {
    }

    @Override
    public void endDocument() {
    }

    @Override
    public void startElement(String uri, String localName,
                             String qName, Attributes attributes) {
        text.setLength(0);
        //TODO rename RssTag ot RssLinkTag
        if (qName.equalsIgnoreCase(site.getRssTag())) {
            CURRENT_TAG = TagType.LINK;
        }

        if (qName.equalsIgnoreCase(site.getCategoryTag())) {
            CURRENT_TAG = TagType.CATEGORY;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (CURRENT_TAG != null) {
            articleInfo.setCategoryName(text.toString());
            articleInfo.setTagType(CURRENT_TAG);
            articleInfo.setSite(site);
            notifyAllObservers();
        }
        CURRENT_TAG = null;
    }

    @Override
    public void characters(char ch[], int start, int length) {
        if (text != null) {
            text.append(ch, start, length);
        }
    }

    public void processRss(Site site) {
        this.site = site;
        readData();
    }

    private void readData() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = null;
        try {
            parser = factory.newSAXParser();
            parser.parse(new InputSource(new URL(site.getRssLink()).openStream()), this);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void register(ArticleObserver observer) {
        if (observer == null) {
            throw new RuntimeException(ErrorMessages.OBSERVER_MUST_NOT_BE_NULL);
        }
        observers.add(observer);
    }

    @Override
    public void unRegister(ArticleObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyAllObservers() {
        for (ArticleObserver articleObserver : observers) {
            articleObserver.update();
        }
    }

    @Override
    public ArticleInfo getUpdate() {
        return articleInfo;
    }

    public List<ArticleObserver> getObservers() {
        return observers;
    }

    public void setObservers(List<ArticleObserver> observers) {
        this.observers = observers;
    }
}
