package com.musala.db; 
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

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "SITE")
public class Site implements Serializable {

    private static final long serialVersionUID = -123141221324478L;

    @Id
    @Column(name = "SITE_NAME")
    private String siteName;

    @Column(name = "RSS_LINK")
    private String rssLink;

    @Column(name = "RSS_TAG")
    private String rssTag;

    @Column(name = "TITLE_TAG")
    private String titleTag;

    @Column(name = "TEXT_CONTENT_TAG")
    private String textContentTag;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "site")
    private List<Article> articlesFromCite = new ArrayList<Article>();

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

    public Site(String siteName, String rssLink, String rssTag, String titleTag, String textContentTag, long articleId) {
        this.siteName = siteName;
        this.rssLink = rssLink;
        this.rssTag = rssTag;
        this.titleTag = titleTag;
        this.textContentTag = textContentTag;
    }

    public Site() {
    }

    public List<Article> getArticlesFromCite() {
        return articlesFromCite;
    }

    public void setArticlesFromCite(List<Article> articlesFromCite) {
        this.articlesFromCite = articlesFromCite;
    }

}
