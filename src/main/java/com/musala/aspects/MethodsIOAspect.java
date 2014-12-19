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
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class MethodsIOAspect {

 private static final Logger logger = LoggerFactory.getLogger(MethodsIOAspect.class);

 @Before("public * *(..)")
 public void loggingBeforeAllMethods( JoinPoint point){
   logger.info("Entering in method"+ point.getTarget().getClass());
 }
}
