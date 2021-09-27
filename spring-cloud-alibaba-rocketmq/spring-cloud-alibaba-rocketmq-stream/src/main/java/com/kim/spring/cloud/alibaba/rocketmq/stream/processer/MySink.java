package com.kim.spring.cloud.alibaba.rocketmq.stream.processer;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author huangjie
 * @description
 * @date 2021/9/26
 */
public interface MySink {


    @Input("input1")
    SubscribableChannel input1();

    @Input("input2")
    SubscribableChannel input2();


}
