package com.dmdev.spring.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

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


}
