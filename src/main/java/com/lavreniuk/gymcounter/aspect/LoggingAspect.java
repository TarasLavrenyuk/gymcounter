package com.lavreniuk.gymcounter.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author taras
 * @date 06.05.18.
 */
@Aspect
public class LoggingAspect {


    @Before("execution(public com.lavreniuk.gymcounter.responses.Response com.lavreniuk.gymcounter.controllers.*(..) ) ")
    public void logBefore(JoinPoint joinPoint) {

        System.out.println();
        System.out.println("-- " + joinPoint.getSignature().getName());
        for (Object o : joinPoint.getArgs()) {
            System.out.println(o);
            System.out.println(o.getClass().getName());
        }
        System.out.println();

    }




}
