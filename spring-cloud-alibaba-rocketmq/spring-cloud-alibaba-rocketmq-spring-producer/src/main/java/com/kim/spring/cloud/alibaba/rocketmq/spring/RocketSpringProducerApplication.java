package com.kim.spring.cloud.alibaba.rocketmq.spring;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author huangjie
 * @description
 * @date 2021/9/17
 */
@SpringBootApplication
@EnableSwagger2Doc
public class RocketSpringProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RocketSpringProducerApplication.class);
    }
}
