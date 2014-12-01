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


import com.musala.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RssExtractorSubject extends DefaultHandler {

    private List<SaxObserver> observers = new ArrayList<SaxObserver>();

    @Autowired
    SiteService siteService;

    private StringBuilder text;

    private String siteNameKey;

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

    public void notifyAllObservers(String element) {
        for (SaxObserver observer : observers) {
            observer.updateAll();
        }
    }

    public void notifyCategoryObserver(String element){
        for(SaxObserver observer: observers) {
            if(observer instanceof CategoryObserver){
                observer.update(element);
            }
        }
    }

    public void notifyRssUrlsObserver(String element){
        for(SaxObserver observer: observers) {
            if(observer instanceof RssUrlsObserver){
                observer.update(element);
            }
        }

    }

    public void addNewElement(String element) {
        System.out.println("Call category observer");
        notifyCategoryObserver("category News "+ element);

        System.out.println("Call rssUrls observer");
        notifyCategoryObserver("som url"+ element);
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
        System.out.println(qName);
        if (qName.equalsIgnoreCase(siteService.findOne(siteNameKey).getRssTag())) {
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
             //   links.add(new URL(text.toString()));
        }
    }


}
