package com.kim.spring.cloud.alibaba.rocketmq.spring.controller;

import com.kim.spring.cloud.alibaba.rocketmq.spring.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author huangjie
 * @description
 * @date 2021/9/17
 */
@Api(tags = "rocketmq-spring客户端")
@RestController
@RequestMapping("rocketmq/producer")
public class RocketProducerController {

    //注入rocket客户端模板
    @Autowired
    private RocketMQTemplate rocketMQTemplate;


    /**
     * 同步发送消息
     * */
    @ApiOperation("同步发送消息")
    @GetMapping("/send/sync")
    public String sendSync(){
        rocketMQTemplate.convertAndSend("TopicTest","hello");
        rocketMQTemplate.send("TopicTest", MessageBuilder.withPayload("hello world").build());
        return "success";

    }

    @ApiOperation("同步发送对象序列化的消息,将序列化为json字符串")
    @GetMapping("/send/sync/obj")
    public String sendSyncObj(){
        User user=User.builder().id(1).name("alan").createTime(new Date());
        rocketMQTemplate.send("TopicTest",MessageBuilder.withPayload(user).build());
        return "success";
    }

    @ApiOperation("单向发送消息")
    @GetMapping("/send/oneway")
    public String sendOneWay(){
        rocketMQTemplate.sendOneWay("SendOneway","hello");
        return "success";
    }

    /**
     * 异步发送消息
     * */
    @ApiOperation("异步发送消息")
    @GetMapping("/send/async")
    public String sendAsync(){
        for(int i=0;i<10;i++){
            int finalI = i;
            rocketMQTemplate.asyncSend("AsyncTopic", "hello" + i, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    System.out.println("第"+ finalI +"条消息发送成功");
                    System.out.printf("发送结果:%n%s ",sendResult);
                }

                @Override
                public void onException(Throwable throwable) {
                    System.out.println("第"+ finalI +"条消息发送失败");
                    throwable.printStackTrace();
                }
            });
        }
        return "success";
    }


    /**
     * 发送顺序消息
     * */
    @ApiOperation("发送顺序消息")
    @GetMapping("/send/orderly")
    public String sendOrderly(){

        for(int i=0;i<10;i++){
            //hashKey是通过一个参数的哈希值除以队列的size取余的算法去获取某个队列
            SendResult sendResult = rocketMQTemplate.syncSendOrderly("OrderMsg", "orderMsg" + i, "hashKey");
            System.out.printf("发送结果: %n%s : ",sendResult);
        }
        return "success";

    }

    @ApiOperation("延时消息")
    @GetMapping("/send/delay")
    public String sendDelay(){
        rocketMQTemplate.asyncSend("DelayMsg", MessageBuilder.withPayload("delayMsg").build(), new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.printf("发送结果:%n%s ",sendResult);
            }

            @Override
            public void onException(Throwable e) {
                e.printStackTrace();
            }
        },3000L,3);
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        return "success";
    }


}
