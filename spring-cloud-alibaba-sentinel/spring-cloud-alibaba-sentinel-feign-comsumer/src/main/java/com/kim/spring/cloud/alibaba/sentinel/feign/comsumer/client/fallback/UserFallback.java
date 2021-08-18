package com.kim.spring.cloud.alibaba.sentinel.feign.comsumer.client.fallback;

import com.kim.spring.cloud.alibaba.sentinel.feign.comsumer.client.UserClient;
import org.springframework.stereotype.Component;

/**
 * @Author kim
 * @Since 2021/8/18
 */
@Component
public class UserFallback implements UserClient {


    @Override
    public String getUser(String name) {
        return "i am fallback:"+name;
    }
}
