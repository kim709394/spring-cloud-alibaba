package com.kim.spring.cloud.alibaba.rocketmq.stream.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.rocketmq.common.message.MessageConst;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.binder.PollableMessageSource;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    private MessageChannel dest;

    @ApiOperation("发消息")
    @GetMapping("/send")
    public String send(){
        dest.send(MessageBuilder.withPayload("hello stream rocketmq")
                .setHeader(RocketMQHeaders.TOPIC,"TopicTest")
                .setHeader(RocketMQHeaders.TAGS,"tagA")
                .setHeader(RocketMQHeaders.KEYS,"key")
                .setHeader("a",3)
                //.setHeader(MessageConst.PROPERTY_DELAY_TIME_LEVEL,"3")  //设置延时消息级别
                .build());
        return "success";
    }

}
