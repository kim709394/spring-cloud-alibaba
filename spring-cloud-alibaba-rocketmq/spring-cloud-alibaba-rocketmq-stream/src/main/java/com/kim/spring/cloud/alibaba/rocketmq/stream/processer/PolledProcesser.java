package com.kim.spring.cloud.alibaba.rocketmq.stream.processer;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.binder.PollableMessageSource;
import org.springframework.messaging.MessageChannel;

/**
 * @author huangjie
 * @description
 * @date 2021/9/24
 */
public interface PolledProcesser {


    @Input
    PollableMessageSource source();

    @Output
    MessageChannel dest();
}
