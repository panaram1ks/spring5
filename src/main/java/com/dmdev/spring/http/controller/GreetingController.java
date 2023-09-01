package com.dmdev.spring.http.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/api/v1/")
public class GreetingController {


    @GetMapping("/hello/{id}")
    public ModelAndView hello(ModelAndView modelAndView, HttpServletRequest request,
                              @RequestParam(value = "age", required = false) Integer age,
                              @RequestHeader("accept") String accept,
                              @CookieValue("JSESSIONID") String JSESSIONID,
                              @PathVariable("id") Integer id) {
//        @RequestParam(value = "age", required = false) Integer age // get parameter from request and auto-convert it to necessary Type // spring way
//        @RequestHeader("accept") String accept  // spring way

//        String age = request.getParameter("age"); // old way
//        String accept = request.getHeader("accept"); // old way
//        Cookie[] cookies = request.getCookies(); // old way

        modelAndView.setViewName("greeting/hello");

        return modelAndView;  //                 http://localhost:8080/api/v1/hello/29?age=18
    }

    @GetMapping("/bye")
    public ModelAndView bye(ModelAndView modelAndView) {
        modelAndView.setViewName("greeting/bye");

        return modelAndView;
    }

}
