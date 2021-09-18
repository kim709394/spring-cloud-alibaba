package com.kim.spring.cloud.alibaba.rocketmq.spring.consumer.listener;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.common.UtilAll;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.annotation.SelectorType;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.stereotype.Component;

/**
 * @author huangjie
 * @description
 * @date 2021/9/18
 */
@Component
@RocketMQMessageListener(topic = "TopicTest",
        consumerGroup = "rocketmq-spring-group-3")
public class OriginalMsgListener implements RocketMQListener<MessageExt>, RocketMQPushConsumerLifecycleListener {

    //获取原始消息对象，包含了消息体和消息头以及其他信息
    @Override
    public void onMessage(MessageExt message) {
        System.out.printf("receive message:%n%s ",message);
    }

    //对消费者进行一些系统设置
    @Override
    public void prepareStart(DefaultMQPushConsumer consumer) {
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_TIMESTAMP);
        consumer.setConsumeTimestamp(UtilAll.timeMillisToHumanString3(System.currentTimeMillis()));
    }
}
