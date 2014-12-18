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


import com.musala.db.Site;
import com.musala.service.ArticleService;
import com.musala.service.SiteService;
import com.musala.view.SiteView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

//TODO make separate controllers
@RestController
@RequestMapping("/site")
public class SitesController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private SiteService siteService;

    @Autowired
    private ConversionService conversionService;


//    @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
//    @ResponseBody
//    public String listArticles() {
//        return "Tovaaa";
//    }

    @RequestMapping(value = {"/", ""}, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Site> create(@PathVariable Object site) {
        System.out.println("In");
        return new ResponseEntity<Site>((Site)site, HttpStatus.OK);
    }

    //Slash is used because of the "." in the siteName
    @RequestMapping(value = {"/{siteName}/"}, method = RequestMethod.GET)
    @ResponseBody
    public SiteView getById(@PathVariable String siteName) {
        return conversionService.convert(siteService.findOne(siteName), SiteView.class);
    }

    @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
    @ResponseBody
    public List<SiteView> listSites() {
        return Arrays.asList(conversionService.convert(siteService.findAll(), SiteView[].class));
    }
}
