package com.country.queue.pojo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value= "classpath:sysconfig.properties",encoding = "utf-8")
@ConfigurationProperties(prefix = "my")
@Data
public class User {

    private int id;

    private String name;

    private String userName;

    private String passWord;
}
