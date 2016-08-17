package com.kennedy.springdemo.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @Description: 记录日志
 * @date: 2016年8月16日 下午2:07:36
 * @author: zengt
 * @version: 1.0
 */
@Aspect
public class LogAspect {
    @Pointcut("execution(* com.kennedy.springdemo.service.*.impl.*.*(..))")
    public void logAspect() {
    };

    @Before("logAspect()")
    public void before() {
        System.out.println("before");
    }

}
 