package com.musala.aspects;

import org.aspectj.lang.annotation.Pointcut;

public class RssSystemArchitecture {

    @Pointcut("execution(* (@org.springframework.stereotype.Repository *).*(..))")
    public void repository() {
    }

    @Pointcut("execution(* (@org.springframework.stereotype.Service *).*(..))")
    public void service() {
    }

}
