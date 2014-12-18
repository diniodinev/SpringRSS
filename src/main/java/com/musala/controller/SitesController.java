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


import com.musala.service.SiteService;
import com.musala.view.SiteUpdateView;
import com.musala.view.SiteView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

import static com.musala.controller.RestURIConstants.SITE_DELETE;
import static com.musala.controller.RestURIConstants.SITE_GET_BY_SITENAME;

@RestController
@RequestMapping("/site")
public class SitesController {

    @Autowired
    private SiteService siteService;

    @Autowired
    private ConversionService conversionService;


    @RequestMapping(value = {SITE_DELETE}, method = RequestMethod.DELETE)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable String deleteSite) {
        siteService.delete(deleteSite);
    }

    @RequestMapping(value = {"/", ""}, method = RequestMethod.POST)
    @ResponseBody
    public SiteView create(@RequestBody SiteView site) {
        return conversionService.convert(siteService.save(site), SiteView.class);
    }

    //Slash is used because of the "." in the siteName
    @RequestMapping(value = {SITE_GET_BY_SITENAME}, method = RequestMethod.GET)
    @ResponseBody
    public SiteView getById(@PathVariable String siteName) {
        return conversionService.convert(siteService.findOne(siteName), SiteView.class);
    }

    @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
    @ResponseBody
    public List<SiteView> listSites() {
        return Arrays.asList(conversionService.convert(siteService.findAll(), SiteView[].class));
    }

    @RequestMapping(value = {SITE_GET_BY_SITENAME}, method = RequestMethod.PUT)
    @ResponseBody
    public void update(@PathVariable String siteName, @RequestBody SiteUpdateView siteUpdateView) {
        System.out.println(siteUpdateView.getRssLink());
        siteService.update(new SiteView(siteName, siteUpdateView));
    }
}
