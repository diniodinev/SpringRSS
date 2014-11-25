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
import javax.persistence.*;

@Entity
@Table(name="ARTICLE")
public class ArticleEntity implements Serializable {
    private static final long serialVersionUID = 8645213321324478L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ARTICLE_ID")
    private long id;

    @Column(name="TITLE")
    private String title;

    @Column(name="ARTICLE_TEXT")
    private String text;

    @Column(name="LINK")
    private URL link;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public URL getLink() {
        return link;
    }

    public void setLink(URL link) {
        this.link = link;
    }
}
