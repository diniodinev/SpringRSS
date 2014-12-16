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

import com.musala.db.Site;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class ArticleInfo {
    private String categoryName;
    private TagType tagType;
    private Site site;

    public ArticleInfo() {
    }

    public ArticleInfo(String categoryName, TagType tagType, Site site) {
        this.categoryName = categoryName;
        this.tagType = tagType;
        this.site = site;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public TagType getTagType() {
        return tagType;
    }

    public void setTagType(TagType tagType) {
        this.tagType = tagType;
    }

    @Override
    public String toString() {
        return "ArticleInfo{" +
                "categoryName='" + categoryName + '\'' +
                ", tagType=" + tagType +
                ", site=" + site +
                '}';
    }
}
