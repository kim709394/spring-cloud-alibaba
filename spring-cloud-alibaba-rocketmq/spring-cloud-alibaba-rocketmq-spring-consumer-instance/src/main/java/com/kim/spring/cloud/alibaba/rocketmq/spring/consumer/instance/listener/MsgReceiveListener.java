package com.kim.spring.cloud.alibaba.rocketmq.spring.consumer.instance.listener;

import org.apache.rocketmq.spring.annotation.MessageModel;
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
        consumerGroup = "rocketmq-spring-group3",
        messageModel = MessageModel.BROADCASTING   //配置消费模式为广播模式
)
public class MsgReceiveListener implements RocketMQListener<String> {


    @Override
    public void onMessage(String message) {
        System.out.printf("received message: %s",message);
        System.out.println();
    }
}
