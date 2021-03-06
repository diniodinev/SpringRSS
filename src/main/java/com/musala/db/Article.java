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
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ARTICLE")
public class Article implements Serializable {

    private static final long serialVersionUID = 3325224652693856531L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ARTICLE_ID", nullable = false)
    private long id;

    @Column(name = "FULL_LINK", nullable = false, length = 4096)
    private String link;

    @Lob
    @Column(name = "ARTICLE_TEXT")
    private String articleText;

    @Column(name = "TITLE", nullable = true, length = 512)
    private String title;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "PUBLICATION_DATE", nullable = true)
    private Date publicationDate;


    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinTable(
            name = "ARTICLE_CATEGORY",
            joinColumns = {@JoinColumn(name = "ART_ID", referencedColumnName = "ARTICLE_ID")},
            inverseJoinColumns = {@JoinColumn(name = "CATEG_ID", referencedColumnName = "CATEGORY_ID")})
    private List<Category> categories = new ArrayList<Category>();


    @ManyToOne
    @JoinColumn(name = "SITE_NAME")
    private Site site;


    public Article() {
    }

    public Article(String link, Site site) {
        this.link = link;
        this.site = site;
    }

    public Article(String link, String articleText, String title, Date publicationDate, Site site) {
        this.link = link;
        this.articleText = articleText;
        this.title = title;
        this.publicationDate = publicationDate;
        this.site = site;
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

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", link='" + link + '\'' +
                ", articleText='" + articleText + '\'' +
                ", title='" + title + '\'' +
                ", publicationDate=" + publicationDate +
                ", site=" + site +
                '}';
    }
}
