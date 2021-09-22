package com.kim.spring.cloud.alibaba.rocketmq.spring.consumer.listener;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.annotation.SelectorType;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @author huangjie
 * @description  接收string类型的消息响应对象
 * @date 2021/9/18
 */
@Component
@RocketMQMessageListener(topic = "TopicTest",
        consumerGroup = "rocketmq-spring-group",
        selectorType = SelectorType.SQL92,  //消息过滤类型，可以选择tag或者sql92表达式
        selectorExpression = "a>=3")   //消息过滤通配符或表达式，值可为tag或者sql92表达式
public class MsgReceiveListener implements RocketMQListener<String> {


    @Override
    public void onMessage(String message) {
        System.out.printf("received message: %s",message);
        System.out.println();
    }
}
