package com.musala.aspects;

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

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.After;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RestExecutionLoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(RestExecutionLoggingAspect.class);

    @Before("execution(* com.musala.controller.*Controller.*(..))")
    public void loggingBeforeAllMethods(JoinPoint point) {
        logger.info("\n ---------------->Entering in: " + point.getStaticPart().getSignature().toString());
    }

    @After("execution(* com.musala.controller.*Controller.*(..))")
    public void loggingAfterAllMethods(JoinPoint point) {
        if (point.getArgs().length >= 1) {
            logger.info("\n ---------------->Arguments of method: " + point.getStaticPart().getSignature().toString());
            for (Object obj : point.getArgs()) {
                logger.info("\n --------> Arg=" + obj.toString());
            }
        }
        logger.info("\n ---------------->Exit from: " + point.getStaticPart().getSignature().toString());
    }

    
    

}
