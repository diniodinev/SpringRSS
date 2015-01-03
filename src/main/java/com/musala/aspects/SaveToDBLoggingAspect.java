package com.musala.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SaveToDBLoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(SaveToDBLoggingAspect.class);

    @AfterReturning(pointcut = "com.musala.aspects.RssSystemArchitecture.service()")
    public void afterSaveToDbLogging(JoinPoint point) {
        logger.info("\n ---------------->Service method: " + point.getStaticPart().getSignature().toString());
        for (Object obj : point.getArgs()) {
            logger.info("\n --------> Service Arguments: " + obj.toString());
        }
    }
}
