package com.musala.service;
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


import com.musala.db.Article;

import java.util.List;

public interface ArticleService {
    public Iterable<Article> findAll();

    public Article save(Article article);

    public Article findByLink(String link);

    public void delete(Article article);

    public Article findOne(long id);

    public List<Article> findByKeyWord(String keyWord);

}
