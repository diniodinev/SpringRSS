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

//SITE_NAME varchar(256) NOT NULL,
// RSS_LINK varchar(256) NOT NULL,
// RSS_TAG varchar(64) NOT NULL,
// TITLE_TAG varchar(64) NULL,
// TEXT_CONTENT_TAG varchar(64) NOT NULL,
// ARTICLE_ID bigint NOT NULL,
// primary key (SITE_NAME),
// FOREIGN KEY(ARTICLE_ID) REFERENCES ARTICLE(ARTICLE_ID)

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "SITE")
public class SiteEntity implements Serializable {

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

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL,mappedBy = "site")
    private List<ArticleEntity> articlesFromCite = new ArrayList<ArticleEntity>();

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

    public SiteEntity(String siteName, String rssLink, String rssTag, String titleTag, String textContentTag, long articleId) {
        this.siteName = siteName;
        this.rssLink = rssLink;
        this.rssTag = rssTag;
        this.titleTag = titleTag;
        this.textContentTag = textContentTag;
    }

    public SiteEntity() {
    }

    public List<ArticleEntity> getArticlesFromCite() {
        return articlesFromCite;
    }

    public void setArticlesFromCite(List<ArticleEntity> articlesFromCite) {
        this.articlesFromCite = articlesFromCite;
    }

    @Override
    public String toString() {
        return "SiteEntity{" +
                "siteName='" + siteName + '\'' +
                ", rssLink='" + rssLink + '\'' +
                ", rssTag='" + rssTag + '\'' +
                ", titleTag='" + titleTag + '\'' +
                ", textContentTag='" + textContentTag + '\'' +
                ", articlesFromCite=" + articlesFromCite +
                '}';
    }
}
