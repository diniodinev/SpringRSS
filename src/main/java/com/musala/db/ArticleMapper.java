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


import com.musala.core.Article;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ArticleMapper implements RowMapper<Article> {

    @Override
    public Article mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        Article article = new Article();
        article.setTitle(resultSet.getString("TITLE"));
        article.setText(resultSet.getString("ARTICLE_TEXT"));
        article.setLink(resultSet.getString("URL"));
        return article;
    }
}
