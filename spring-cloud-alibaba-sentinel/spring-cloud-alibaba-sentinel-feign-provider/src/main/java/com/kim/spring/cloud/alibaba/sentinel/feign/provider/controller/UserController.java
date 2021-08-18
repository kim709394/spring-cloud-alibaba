package com.kim.spring.cloud.alibaba.sentinel.feign.provider.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author kim
 * @Since 2021/8/18
 */
@RestController
@RequestMapping("/sentinel/feign")
public class UserController {


    @GetMapping("/user/get/{name}")
    public String getUser(@PathVariable(name="name") String name){
        return "hello i am "+name;
    }

}
