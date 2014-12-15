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


public class ErrorMessages {

    private ErrorMessages(){
    }

    public static final String OBSERVER_MUST_NOT_BE_NULL = "The attached observer must not be null";
    public static final String UPDATE_ON_NULL_OBSERVER_NOT_ALLOW = "Update ca not be executed on null "+RssProcessor.class;
    public static final String INVALID_URL_FROM_RSS = "Invalid URL from the RSS:";
}
