package com.kim.spring.cloud.alibaba.rocketmq.stream.controller;

import com.kim.spring.cloud.alibaba.rocketmq.stream.processer.MySource;
import com.kim.spring.cloud.alibaba.rocketmq.stream.processer.TransactionListenerImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author huangjie
 * @description
 * @date 2021/9/24
 */
@Api(tags = "发送消息")
@RestController
@RequestMapping("/rocketmq/stream")
public class SendMsgController {

    @Autowired
    private MySource send;

    @ApiOperation("发消息")
    @GetMapping("/send")
    public String send() {
        send.output1().send(MessageBuilder.withPayload("hello stream rocketmq").setHeader(RocketMQHeaders.TAGS, "tagA").setHeader(RocketMQHeaders.KEYS, "key").setHeader("a", 3)
                //.setHeader(MessageConst.PROPERTY_DELAY_TIME_LEVEL,"3")  //设置延时消息级别
                .build());
        return "success";
    }

    @ApiOperation("发送事务消息")
    @GetMapping("/send/transactional/msg")
    public String sendTransaction() {
        String uuid = uuid();
        final int[] count = {0};
        TransactionListenerImpl.setHandle(uuid, new RocketMQLocalTransactionListener() {

            @Override
            public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {
                System.out.printf("execute local : %n%s, %s", message, o);
                return RocketMQLocalTransactionState.UNKNOWN;
            }

            @Override
            public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
                System.out.printf("check local : %n%s", message);
                count[0]++;
                return count[0] >= 3 ? RocketMQLocalTransactionState.COMMIT : RocketMQLocalTransactionState.UNKNOWN;
            }
        });
        send.output2().send(MessageBuilder.withPayload("hello transactional msg").setHeader(RocketMQHeaders.TAGS, "tagA").setHeader("arg",uuid).build());
        return "success";
    }

    private String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }


}
