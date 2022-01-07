package com.kim.spring.cloud.alibaba.rocketmq.spring.consumer.listener;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.common.UtilAll;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

/**
 * @author huangjie
 * @description  接收原生消息响应对象，并且可以对消费者进行一些系统的设置
 * @date 2021/9/18
 */
@Component
@RocketMQMessageListener(topic = "TopicTest",
        messageModel = MessageModel.BROADCASTING,   //配置消费模式为广播模式
        consumerGroup = "rocketmq-spring-group-3")
public class OriginalMsgListener implements RocketMQListener<MessageExt>, RocketMQPushConsumerLifecycleListener {

    //获取原始消息对象，包含了消息体和消息头以及其他信息
    @Override
    public void onMessage(MessageExt message) {
        System.out.printf("receive message:%n%s ",message);
        try {
            System.out.println(new String(message.getBody(),"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    //对消费者进行一些系统设置
    @Override
    public void prepareStart(DefaultMQPushConsumer consumer) {
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_TIMESTAMP);
        consumer.setConsumeTimestamp(UtilAll.timeMillisToHumanString3(System.currentTimeMillis()));
    }
}
