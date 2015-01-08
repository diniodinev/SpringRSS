package com.musala.testutils; 
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

import com.google.common.base.Preconditions;
import com.musala.core.ArticleInfo;
import com.musala.core.CategoryObserver;
import com.musala.core.GetTextFromPages;
import com.musala.core.RssProcessor;
import com.musala.core.RssProcessorImpl;
import com.musala.repository.ArticleRepository;
import com.musala.repository.CategoryRepository;
import com.musala.repository.SiteRepository;
import com.musala.service.ArticleService;
import com.musala.service.ArticleServiceImpl;
import com.musala.service.CategoryServiceImpl;
import com.musala.service.SiteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@PropertySource({"classpath:persistence-h2.properties"})
//@ComponentScan(basePackages = {"com.musala.service", "com.musala.core", "com.musala.converter", "com.musala.controller", "com.musala.aspects", "com.musala.cron"})
public class DatabaseTestConfiguration {
    @Autowired
    private Environment env;

    @Bean(name = "dataSource")
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(Preconditions.checkNotNull(env.getProperty("jdbc.driverClassName")));
        dataSource.setUrl(Preconditions.checkNotNull(env.getProperty("jdbc.url")));
        return dataSource;
    }

    @Bean(name = "entityManagerFactory")
    public EntityManagerFactory entityManagerFactory() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(dataSource());
        factory.setPackagesToScan("com.musala.db");

        factory.setPersistenceUnitName("persistenceUnit");
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setJpaProperties(additionalProperties());
        factory.afterPropertiesSet();

        return factory.getObject();
    }

    final Properties additionalProperties() {
        final Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        hibernateProperties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
        hibernateProperties.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        hibernateProperties.setProperty("hibernate.hbm2ddl.import_files", env.getProperty("hibernate.hbm2ddl.import_files"));

        return hibernateProperties;
    }

    @Bean
    public PlatformTransactionManager transactionManager() throws SQLException {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory());
        return txManager;
    }

//    @Bean
//    public SiteRepository siteRepository() {
//        return new SiteServiceImpl().getSiteRepository();
//    }
//
//    @Bean
//    public CategoryRepository categoryService() {
//        return new CategoryServiceImpl().getCategoryRepository();
//    }
//
//    @Bean
//    public CategoryServiceImpl categoryServiceImpl() {
//        return new CategoryServiceImpl();
//    }
//
//    @Bean
//    public SiteServiceImpl siteServiceImpl() {
//        return new SiteServiceImpl();
//    }
//
//    @Bean
//    public ArticleRepository articleRepository() {
//        return new ArticleServiceImpl().getArticleRepository();
//    }
//
//    @Bean
//    public ArticleService articleService() {
//        return new ArticleServiceImpl();
//    }
//
//    @Bean
//    public ArticleInfo articleInfo() {
//        return new ArticleInfo();
//    }
//
//    @Bean
//    public GetTextFromPages getTextFromPages() {
//        GetTextFromPages getTextFromPages = new GetTextFromPages();
//        getTextFromPages.setArticleService(articleService());
//        return getTextFromPages();
//    }
//
//    @Bean
//    public RssProcessor rssProcessor() {
//        RssProcessorImpl rssProcessorImpl = new RssProcessorImpl();
//        rssProcessorImpl.setArticleInfo(articleInfo());
//        rssProcessorImpl.setArticleService(articleService());
//        //TODO add for rssProcessorImpl.setObservers();
//        //Not Full
//        return rssProcessorImpl;
//    }
//
//    @Bean
//    public CategoryObserver categoryObserver() {
//        CategoryObserver categoryObserver = new CategoryObserver();
//        categoryObserver.setCategoryService(categoryServiceImpl());
//        categoryObserver.setRssProcessor(rssProcessor());
//        return categoryObserver;
//    }

}
