package com.kim.spring.cloud.alibaba.rocketmq.spring.controller;

import com.kim.spring.cloud.alibaba.rocketmq.spring.entity.User;
import com.kim.spring.cloud.alibaba.rocketmq.spring.listener.TransactionListenerImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.MessageConst;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

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
     */
    @ApiOperation("同步发送消息")
    @GetMapping("/send/sync")
    public String sendSync() {
        //tpoic:tag  指定topic和tag，只能指定一个tag
        rocketMQTemplate.convertAndSend("TopicTest:tagA", "hello");
        /**
         * 通过setHeaders方法设置消息的key值
         * 同理还可以根据这种方式来设置消息的FLAG、WAIT_STORE_MSG_OK以及一些用户自定义的其它头信息
         * 在将Spring的Message转化为RocketMQ的Message时，为防止header信息与RocketMQ的系统属性冲突，
         * 在所有header的名称前面都统一添加了前缀USERS_。因此在消费时如果想获取自定义的消息头信息，
         * 请遍历头信息中以USERS_开头的key即可
         * */
        rocketMQTemplate.send("TopicTest:tagA", MessageBuilder.withPayload("hello world").setHeader(MessageConst.PROPERTY_KEYS, "key").setHeader("a", 3).build());
        return "success";

    }

    @ApiOperation("同步发送对象序列化的消息,将序列化为json字符串")
    @GetMapping("/send/sync/obj")
    public String sendSyncObj() {
        User user = User.builder().id(1).name("alan").createTime(new Date());
        rocketMQTemplate.send("TopicTest:tagA", MessageBuilder.withPayload(user).build());
        return "success";
    }

    @ApiOperation("单向发送消息")
    @GetMapping("/send/oneway")
    public String sendOneWay() {
        rocketMQTemplate.sendOneWay("SendOneway", "hello");
        return "success";
    }

    /**
     * 异步发送消息
     */
    @ApiOperation("异步发送消息")
    @GetMapping("/send/async")
    public String sendAsync() {
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            rocketMQTemplate.asyncSend("AsyncTopic", "hello" + i, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    System.out.println("第" + finalI + "条消息发送成功");
                    System.out.printf("发送结果:%n%s ", sendResult);
                }

                @Override
                public void onException(Throwable throwable) {
                    System.out.println("第" + finalI + "条消息发送失败");
                    throwable.printStackTrace();
                }
            });
        }
        return "success";
    }


    /**
     * 发送顺序消息
     */
    @ApiOperation("发送顺序消息")
    @GetMapping("/send/orderly")
    public String sendOrderly() {

        for (int i = 0; i < 10; i++) {
            //hashKey是通过一个参数的哈希值除以队列的size取余的算法去获取某个队列
            SendResult sendResult = rocketMQTemplate.syncSendOrderly("OrderMsg", "orderMsg" + i, "hashKey");
            System.out.printf("发送结果: %n%s : ", sendResult);
        }
        return "success";

    }

    //延时消息需要先启动消息消费者服务再发送。延时消息只有在被消费后才会存储
    @ApiOperation("发送延时消息")
    @GetMapping("/send/delay")
    public String sendDelay() {
        rocketMQTemplate.syncSend("DelayMsg:tagB", MessageBuilder.withPayload("hello delayMsg").build(), 3000L, 3);
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        return "success";
    }

    /**
     * 事务消息例子
     */
    @ApiOperation("本地事务提交成功的例子")
    @GetMapping("/send/transaction/local/success")
    public String sendTransactionLocalSuccess() {
        String uuid = uuid();
        TransactionListenerImpl.setHandle(uuid, new RocketMQLocalTransactionListener() {
            @Override
            public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
                //TODO 模拟业务逻辑
                return RocketMQLocalTransactionState.COMMIT;
            }

            @Override
            public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
                //TODO 模拟业务逻辑
                return RocketMQLocalTransactionState.COMMIT;
            }
        });
        rocketMQTemplate.sendMessageInTransaction("TransactionMsg", MessageBuilder.withPayload("transactionMsg" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())).build(), uuid);
        return "success";
    }

    @ApiOperation("本地事务提交失败的例子")
    @GetMapping("/send/transaction/local/failed")
    public String sendTransactionLocalFailed() {
        String uuid = uuid();
        TransactionListenerImpl.setHandle(uuid, new RocketMQLocalTransactionListener() {
            @Override
            public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
                //TODO 模拟业务逻辑
                return RocketMQLocalTransactionState.ROLLBACK;
            }

            @Override
            public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
                //TODO 模拟业务逻辑
                return RocketMQLocalTransactionState.COMMIT;
            }
        });
        rocketMQTemplate.sendMessageInTransaction("TransactionMsg", MessageBuilder.withPayload("transactionMsg" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())).build(), uuid);
        return "success";
    }

    @ApiOperation("本地事务提交未知，检查3次后成功提交的例子")
    @GetMapping("/send/transaction/check/success")
    public String sendTransactionCheck3TimesSuccess() {
        String uuid = uuid();
        final int[] count = {0};
        TransactionListenerImpl.setHandle(uuid, new RocketMQLocalTransactionListener() {
            @Override
            public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
                //TODO 模拟业务逻辑
                return RocketMQLocalTransactionState.UNKNOWN;
            }

            @Override
            public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
                //TODO 模拟业务逻辑
                count[0]++;
                System.out.println("检查第" + count[0] + "次");
                return count[0] >= 3 ? RocketMQLocalTransactionState.COMMIT : RocketMQLocalTransactionState.UNKNOWN;
            }
        });
        rocketMQTemplate.sendMessageInTransaction("TransactionMsg", MessageBuilder.withPayload("transactionMsg" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())).build(), uuid);
        return "success";
    }

    @ApiOperation("本地事务提交未知，检查3次后事务依然失败的例子")
    @GetMapping("/send/transaction/check/failed")
    public String sendTransactionCheck3Timesfailed() {
        String uuid = uuid();
        final int[] count = {0};
        TransactionListenerImpl.setHandle(uuid, new RocketMQLocalTransactionListener() {
            @Override
            public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
                //TODO 模拟业务逻辑
                return RocketMQLocalTransactionState.UNKNOWN;
            }

            @Override
            public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
                //TODO 模拟业务逻辑
                count[0]++;
                System.out.println("检查第" + count[0] + "次");
                return count[0] >= 3 ? RocketMQLocalTransactionState.ROLLBACK : RocketMQLocalTransactionState.UNKNOWN;
            }
        });
        rocketMQTemplate.sendMessageInTransaction("TransactionMsg", MessageBuilder.withPayload("transactionMsg" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())).build(), uuid);
        return "success";
    }

    private String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");

    }

}
