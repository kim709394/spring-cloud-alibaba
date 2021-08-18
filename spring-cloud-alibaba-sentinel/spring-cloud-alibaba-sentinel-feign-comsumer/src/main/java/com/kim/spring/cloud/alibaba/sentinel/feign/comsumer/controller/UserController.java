package com.kim.spring.cloud.alibaba.sentinel.feign.comsumer.controller;

import com.kim.spring.cloud.alibaba.sentinel.feign.comsumer.client.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private UserClient userClient;

    @GetMapping("/comsumer/{name}")
    public String getUser(@PathVariable(name="name") String name){

        return userClient.getUser(name);

    }

}
