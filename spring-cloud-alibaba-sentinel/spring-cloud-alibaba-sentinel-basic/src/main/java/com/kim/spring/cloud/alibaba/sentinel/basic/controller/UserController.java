package com.kim.spring.cloud.alibaba.sentinel.basic.controller;

import com.kim.spring.cloud.alibaba.sentinel.basic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author kim
 * @Since 2021/8/7
 */
@RestController
@RequestMapping("/sentinel")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/hello/{name}")
    public String hello(@PathVariable(name="name") String name){
        return userService.hello(name);
    }

    @GetMapping("/hi")
    public String hi(){
        return userService.hi();
    }

    @GetMapping("/how")
    public String how(){
        userService.how();
        return "how success";
    }
}
