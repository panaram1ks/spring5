package com.dmdev.spring.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Aspect
@Component
public class FirstAspect {

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

    /**
     * this - check AOP proxy class type
     * target - check target object class type
     */
    @Pointcut("this(org.springframework.data.repository.Repository)")
//    @Pointcut("target(org.springframework.data.repository.Repository)")
    public void isRepositoryLayer() {
    }

    /**
     * @annotation - check annotation above method
     */
    @Pointcut("isControllerLayer() && @annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void hasGetMapping() {
    }

    /**
     * args - check method param type
     * * - any param type
     * .. - 0+ any params type
     */
    @Pointcut("isControllerLayer() && args(org.springframework.ui.Model,..)")
    // first parameter is Model and then any numbers of parameters
//    @Pointcut("args(org.springframework.ui.Model,*)") // first parameter is Model and only one more parameter
//    @Pointcut("args(org.springframework.ui.Model,*,*,*)") // first parameter is Model and 3 parameters after
    public void hasModelParam() {
    }

    /**
     * @args - try find methods where first and only one parm has annotation UserInfo
     * @args - check annotation above the param type
     */
//    @Pointcut("isControllerLayer() && @args(com.dmdev.spring.validation.UserInfo)")
    @Pointcut("isControllerLayer() && @args(com.dmdev.spring.validation.UserInfo,..)")
    public void hasUserInfoParamAnnotation() {
    }

    /**
     * bean - check bean name
     */
//    @Pointcut("bean(userService)")
    @Pointcut("bean(*Service)")
    public void isServiceLayerBean() {
    }

    /**
     * ? - optional parameter
     * execution(modifiers-pattern? return-type-pattern declaring-type-pattern?name-pattern(param-pattern) throws-pattern?)
     */
//    @Pointcut("execution(public Long com.dmdev.spring.service.*.findById(Integer, Long)) SQLException ) )")
    @Pointcut("execution(public * findById(..))")
    public void anyFindByIdServiceMethod() {
    }

//    Pointcut - is simply predicate return true or false!

    //  ADVICE  !!!
    @Before(value = "anyFindByIdServiceMethod() && args(id) && target(service) && this(serviceProxy) && @within(transactional)",
            argNames = "joinPoint,id,service,serviceProxy,transactional")
//    @Before("execution(public Long com.dmdev.spring.service.*.findById(Integer, Long)) SQLException ) )")
//    JoinPoint always should go first !!!
    public void addLogging(JoinPoint joinPoint, Object id, Object service, Object serviceProxy, Transactional transactional) {
        log.warn("invoked findById method in class {}, with id {}", service, id);
    }


}
