package com.kim.spring.cloud.alibaba.nacos.discovery.provider.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author kim
 * @Since 2021/8/2
 */
@RestController
@RequestMapping("/nacos/discovery")
public class ProviderController {



    @GetMapping("/provider")
    public String provider(){
        return "this is nacos discovery provider";
    }

}
