package com.kim.spring.cloud.alibaba.sentinel.feign.comsumer.client.fallback;

import com.kim.spring.cloud.alibaba.sentinel.feign.comsumer.client.UserClient;
import org.springframework.stereotype.Component;

/**
 * @Author kim
 * @Since 2021/8/18
 */
/**
 * 需要将fallback注册进spring容器
 * sentinel所有规则控制都走fallback方法里面执行，流控和熔断等，
 * */
@Component
public class UserFallback implements UserClient {


    @Override
    public String getUser(String name) {
        return "i am fallback:"+name;
    }
}
