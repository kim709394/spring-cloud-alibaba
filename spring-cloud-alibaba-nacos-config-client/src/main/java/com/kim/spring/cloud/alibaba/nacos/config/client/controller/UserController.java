package com.kim.spring.cloud.alibaba.nacos.config.client.controller;

import com.kim.spring.cloud.alibaba.nacos.config.client.config.UserConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author kim
 * @Since 2021/7/30
 */
//使用@Value注解获取的配置需要配合使用@RefreshScope注解才能在nacos配置更新了之后随之更新
@RefreshScope
@RestController
@RequestMapping("/nacos/config")
public class UserController {

    @Value("${user.name}")
    private String username;

    @Value("${spring.application.name}")
    private String name;

    @Autowired
    private UserConfig userConfig;

    @GetMapping("/get")
    public String get(){
        return String.format("从@ConfigurationProperties注解获取的配置信息:" +
                "username:%s,id:%s,age:%s；从@Value注解获取的配置信息:username:%s",
                userConfig.getName(),userConfig.getId(),userConfig.getAge(),username);
    }

    @GetMapping("/name")
    public String name(){
        return name;
    }

}
