package com.cn.study.basicSpring.AOP;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/6/27.
 */
@Aspect
@Component
@EnableAspectJAutoProxy
public class LogAspect {

    @Pointcut("@annotation(com.cn.study.basicSpring.AOP.Action)")
    public void annotationPointCut() {
    }

    @Before("execution(* *..AOP*..*(..))")
    public void before(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        System.out.println("方法规则拦截Before--" + method.getName());
    }

    @After("execution(* *..AOP*..*(..))")
    public void AfterM(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        System.out.println("方法规则拦截After--" + method.getName());
    }

//    @Around("execution(* *..AOP*..*(..))")
//    public void aroundM(JoinPoint joinPoint) {
//        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//        Method method = signature.getMethod();
//        System.out.println("方法规则拦截Around--" + method.getName());
//    }


//    @Around("annotationPointCut()")
//    public void around(JoinPoint joinPoint) {
//        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//        Method method = signature.getMethod();
//        Action action = method.getAnnotation(Action.class);
//        System.out.println("注解规则式拦截 Around--" + method.getName());
//    }

    @Before("annotationPointCut()")
    public void beforeA(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Action action = method.getAnnotation(Action.class);
        System.out.println("注解规则式拦截 Before--" + method.getName());
    }

    @After("annotationPointCut()")
    public void after(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Action action = method.getAnnotation(Action.class);
        System.out.println("注解规则式拦截After--" + method.getName());
    }
}
