package com.kim.spring.cloud.alibaba.dubbo.user.comsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author kim
 * @Since 2021/8/3
 */
@SpringBootApplication
@EnableDiscoveryClient
public class UserComsumerApplication {


    public static void main(String[] args) {
        SpringApplication.run(UserComsumerApplication.class);
    }

}
