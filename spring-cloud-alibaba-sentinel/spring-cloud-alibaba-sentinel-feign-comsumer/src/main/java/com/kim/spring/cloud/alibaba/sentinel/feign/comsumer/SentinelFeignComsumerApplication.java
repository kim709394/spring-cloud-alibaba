package com.kim.spring.cloud.alibaba.sentinel.feign.comsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author kim
 * @Since 2021/8/18
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.kim.spring.cloud.alibaba.sentinel.feign.comsumer.client")
public class SentinelFeignComsumerApplication {


    public static void main(String[] args) {
        SpringApplication.run(SentinelFeignComsumerApplication.class);
    }

}
