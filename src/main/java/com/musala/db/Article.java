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
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "ARTICLE")
public class Article implements Serializable {

    private static final long serialVersionUID = 3325224652693856531L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ARTICLE_ID")
    private long id;

    @Column(name = "FULL_LINK")
    private String link;

    @Column(name = "ARTICLE_TEXT",length = 32768)
    private String articleText;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "DATE")
    private Date date;



    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name="ARTICLE_CATEGORY",
            joinColumns={@JoinColumn(name="ART_ID", referencedColumnName="ARTICLE_ID")},
            inverseJoinColumns={@JoinColumn(name="CATEG_ID", referencedColumnName="CATEGORY_ID")})
   private List<Category>  categories = new ArrayList<Category>();


   @ManyToOne
   @JoinColumn(name="SITE_NAME")
   private Site site;

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
                ", date=" + date +
                ", site=" + site +
                '}';
    }

    public Article() {
    }
}
