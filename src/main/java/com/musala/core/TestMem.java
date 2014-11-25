package com.musala.core;
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


import java.sql.*;

import com.musala.db.DatabaseConfiguration;
import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

public class TestMem {

//    private static Connection createConnection() throws ClassNotFoundException, SQLException {
//        Class.forName("org.h2.Driver");
//        return DriverManager.getConnection("jdbc:h2:mem:test");
//    }

    private void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String... args) throws Exception {

        // open the in-memory database within a VM
        ApplicationContext context = new ClassPathXmlApplicationContext("RSSBean.xml");
        DatabaseConfiguration dbConfiguration = context.getBean("databaseConfigurationBean", DatabaseConfiguration.class);

        Connection conn = dbConfiguration.createConnection();
        Statement stmt = null;
        stmt = conn.createStatement();

        //conn.createStatement().execute("create table test(id int)");

        conn.createStatement().execute("CREATE TABLE articles ( ID int IDENTITY(1,1) NOT NULL, TITLE VARCHAR(500), ARTICLE_TEXT VARCHAR(4200) NOT NULL, URL VARCHAR(120) NOT NULL)");
        conn.createStatement().execute("insert into articles ( TITLE, URL, ARTICLE_TEXT) values ('title1', 'http://www.google.bg','article_text 1')");

        ResultSet rs = stmt.executeQuery("SELECT URL, ARTICLE_TEXT from articles");

        System.out.println("Count=" + dbConfiguration.getCount());
        System.out.println("Text="+dbConfiguration.getArticleTextName("title1"));
        while (rs.next()) {
            System.out.println(rs.getString("URL"));
            System.out.println(rs.getString("ARTICLE_TEXT"));
        }

        // start a TCP server
        // (either before or after opening the database)
        Server server = Server.createTcpServer().start();

        // .. use in embedded mode ..

        // or use it from another process:
        System.out.println("Server started and connection is open.");
        System.out.println("URL: jdbc:h2:" + server.getURL() + "/mem:test");

        // now start the H2 Console here or in another process using
        // java org.h2.tools.Console -web -browser

        System.out.println("Press [Enter] to stop.");
        System.in.read();

        System.out.println("Stopping server and closing the connection");
        server.stop();
        conn.close();
    }
}