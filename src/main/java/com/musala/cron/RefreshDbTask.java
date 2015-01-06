package com.musala.cron; 
 /*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
  * Created by dinyo.dinev on 2015.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Qualifier("refreshDbTask")
public class RefreshDbTask {
    private static final Logger logger = LoggerFactory.getLogger(RefreshDbTask.class);

    public RefreshDbTask() {
    }

    @Scheduled(fixedRate = 100000)
    public void printMe() {
        logger.info("Spring 4 + Quartz 1.8.6 ~");
    }

}
