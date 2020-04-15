package com.country.queue.pojo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value= "classpath:sysconfig.properties",encoding = "utf-8")
@ConfigurationProperties(prefix = "my")
public class User {

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
