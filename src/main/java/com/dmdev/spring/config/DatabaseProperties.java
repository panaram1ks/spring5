package com.dmdev.spring.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@ConfigurationProperties(prefix = "db")
//@Component //or use @ConfigurationPropertiesScan on mane class
public class DatabaseProperties {
    private String username;
    private String password;
    private String driver;
    private String url;
    private String hosts;
    private PoolProperties pool;
    private List<PoolProperties> pools;
    private Map<String, Object> properties;

    @Data
    @NoArgsConstructor
    public static class PoolProperties {
        private Integer size;
        private Integer timeout;
    }
}
