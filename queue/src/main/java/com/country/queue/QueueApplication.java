package com.country.queue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
* Description: 若是要通过外置的servlet容器，则继承
 * SpringBootServletInitializer 并重写configure方法即可
* @author: yuhang tao
* @date: 2020/5/7
* @version: v1.0
*/
@SpringBootApplication
public class QueueApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(QueueApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(QueueApplication.class);
    }
}
