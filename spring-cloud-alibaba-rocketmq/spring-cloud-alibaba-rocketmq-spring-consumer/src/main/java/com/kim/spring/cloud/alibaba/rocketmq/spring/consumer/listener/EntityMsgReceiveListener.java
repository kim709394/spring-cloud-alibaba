package com.kim.spring.cloud.alibaba.rocketmq.spring.consumer.listener;

import com.kim.spring.cloud.alibaba.rocketmq.spring.consumer.entity.User;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.annotation.SelectorType;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @author huangjie
 * @description   接收实体对象类型的消息响应对象
 * @date 2021/9/18
 */
@Component
@RocketMQMessageListener(topic = "TopicTest",
        consumerGroup = "rocketmq-spring-group-2",
        selectorType = SelectorType.TAG,
        selectorExpression = "tagA"
)
public class EntityMsgReceiveListener implements RocketMQListener<User> {


    @Override
    public void onMessage(User message) {
        System.out.printf("receive message:%n%s ",message);
    }

    /**
     * pull模式详见:
     * https://github.com/apache/rocketmq-spring/wiki/%E6%8E%A5%E6%94%B6%E6%B6%88%E6%81%AF
     * */
}
