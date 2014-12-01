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
import java.util.ArrayList;
import java.util.List;

public class RssUrlsObserver extends SaxObserver {

    private List<URL> links = new ArrayList<URL>();

    public RssUrlsObserver(RssExtractorSubject subject) {
        this.subject = subject;
        subject.attach(this);
    }

    @Override
    public void update(String url) {
        UrlValidator defaultValidator = new UrlValidator();
        if (defaultValidator.isValid(url)) {
            try {
                links.add(new URL(url));
                System.out.println("Addes url in RsUrlsobserver " +url);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

    }
}
