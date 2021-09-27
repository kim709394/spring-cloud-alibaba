package com.kim.spring.cloud.alibaba.rocketmq.stream.processer;

import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.messaging.Message;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author huangjie
 * @description 事务消息本地事务状态逻辑监听器
 * @date 2021/9/22
 */
@RocketMQTransactionListener(txProducerGroup = "rocketmq-stream-output2")
public class TransactionListenerImpl implements RocketMQLocalTransactionListener {

    private static Map<String, RocketMQLocalTransactionListener> executeLocalTransactionHandle = new ConcurrentHashMap<>();

    public static void setHandle(String arg, RocketMQLocalTransactionListener listener) {
        executeLocalTransactionHandle.put(arg, listener);
    }

    private static void remove(Object key) {
        executeLocalTransactionHandle.remove(key);
    }

    private static RocketMQLocalTransactionListener get(Object key) {
        return executeLocalTransactionHandle.get(key);
    }

    private String getTransactionId(Message msg) {
        return msg.getHeaders().get(RocketMQHeaders.PREFIX + RocketMQHeaders.TRANSACTION_ID).toString();
    }

    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        System.out.println("本地事务提交逻辑方法开始执行");
        //根据发送消息的参数获取逻辑方法监听器
        RocketMQLocalTransactionListener listener = get(msg.getHeaders().get("arg"));
        String transactionId = getTransactionId(msg);
        //替换方法逻辑器的key值，方便本地事务检查方法获取
        setHandle(transactionId, listener);
        remove(msg.getHeaders().get("arg"));
        //利用逻辑方法监听器针对每一个事务消息处理本地事务逻辑
        return listener.executeLocalTransaction(msg, arg);
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        System.out.println("本地事务提交检查逻辑开始执行");
        //根据事务id获取逻辑方法监听器
        String transactionId = getTransactionId(msg);
        RocketMQLocalTransactionListener listener = get(transactionId);
        return listener.checkLocalTransaction(msg);
    }
}
