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

import com.musala.db.Category;

import java.util.Date;
import java.util.List;

public class ArticleView {
    private long id;

    private String link;

    private String articleText;

    private String title;

    private Date publicationDate;

    private List<String> categories;

    private String siteName;

    public ArticleView() {
    }

    public ArticleView(long id, String link, String articleText, String title, Date publicationDate,
        List<String> categories, String siteName) {
        this.id = id;
        this.link = link;
        this.articleText = articleText;
        this.title = title;
        this.publicationDate = publicationDate;
        this.categories = categories;
        this.siteName = siteName;
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

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    @Override
    public String toString() {
        return "ArticleView [id=" + id + ", link=" + link + ", articleText=" + articleText + ", title=" + title
            + ", publicationDate=" + publicationDate + ", categories=" + categories + ", siteName=" + siteName + "]";
    }
}
