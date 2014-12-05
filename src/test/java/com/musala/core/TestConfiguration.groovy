package com.musala.core

import com.musala.configuration.RssAplicationConfiguration
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.test.context.ContextConfiguration

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


import javax.annotation.Resource
import spock.lang.*

import javax.persistence.EntityManagerFactory

//@ContextConfiguration(locations = "classpath*:/RSSBean.xml")

@ContextConfiguration(classes =RssAplicationConfiguration)
class TestConfiguration extends Specification {
//    @Autowired
//
//    @Resource


    @Autowired
    EntityManagerFactory factory

    def 'check entityManagerFactory() method properties'() {
//        given:
//        EntityManagerFactory factory = context.getBean("entityManagerFactory",EntityManagerFactory.class);

        expect:
        factory != null
    }
}
