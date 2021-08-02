package com.kim.spring.cloud.alibaba.nacos.discovery.comsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @Author kim
 * @Since 2021/8/2
 */
@SpringBootApplication
@EnableDiscoveryClient
public class NacosDiscoveryComsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosDiscoveryComsumerApplication.class);
    }


    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
