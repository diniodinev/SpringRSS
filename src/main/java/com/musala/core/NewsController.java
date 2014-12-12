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


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/movies")
public class NewsController {

//    @Autowired
//    ArticleService articleService;

//    @Autowired
//    private ConversionService conversionService;

    @RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
    @ResponseBody
    public String listArticles() {
        return "Tovaaa";
    }
}
