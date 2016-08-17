package com.kennedy.springdemo.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import com.kennedy.springdemo.config.datasources.DBContextHolder;

@Aspect
public class RWAspect {
    @Pointcut("execution(* com.kennedy.springdemo.service.*.impl.*.add*(..))")
    public void RW() {
    };

    @Pointcut("execution(* com.kennedy.springdemo.service.*.impl.*.get*(..))")
    public void R() {
    };

    @Before("RW()")
    public void rwBefore() {
        DBContextHolder.setDbType(DBContextHolder.DB_TYPE_RW);
    }

    @Before("R()")
    public void rBefore() {
        DBContextHolder.setDbType(DBContextHolder.DB_TYPE_R);
    }

}
