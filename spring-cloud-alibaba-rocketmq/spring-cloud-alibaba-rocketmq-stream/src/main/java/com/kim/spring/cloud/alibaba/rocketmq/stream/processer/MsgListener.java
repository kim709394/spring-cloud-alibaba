package com.kim.spring.cloud.alibaba.rocketmq.stream.processer;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

/**
 * @author huangjie
 * @description
 * @date 2021/9/24
 */
@Component
public class MsgListener {


    @StreamListener("input1")
    public void receiveInput1(String msg) {
        System.out.printf("input1 receive msg: %s", msg);
    }

    @StreamListener("input2")
    public void receiveInput2(String msg) {
        System.out.printf("input2 receive msg: %s", msg);
    }

}
