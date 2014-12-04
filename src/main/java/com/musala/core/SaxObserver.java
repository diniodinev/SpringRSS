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

public abstract class SaxObserver {
    protected RssExtractorSubject subject;

    public abstract void updateAll();

    public abstract void update(String tagElement, TagContent tagContent);
}
