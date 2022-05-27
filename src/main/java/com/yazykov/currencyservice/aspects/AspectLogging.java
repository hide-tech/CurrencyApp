package com.yazykov.currencyservice.aspects;

import com.yazykov.currencyservice.throwable.ExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.ResponseStatusException;

@Aspect
@Configuration
@Slf4j
public class AspectLogging {

    @Before(value = "within(com.yazykov.currencyservice.security.controller.*)")
    public void loggingStatementBeforeAppUserController(JoinPoint joinPoint){
        log.info("Executing {}", joinPoint);
    }

    @After(value = "within(com.yazykov.currencyservice.security.controller.*)")
    public void loggingStatementAfterAppUserController(JoinPoint joinPoint){
        log.info("Complete {}", joinPoint);
    }

    @Before(value = "within(com.yazykov.currencyservice.security.service.*)")
    public void loggingStatementBeforeAppUserService(JoinPoint joinPoint){
        log.info("Executing {}", joinPoint);
    }

    @After(value = "within(com.yazykov.currencyservice.security.service.*)")
    public void loggingStatementAfterAppUserService(JoinPoint joinPoint){
        log.info("Complete {}", joinPoint);
    }

    @Before(value = "within(com.yazykov.currencyservice.security.email.*)")
    public void loggingStatementBeforeAppUserEmail(JoinPoint joinPoint){
        log.info("Executing {}", joinPoint);
    }

    @After(value = "within(com.yazykov.currencyservice.security.email.*)")
    public void loggingStatementAfterAppUserEmail(JoinPoint joinPoint){
        log.info("Complete {}", joinPoint);
    }

    @Before(value = "within(com.yazykov.currencyservice.service.*)")
    public void loggingStatementBeforeCurrencyService(JoinPoint joinPoint){
        log.info("Executing {}", joinPoint);
    }

    @After(value = "within(com.yazykov.currencyservice.service.*)")
    public void loggingStatementAfterCurrencyService(JoinPoint joinPoint){
        log.info("Complete {}", joinPoint);
    }

    @Around(value = "execution(* com.yazykov.currencyservice.security.service.*.*(..))")
    public Object exceptionHandling(ProceedingJoinPoint joinPoint) throws Throwable{
        try {
            Object obj = joinPoint.proceed();
            return obj;
        } catch (ExceptionHandler ex){
            log.info("Exception message {}", ex.getMessage());
            log.info("Exception status {}", ex.getHttpStatus());
            throw new ResponseStatusException(ex.getHttpStatus(), ex.getMessage());
        }
    }
}
