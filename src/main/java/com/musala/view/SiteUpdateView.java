package com.musala.view; 
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


public class SiteUpdateView {
    private String rssLink;
    private String rssTag;
    private String titleTag;
    private String textContentTag;
    private String categoryTag;

    public SiteUpdateView() {
    }

    public SiteUpdateView(String rssLink, String rssTag, String titleTag, String textContentTag, String categoryTag) {
        this.rssLink = rssLink;
        this.rssTag = rssTag;
        this.titleTag = titleTag;
        this.textContentTag = textContentTag;
        this.categoryTag = categoryTag;
    }

    public String getRssLink() {
        return rssLink;
    }

    public void setRssLink(String rssLink) {
        this.rssLink = rssLink;
    }

    public String getRssTag() {
        return rssTag;
    }

    public void setRssTag(String rssTag) {
        this.rssTag = rssTag;
    }

    public String getTitleTag() {
        return titleTag;
    }

    public void setTitleTag(String titleTag) {
        this.titleTag = titleTag;
    }

    public String getTextContentTag() {
        return textContentTag;
    }

    public void setTextContentTag(String textContentTag) {
        this.textContentTag = textContentTag;
    }

    public String getCategoryTag() {
        return categoryTag;
    }

    public void setCategoryTag(String categoryTag) {
        this.categoryTag = categoryTag;
    }
}
