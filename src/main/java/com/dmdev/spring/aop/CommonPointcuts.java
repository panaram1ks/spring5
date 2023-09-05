package com.dmdev.spring.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class CommonPointcuts {


    /**
     * @within - check annotation on the class level
     */
    @Pointcut("@within(org.springframework.stereotype.Controller)")
    public void isControllerLayer() {
    }

    /**
     * within - check class type name
     */
//    @Pointcut("within(com.dmdev.spring.service.*)") // classes in same catalogue
    @Pointcut("within(com.dmdev.spring.service.*Service)") // classes use Postfix
//    @Pointcut("within(com.dmdev.spring.service..*)") // classes in under catalogues
    public void isServiceLayer() {
    }
}
