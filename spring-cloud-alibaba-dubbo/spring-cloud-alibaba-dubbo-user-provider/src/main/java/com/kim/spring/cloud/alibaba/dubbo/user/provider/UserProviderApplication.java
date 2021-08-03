package com.kim.spring.cloud.alibaba.dubbo.user.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author kim
 * @Since 2021/8/3
 */
@SpringBootApplication
@EnableDiscoveryClient
public class UserProviderApplication {


    public static void main(String[] args) {
        SpringApplication.run(UserProviderApplication.class);
    }
}
