package com.dinio.core;
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


import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Aspect
public class AppConfig {

    @After("execution(public * *(..))")
    public void sleepOneSecond() throws InterruptedException {
        System.out.println("Wait 1 sec");
        Thread.sleep(10000);
    }

    @After("execution(public * *(..))")
    public void deleteAdvice() {
        System.out.println("Wait 1 sec");
    }
}