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

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.quartz.JobDetailBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CronManager {
    private static final Logger logger = LoggerFactory.getLogger(CronManager.class);

    @Autowired
    RefreshDbTask refreshDbTask;

    @Bean
    public Scheduler scheduler() {
        Scheduler scheduler = null;
        try {
            return new StdSchedulerFactory().getScheduler();
        } catch (SchedulerException e) {
            logger.warn("There is a problem with the underlying Scheduler.", e);
        }
        return scheduler;
    }

    @Bean
    public JobDetail jobDetailBean() {
        Map<String, Object> jobData = new HashMap<>();
        JobDetailBean jobDetailBean = new JobDetailBean();
        jobDetailBean.setJobClass(RefreshDBJob.class);
        jobData.put("refreshDBJob", refreshDbTask);
        jobDetailBean.setJobDataAsMap(jobData);
        return jobDetailBean;
    }


}
