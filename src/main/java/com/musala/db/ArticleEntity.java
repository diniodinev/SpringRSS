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


import java.io.Serializable;
import java.net.URL;
import java.sql.Date;
import javax.persistence.*;

@Entity
@Table(name="ARTICLE")
public class ArticleEntity implements Serializable {
    private static final long serialVersionUID = 8645213321324478L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ARTICLE_ID")
    private long id;

    @Column(name="FULL_LINK")
    private String link;

    @Column(name="ARTICLE_TEXT")
    private String articleText;

    @Column(name="TITLE")
    private String title;

    @Column(name="DATE")
    private Date date;

    @Column(name="SITE_NAME")
    private String siteName;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getArticleText() {
        return articleText;
    }

    public void setArticleText(String articleText) {
        this.articleText = articleText;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }
}
