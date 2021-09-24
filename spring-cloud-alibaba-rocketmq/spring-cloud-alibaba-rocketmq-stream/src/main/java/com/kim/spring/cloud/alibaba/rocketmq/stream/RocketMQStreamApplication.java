package com.kim.spring.cloud.alibaba.rocketmq.stream;

import com.kim.spring.cloud.alibaba.rocketmq.stream.processer.PolledProcesser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * @author huangjie
 * @description
 * @date 2021/9/24
 */
@SpringBootApplication
@EnableBinding(PolledProcesser.class)
public class RocketMQStreamApplication {


    public static void main(String[] args) {
        SpringApplication.run(RocketMQStreamApplication.class);
    }
}
