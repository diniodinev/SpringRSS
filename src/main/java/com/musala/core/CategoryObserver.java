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


import java.util.ArrayList;
import java.util.List;

public class CategoryObserver extends SaxObserver {

    private List<String> categories = new ArrayList<String>();

    public CategoryObserver(RssExtractorSubject subject) {
        this.subject = subject;
        subject.attach(this);
    }

    @Override
    public void update(String category) {
        if (category != null && !category.isEmpty()) {
            categories.add(category);
            System.out.println("Observer category is called and is added category" + category);
        }
    }

    @Override
    public void updateAll() {

    }
}
