package com.kim.spring.cloud.alibaba.rocketmq.stream.processer;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @author huangjie
 * @description
 * @date 2021/9/26
 */
public interface MySource {


    @Output("output1")
    MessageChannel output1();

    @Output("output2")
    MessageChannel output2();

}
