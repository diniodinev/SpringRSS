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
  * Created by dinyo.dinev on 2015.
 */


import com.musala.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LastVisitDateObserver implements ArticleObserver {
    @Autowired
    private RssProcessor rssProcessor;

    @Autowired
    private SiteService siteService;

    public LastVisitDateObserver() {
    }

    @Override
    public void update() {
        ArticleInfo articleInfo;
        articleInfo = rssProcessor.getUpdate();
        if (articleInfo.getTagType() == TagType.LAST_VISIT) {
            if (articleInfo.getSite().getLastVisitDateTag() != null && !articleInfo.getCategoryName().isEmpty()) {
                // categoryService.save(articleInfo.getCategoryName());
                siteService.updateLastVisitDate(articleInfo.getSite(), articleInfo.getSite().getLastVisitDate());
            }
        }
    }

    public RssProcessor getRssProcessor() {
        return rssProcessor;
    }

    public void setRssProcessor(RssProcessor rssProcessor) {
        this.rssProcessor = rssProcessor;
    }

    public SiteService getSiteService() {
        return siteService;
    }

    public void setSiteService(SiteService siteService) {
        this.siteService = siteService;
    }

    @Override
    public String toString() {
        return "LastVisitDateObserver{" +
                "rssProcessor=" + rssProcessor +
                ", siteService=" + siteService +
                '}';
    }
}
