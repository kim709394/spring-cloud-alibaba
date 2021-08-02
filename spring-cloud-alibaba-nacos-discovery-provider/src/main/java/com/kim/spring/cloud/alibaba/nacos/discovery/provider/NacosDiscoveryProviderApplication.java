package com.kim.spring.cloud.alibaba.nacos.discovery.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author kim
 * @Since 2021/8/2
 */
@SpringBootApplication
@EnableDiscoveryClient
public class NacosDiscoveryProviderApplication {


    public static void main(String[] args) {
        SpringApplication.run(NacosDiscoveryProviderApplication.class);
    }

}
