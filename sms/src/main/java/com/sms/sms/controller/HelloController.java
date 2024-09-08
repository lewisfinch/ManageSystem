package com.sms.sms.controller;

import com.sms.sms.entity.User;
import com.sms.sms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {

    @GetMapping
    public String hello(){
        return "hello sms";
    }

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public List<User> list(){
//        return userService.list();
        return userService.listAll();
    }
}
