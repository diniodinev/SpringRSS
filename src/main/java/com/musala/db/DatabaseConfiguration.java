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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfiguration {
    @Autowired
    private SimpleDriverDataSource dataSource;

    private JdbcTemplate jdbcTemplate = new JdbcTemplate();

    public Connection createConnection() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public int getCount() {
        String sql = "Select count(*) from articles";
        jdbcTemplate.setDataSource(dataSource);
        return jdbcTemplate.queryForObject(sql, new Object[]{}, Integer.class);
    }

    public Article getArticleTextName(String title) {
        String sql = "select * from articles where TITLE= ?";
        jdbcTemplate.setDataSource(dataSource);
        return jdbcTemplate.queryForObject(sql, new Object[]{title}, new ArticleMapper() );
    }

    public void setDataSource(SimpleDriverDataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

}
