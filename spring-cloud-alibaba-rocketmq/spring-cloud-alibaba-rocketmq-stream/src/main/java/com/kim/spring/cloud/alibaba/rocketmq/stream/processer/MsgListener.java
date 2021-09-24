package com.kim.spring.cloud.alibaba.rocketmq.stream.processer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.binder.PollableMessageSource;
import org.springframework.stereotype.Component;

/**
 * @author huangjie
 * @description
 * @date 2021/9/24
 */
@Component
public class MsgListener {

    @Autowired
    private PollableMessageSource source;



    public void listener(){
        source.poll(message -> {
            String receive = (String)message.getPayload();
            System.out.printf("receive a message: %s",receive);
        });
    }

}
