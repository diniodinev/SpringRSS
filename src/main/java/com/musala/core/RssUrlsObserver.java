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


import org.apache.commons.validator.routines.UrlValidator;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RssUrlsObserver extends SaxObserver {

    private URL currentLink;

    private Map<URL, Set<String>> articles = new HashMap<>();

    GetTextFromPages getTextFromPages;

    public RssUrlsObserver(RssExtractorSubject subject, GetTextFromPages getTextFromPages) {
        this.subject = subject;
        subject.attach(this);
        this.getTextFromPages = getTextFromPages;
    }

    @Override
    public void updateAll(String elementTag, TagContent tagContent) {
        if (tagContent == TagContent.CATEGORY) {
            if (elementTag != null && !elementTag.isEmpty() && currentLink != null) {
                Set<String> currentCategories = new HashSet<String>(articles.get(currentLink));
                currentCategories.add(elementTag);
                articles.put(currentLink, currentCategories);
            }
        }
        if (tagContent == TagContent.LINK) {
            UrlValidator defaultValidator = new UrlValidator();
            if (defaultValidator.isValid(elementTag)) {
                try {
                    currentLink = new URL(elementTag);
                    articles.put(currentLink, new HashSet<String>());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void updateAll() {
        System.out.println("URL observer write to db:");
        //Fill article's data
        getTextFromPages.readData(articles);
    }
}
