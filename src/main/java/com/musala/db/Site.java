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
//TODO add size(all information) ,take it form the ddl
@Entity
@Table(name = "SITE")
public class Site implements Serializable {

    private static final long serialVersionUID = -123141221324478L;

    @Id
    @Column(name = "SITE_NAME", nullable = false, length= 256)
    private String siteName;

    @Column(name = "RSS_LINK", nullable = false, length= 256)
    private String rssLink;

    @Column(name = "RSS_TAG", nullable = false, length= 64)
    private String rssTag;

    @Column(name = "TITLE_TAG", nullable = true, length= 64)
    private String titleTag;

    @Column(name = "TEXT_CONTENT_TAG", nullable = false, length= 64)
    private String textContentTag;

    @Column(name = "CATEGORY_TAG", nullable = true, length= 128)
    private String categoryTag;

    @OneToMany(fetch = FetchType.LAZY , cascade = CascadeType.ALL, mappedBy = "site")
    private List<Article> articlesFromCite = new ArrayList<Article>();

    public Site() {
    }

    public Site(String siteName, String rssLink, String rssTag, String titleTag, String textContentTag, long articleId) {
        this.siteName = siteName;
        this.rssLink = rssLink;
        this.rssTag = rssTag;
        this.titleTag = titleTag;
        this.textContentTag = textContentTag;
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

    public List<Article> getArticlesFromCite() {
        return articlesFromCite;
    }

    public void setArticlesFromCite(List<Article> articlesFromCite) {
        this.articlesFromCite = articlesFromCite;
    }

}
