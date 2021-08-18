package com.kim.spring.cloud.alibaba.sentinel.feign.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author kim
 * @Since 2021/8/18
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SentinelFeignProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SentinelFeignProviderApplication.class);
    }

}
