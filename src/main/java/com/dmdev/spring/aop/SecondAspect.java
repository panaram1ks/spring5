package com.dmdev.spring.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
@Order(2)
public class SecondAspect {

    // All in one !
    @Around("com.dmdev.spring.aop.FirstAspect.anyFindByIdServiceMethod() && target(service) && args(id)")
    public Object addLoggingAround(ProceedingJoinPoint joinPoint, Object service, Object id) throws Throwable {
        log.warn("@Around (Before) - invoked findById method in class {}, with id {}", service, id);
        try{
            Object result = joinPoint.proceed();
            log.warn("@Around (AfterReturning) - invoked findById method in class {}, with result {}", service, result);
            return result;
        } catch (Throwable ex){
            log.warn("@Around (AfterThrowing) - invoked findById method in class {}, with exception {}: {}", service, ex.getClass(), ex.getMessage());
            throw ex;
        } finally {
            log.warn("@Around (After) - invoked findById method in class {}", service);
        }
    }

}
