package com.dmdev.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("web") // it will be loaded only if profile web application
@Configuration
public class WebConfiguration {
}
