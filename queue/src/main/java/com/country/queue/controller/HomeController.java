package com.country.queue.controller;

import com.country.queue.mapper.UserMapper;
import com.country.queue.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private User user;

    @Autowired
    private UserMapper userMapper;

    @RequestMapping
    public String welcome(){
        System.out.println(userMapper.findUser("张三"));
        return "我的名字：" +user.getName();
    }
}
