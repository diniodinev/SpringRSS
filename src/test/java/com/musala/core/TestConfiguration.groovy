package com.musala.core

import com.musala.testutils.DatabaseTestConfiguration
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.PropertySource
import org.springframework.core.env.Environment
import org.springframework.test.context.ContextConfiguration
import org.springframework.transaction.annotation.Transactional
import spock.lang.Specification

import javax.sql.DataSource

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

@ContextConfiguration(classes = [DatabaseTestConfiguration.class])
@PropertySource("classpath:persistence-h2.properties")
@Transactional
class TestConfiguration extends Specification {
    @Autowired
    private Environment env;

    @Autowired
    DataSource dataSource

    private ApplicationContext applicationContext;


    def 'check checkDataSource() initialzation'() {
        given:
        def properties = dataSource.getProperties()
        expect:
        dataSource != null

        and:
        properties.get("driverClassName") == env.getProperty("jdbc.driverClassName")
        properties.get("url") == env.getProperty("jdbc.url")
    }
}
