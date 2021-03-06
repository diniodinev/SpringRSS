package com.musala.controller; 
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

import com.musala.service.ArticleService;
import com.musala.view.ArticleView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {
    private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    ArticleService articleService;

    @Autowired
    private ConversionService conversionService;

    @RequestMapping(value = {"/{articleID}"}, method = RequestMethod.GET)
    @ResponseBody
    public ArticleView getById(@PathVariable long articleID) {
        logger.debug("Getting article byID:", articleID);
        return conversionService.convert(articleService.findOne(articleID), ArticleView.class);
    }

    @RequestMapping(value = {"/search/{keyword}"}, method = RequestMethod.GET)
    @ResponseBody
    public List<ArticleView> getByKeyword(@PathVariable String keyword) {
        return Arrays.asList(conversionService.convert(articleService.findByKeyWord(keyword), ArticleView[].class));
    }

    @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
    @ResponseBody
    public List<ArticleView> getAllArticles(@RequestBody String keyword) {
        return Arrays.asList(conversionService.convert(articleService.findAll(), ArticleView[].class));
    }

}
