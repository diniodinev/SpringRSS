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


import java.util.Date;

public class SiteView {

    private String siteName;
    private String rssLink;
    private String rssTag;
    private String titleTag;
    private String textContentTag;
    private String categoryTag;
    private String lastVisitDateTag;
    private String lastVisitDate;

    public SiteView() {
    }

    public SiteView(String siteName, String rssLink, String rssTag, String titleTag, String textContentTag, String categoryTag, String lastVisitDateTag, String lastVisitDate) {
        this.siteName = siteName;
        this.rssLink = rssLink;
        this.rssTag = rssTag;
        this.titleTag = titleTag;
        this.textContentTag = textContentTag;
        this.categoryTag = categoryTag;
        this.lastVisitDateTag = lastVisitDateTag;
        this.lastVisitDate = lastVisitDate;
    }

    public SiteView(String siteName, SiteUpdateView siteUpdateView) {
        this.siteName = siteName;
        this.categoryTag = siteUpdateView.getCategoryTag();
        this.rssLink = siteUpdateView.getRssLink();
        this.rssTag = siteUpdateView.getRssTag();
        this.titleTag = siteUpdateView.getTitleTag();
        this.textContentTag = siteUpdateView.getTextContentTag();
        this.lastVisitDateTag = siteUpdateView.getLastVisitDateTag();
        this.lastVisitDate = siteUpdateView.getLastVisitDate();
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
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

    public String getLastVisitDateTag() {
        return lastVisitDateTag;
    }

    public void setLastVisitDateTag(String lastVisitDateTag) {
        this.lastVisitDateTag = lastVisitDateTag;
    }

    public String getLastVisitDate() {
        return lastVisitDate;
    }

    public void setLastVisitDate(String lastVisitDate) {
        this.lastVisitDate = lastVisitDate;
    }

    @Override
    public String toString() {
        return "SiteView{" +
                "siteName='" + siteName + '\'' +
                ", rssLink='" + rssLink + '\'' +
                ", rssTag='" + rssTag + '\'' +
                ", titleTag='" + titleTag + '\'' +
                ", textContentTag='" + textContentTag + '\'' +
                ", categoryTag='" + categoryTag + '\'' +
                ", lastVisitDateTag='" + lastVisitDateTag + '\'' +
                ", lastVisitDate=" + lastVisitDate +
                '}';
    }
}
