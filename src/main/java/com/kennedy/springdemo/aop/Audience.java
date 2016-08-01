package com.kennedy.springdemo.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class Audience {
    @Pointcut("execution(** com.kennedy.springdemo.service.**.impl.add(..))")
    public void performance() {
    };

    @Before(value = "performance()")
    public void before() {
        System.out.println("before");
    }
}
