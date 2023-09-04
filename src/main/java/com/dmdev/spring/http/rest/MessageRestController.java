package com.dmdev.spring.http.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;
import java.util.ResourceBundle;

@RestController
@RequestMapping("/api/v1/messages")
@RequiredArgsConstructor
public class MessageRestController {

    private final MessageSource messageSource;

    @GetMapping
    public String getMessages(@RequestParam("key") String key,
                              @RequestParam("lang") String language){
        String messages = ResourceBundle.getBundle("messages").getString(key);// if we do not work in spring

        return messageSource.getMessage(key, null, null, new Locale(language));
    }

}
