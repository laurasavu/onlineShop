
package com.practice.onlineShop.Aspects;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
public class LogAspect {

    @Pointcut("execution(* com.practice.onlineShop.controllers.ProductController.addProduct(..))")
    public void addProductPointcut(){}

    @Before("com.practice.onlineShop.Aspects.LogAspect.addProductPointcut()")
    public void before(JoinPoint joinPoint){
            System.out.println("in before aspect"+ new Date());
        System.out.println("product vo"+joinPoint.getArgs()[0]);
        System.out.println("user id"+joinPoint.getArgs()[1]);
    }

    @After("com.practice.onlineShop.Aspects.LogAspect.addProductPointcut()")
    public void after(JoinPoint joinPoint){
        System.out.println("in after aspect at"+ new Date());
    }
}

