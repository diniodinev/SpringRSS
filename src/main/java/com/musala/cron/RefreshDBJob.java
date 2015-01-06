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


import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

public class RefreshDBJob extends QuartzJobBean {

    @Autowired
    private RefreshDbTask refreshDbTask;

    @PostConstruct
    protected void executeInternal(JobExecutionContext context)
            throws JobExecutionException {

        refreshDbTask.printMe();

    }
}